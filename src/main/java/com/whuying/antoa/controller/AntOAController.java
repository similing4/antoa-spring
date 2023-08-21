package com.whuying.antoa.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.bean.AntOAApiColumnChangeResponse;
import com.whuying.antoa.bean.AntOAApiDetailResponse;
import com.whuying.antoa.bean.AntOAApiGridConfigResponse;
import com.whuying.antoa.bean.AntOAApiListResponse;
import com.whuying.antoa.bean.CustomParamResponse;
import com.whuying.antoa.bean.ErrorResponse;
import com.whuying.antoa.bean.NormalResponse;
import com.whuying.antoa.bean.AntOAApiGridConfigResponse.AntOAApiGridConfigResponseGrid;
import com.whuying.antoa.db.DB;
import com.whuying.antoa.db.PaginateResult;
import com.whuying.antoa.utils.*;
import com.whuying.antoa.utils.AbstractModel.*;
import com.whuying.antoa.utils.model.*;
import com.whuying.antoa.utils.hook.*;

public abstract class AntOAController {
	public static final String JSON_INPUT_KEY = "JSONINPUT";
	protected Grid gridObj = null; // Grid对象
	protected AuthService auth = null; // 如果你需要自己实现授权，你可以修改该类的实现。

	public void init() throws HttpException {
		this.auth = new AuthService();
		this.gridObj = new Grid();
		this.getUserInfo();
		this.grid(this.gridObj);
	}

	private String md5(String text) {
		return DigestUtils.md5DigestAsHex(text.getBytes()).toLowerCase();
	}

	/**
	 * 初始化Grid对象
	 * 
	 * @param Grid grid
	 */
	public abstract void grid(Grid grid);

	/**
	 * 处理统计数据
	 * 
	 * @return string 统计结果
	 */
	public abstract String statistic();
	
	protected String error(Exception e) {
		e.printStackTrace();
		return new ErrorResponse(0, e.getMessage(), null).toString();
	}
	
	protected String getRequestBaseUrl() {
		HttpServletRequest request = request();
		String url = request.getScheme() + "://" + request.getServerName();
		if(request.getScheme().toLowerCase().equals("https") && request.getServerPort() == 443)
			return url;
		if(request.getScheme().toLowerCase().equals("http") && request.getServerPort() == 80)
			return url;
		return url + ":" + request.getServerPort();
	}

	protected HttpServletRequest request() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		Assert.notNull(servletRequestAttributes, "RequestAttributes不能为null");
		HttpServletRequest ret = servletRequestAttributes.getRequest();
		if(ret.getAttribute(JSON_INPUT_KEY) == null)
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(ret.getInputStream(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				String temp;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				br.close();
				JSONObject req = JSONObject.parseObject(sb.toString());
				ret.setAttribute(JSON_INPUT_KEY, req);
			} catch(Exception e) {
				ret.setAttribute(JSON_INPUT_KEY, new JSONObject());
			}
		return ret;
	}

	/**
	 * 根据TOKEN获取UID，失败时抛出异常，只对API有效！
	 * 
	 * @return string UID
	 * @throws HttpException 
	 * @throws Exception token不合法时登录失效
	 */
	protected String getUserInfo() throws HttpException {
		String token = request().getHeader("Authorization");
		if (token == null)
			throw new HttpException("登录失效", 403);
		String uid = this.auth.getUidFromToken(token);
		if (uid == null)
			throw new HttpException("登录失效", 403);
		Map<String, Object> user = DB.table("antoa_user").where("id", uid).first();
		if (user == null)
			throw new HttpException("登录失效", 403);
		if ((int)(user.get("status")) == 0)
			throw new HttpException("账户已被封禁", 403);
		if (!this.checkPower(uid))
			throw new HttpException("权限不足", 403);
		return uid;
	}

	/**
	 * 获取页面所需的必要参数
	 * 
	 * @param Request request
	 * @return array 参数数组
	 */
	protected CustomParamResponse getCustomParam() {
		String path = request().getRequestURI();
		int apiPos = path.indexOf("api/");
		if (apiPos != -1)
			path = path.substring(apiPos + 4);
		path = path.substring(0, path.lastIndexOf("/"));
		CustomParamResponse ret = new CustomParamResponse();
		ret.grid = this.gridObj;
		ret.api = new CustomParamResponse.Api();
		ret.api.path = request().getRequestURI();
		ret.api.list = "/api/" + path + "/list";
		ret.api.create = "/api/" + path + "/create";
		ret.api.detail = "/api/" + path + "/detail";
		ret.api.save = "/api/" + path + "/save";
		ret.api.delete = "/api/" + path + "/delete";
		ret.api.detail_column_list = "/api/" + path + "/detail_column_list";
		ret.api.api_column_change = "/api/" + path + "/column_change";
		ret.api.api_upload = "/api/" + path + "/upload";
		ret.api.list_page = "/" + path + "/list";
		ret.api.create_page = "/" + path + "/create";
		ret.api.edit_page = "/" + path + "/edit";
		return ret;
	}

	/**
	 * /list 接口
	 *
	 * @return AntOAApiListResponse 列表页接口JSON
	 */
	public AntOAApiListResponse apiList() throws Exception {
		init();
		String uid = this.getUserInfo();
		if (this.gridObj.getGridList() == null)
			throw new Exception("页面配置信息不存在");
		DBListOperator gridListDbObject = this.gridObj.getGridList().getDBObject();
		GridList gridList = this.gridObj.getGridList();
		JSONObject req = (JSONObject) request().getAttribute(JSON_INPUT_KEY);
		List<UrlParamCalculatorParamItem> pageParams = new ArrayList<>();
		for (Entry<String, Object> key : req.entrySet())
			pageParams.add(new UrlParamCalculatorParamItem(key.getKey(), key.getValue()));
		UrlParamCalculator urlParamCalculator = new UrlParamCalculator(pageParams);
		for (ListFilterBase r : gridList.getFilterList())
			r.onFilter(gridListDbObject, urlParamCalculator, uid);
		List<String> columns = new ArrayList<>();
		for (ListTableColumnBase column : gridList.getTableColumnList()) {
			if (!column.isTypeDisplay())
				columns.add(column.col);
		}
		PaginateResult res = gridListDbObject.select(columns.toArray(new String[columns.size()])).paginate(15, Integer.parseInt(((req.get("page") == null) ? "1" : (req.get("page") + ""))));
		for (Map<String, Object> searchResultItem : res.data) {
			ArrayList<String> BUTTON_CONDITION_DATA = new ArrayList<String>();
			ArrayList<String> BUTTON_FINAL_URL_DATA = new ArrayList<String>();
			ArrayList<UrlParamCalculatorParamItem> searchResultParams = new ArrayList<UrlParamCalculatorParamItem>();
			for (ListTableColumnBase column : gridList.getTableColumnList()) {
				if (column.isTypeDisplay())
					searchResultItem.put(column.col, "");
				else
					column.onParse(searchResultItem, urlParamCalculator, uid);
				searchResultParams.add(new UrlParamCalculatorParamItem(column.col, searchResultItem.get(column.col)));
			}
			UrlParamCalculator rowParamCalculator = new UrlParamCalculator(pageParams, searchResultParams);
			for (ListRowButtonBase rowButtonItem : gridList.getRowButtonList()) {
				BUTTON_FINAL_URL_DATA.add(rowButtonItem.calcButtonFinalUrl(rowParamCalculator));
				BUTTON_CONDITION_DATA.add(rowButtonItem.judgeIsShow(rowParamCalculator) ? "1" : "0");
			}
			searchResultItem.put("BUTTON_CONDITION_DATA", BUTTON_CONDITION_DATA);
			searchResultItem.put("BUTTON_FINAL_URL_DATA", BUTTON_FINAL_URL_DATA);
		}
		AntOAApiListResponse resp = new AntOAApiListResponse(res);
		resp.status = 1;
		resp.statistic = this.statistic();
		ListHook hook = this.gridObj.getListHook();
		if (hook != null)
			return hook.hook(resp);
		return resp;
	}

	/**
	 * /grid_config 接口
	 *
	 * @return String 页面配置信息JSON
	 */
	public AntOAApiGridConfigResponse apiGridConfig() throws Exception {
		init();
		this.getUserInfo();
		GridList gridList = this.gridObj.getGridList();
		GridCreateForm gridCreate = this.gridObj.getCreateForm();
		GridEditForm gridEdit = this.gridObj.getEditForm();
		JSONObject req = (JSONObject) request().getAttribute(JSON_INPUT_KEY);
		List<UrlParamCalculatorParamItem> pageParams = new ArrayList<UrlParamCalculatorParamItem>();
		for (Entry<String, Object> key : req.entrySet())
			pageParams.add(new UrlParamCalculatorParamItem(key.getKey(), key.getValue()));
		UrlParamCalculator urlParamCalculator = new UrlParamCalculator(pageParams);
		List<ListHeaderButtonBase> removeList = new ArrayList<ListHeaderButtonBase>();
		if (gridList != null) {
			for (ListHeaderButtonBase headerButtonItem : gridList.getHeaderButtonList()) {
				if (!headerButtonItem.judgeIsShow(urlParamCalculator))
					removeList.add(headerButtonItem);
				headerButtonItem.finalUrl = headerButtonItem.calcButtonFinalUrl(urlParamCalculator);
			}
			gridList.removeHeaderButtons(removeList);
		}
		AntOAApiGridConfigResponse resp = new AntOAApiGridConfigResponse();
		resp.status = 1;
		resp.grid = new AntOAApiGridConfigResponseGrid();
		resp.grid.list = gridList;
		resp.grid.create = gridCreate;
		resp.grid.edit = gridEdit;
		resp.api = this.getCustomParam().api;
		return resp;
	}

	/**
	 * /create 接口
	 *
	 * @return NormalResponse 通用成功失败返回
	 */
	public NormalResponse apiCreate() throws Exception {
		init();
		String uid = this.getUserInfo();
		if (this.gridObj.getCreateForm() == null)
			throw new Exception("页面配置信息不存在");
		JSONObject req = (JSONObject) request().getAttribute(JSON_INPUT_KEY);
		GridCreateForm gridCreateForm = this.gridObj.getCreateForm();
		Map<String, Object> param = new HashMap<>();
		for (CreateColumnBase col : gridCreateForm.getCreateColumnList())
			param.put(col.col, col.onGuestVal(req, uid));
		CreateHook hook = this.gridObj.getCreateHook();
		if (hook != null)
			param = hook.hook(param);
		if (param != null)
			gridCreateForm.getDBObject().insert(param);
		return new NormalResponse(1, "创建成功", null);
	}

	/**
	 * /detail 接口
	 *
	 * @return AntOAApiDetailResponse 详情数据
	 */
	public AntOAApiDetailResponse apiDetail() throws Exception {
		init();
		if (this.gridObj.getEditForm() == null)
			throw new Exception("页面配置信息不存在");
		String uid = this.getUserInfo();
		JSONObject req = (JSONObject) request().getAttribute(JSON_INPUT_KEY);
		GridEditForm gridEditForm = this.gridObj.getEditForm();
		Map<String, Object> res = gridEditForm.getDBObject().find(req.getString(gridEditForm.primaryKey));
		if (res == null)
			throw new Exception("该项目不存在");
		for (EditColumnBase col : gridEditForm.getEditColumnList())
			res.put(col.col, col.onServerVal(new JSONObject(res), uid));
		DetailHook hook = this.gridObj.getDetailHook();
		if (hook != null)
			return hook.hook(new AntOAApiDetailResponse(1, res));
		return new AntOAApiDetailResponse(1, res);
	}

	/**
	 * /save 接口
	 *
	 * @return NormalResponse 通用成功失败返回
	 */
	public NormalResponse apiSave() throws Exception {
		init();
		if (this.gridObj.getEditForm() == null)
			throw new Exception("页面配置信息不存在");
		String uid = this.getUserInfo();
		JSONObject req = (JSONObject) request().getAttribute(JSON_INPUT_KEY);
		GridEditForm gridEditForm = this.gridObj.getEditForm();
		Map<String, Object> param = new HashMap<>();
		for (EditColumnBase col : gridEditForm.getEditColumnList())
			param.put(col.col, col.onGuestVal(req, uid));
		SaveHook hook = this.gridObj.getSaveHook();
		if (hook != null)
			param = hook.hook(param);
		if (param != null)
			gridEditForm.getDBObject().onUpdate(gridEditForm.primaryKey, param);
		return new NormalResponse(1, "保存成功", null);
	}

	/**
	 * /detail_column_list接口
	 *
	 * @return String 通用成功失败返回
	 */
	public AntOAApiListResponse apiDetailColumnList() throws Exception {
		init();
		String uid = this.getUserInfo();
		JSONObject req = (JSONObject) request().getAttribute(JSON_INPUT_KEY);
		String type = request().getParameter("type");
		String column = request().getParameter("col");
		if (this.gridObj.getEditForm() == null && "edit".equals(type))
			throw new Exception("页面配置信息不存在");
		if (this.gridObj.getCreateForm() == null && "create".equals(type))
			throw new Exception("页面配置信息不存在");
		if ("create".equals(type)) {
			for (CreateColumnBase columnItem : this.gridObj.getCreateForm().getCreateColumnList()) {
				if (columnItem.isColumnNeedDealApiDetailColumnList() && column.equals(columnItem.col))
					return columnItem.dealApiDetailColumnList(request(), uid);
			}
		} else if ("edit".equals(type)) {
			for (EditColumnBase columnItem : this.gridObj.getEditForm().getEditColumnList()) {
				if (columnItem.isColumnNeedDealApiDetailColumnList() && column.equals(columnItem.col))
					return columnItem.dealApiDetailColumnList(request(), uid);
			}
		} else if ("easy_row".equals(type)) {
			int index = Integer.parseInt(req.getString("index"));
			List<ListRowButtonBase> buttonList = this.gridObj.getGridList().getRowButtonList();
			if (!buttonList.get(index).isColumnNeedDealApiDetailColumnList())
				throw new Exception("该数据不存在，请检查RowButton相关配置！");
			return buttonList.get(index).dealApiDetailColumnList(request(), uid);
		}
		throw new Exception("指定字段不存在");
	}

	/**
	 * /delete 接口
	 *
	 * @return String 通用成功失败返回
	 */
	public NormalResponse apiDelete() throws Exception {
		init();
		if (this.gridObj.getGridList() == null)
			throw new Exception("页面配置信息不存在");
		if (!this.gridObj.getGridList().hasDelete)
			throw new Exception("非法操作");
		this.getUserInfo();
		DBListOperator obj = this.gridObj.getGridList().getDBObject();
		String id = request().getParameter("id");
		if (id == null)
			throw new Exception("缺少参数ID");
		Map<String, Object> resp = obj.find(Integer.parseInt(id));
		if (resp == null)
			throw new Exception("项目不存在");
		DeleteHook hook = this.gridObj.getDeleteHook();
		if (hook != null)
			id = hook.hook(id);
		if (id != null)
			obj.delete(Integer.parseInt(id));
		return new NormalResponse(1, "删除成功", null);
	}

	/**
	 * /column_change 接口
	 *
	 * @param Request request
	 * @return AntOAApiColumnChangeResponse 详见CreateOrEditColumnChangeHook
	 */
	public AntOAApiColumnChangeResponse apiColumnChange() throws Exception {
		init();
		this.getUserInfo();
		JSONObject content = (JSONObject) request().getAttribute(JSON_INPUT_KEY);
		if (content == null)
			throw new Exception("非法操作");
		List<CreateOrEditColumnChangeHook> changeHookList = new ArrayList<>();
		if (!content.containsKey("type") || !content.containsKey("form"))
			throw new Exception("非法操作");
		String type = content.getString("type");
		String col = content.getString("col");
		if ("create".equals(type)) {
			if (this.gridObj.getCreateForm() == null)
				throw new Exception("页面配置信息不存在");
			changeHookList = this.gridObj.getCreateForm().getChangeHookList();
		} else if ("edit".equals(type)) {
			if (this.gridObj.getEditForm() == null)
				throw new Exception("页面配置信息不存在");
			changeHookList = this.gridObj.getEditForm().getChangeHookList();
		} else if ("easy_row".equals(type)) {
			if (!content.containsKey("index"))
				throw new Exception("非法操作");
			int index = Integer.parseInt(content.getString("index"));
			if (this.gridObj.getGridList() == null)
				throw new Exception("页面配置信息不存在");
			List<ListRowButtonBase> buttonList = this.gridObj.getGridList().getRowButtonList();// .getChangeHookList();
			if (!(buttonList.get(index) instanceof ListRowButtonWithForm))
				throw new Exception("非法操作");
			changeHookList = ((ListRowButtonWithForm)(buttonList.get(index))).gridCreateForm.getChangeHookList();
		} else if ("easy_header".equals(type)) {
			if (!content.containsKey("index"))
				throw new Exception("非法操作");
			int index = Integer.parseInt(content.getString("index"));
			if (this.gridObj.getGridList() == null)
				throw new Exception("页面配置信息不存在");
			List<ListHeaderButtonBase> buttonList = this.gridObj.getGridList().getHeaderButtonList();// .getChangeHookList();
			if (!(buttonList.get(index) instanceof ListHeaderButtonWithForm))
				throw new Exception("非法操作");
			changeHookList = ((ListHeaderButtonWithForm)(buttonList.get(index))).gridCreateForm.getChangeHookList();
		}
		changeHookList = changeHookList.stream().filter(t -> t.col.equals(col)).collect(Collectors.toList());
		if (changeHookList.size() == 0)
			throw new Exception("页面配置信息不存在");
		CreateOrEditColumnChangeHookReturnData data = changeHookList.get(0).hook(content.getJSONObject("form"),
				content.getJSONObject("page"));
		AntOAApiColumnChangeResponse ret = new AntOAApiColumnChangeResponse();
		ret.status = 1;
		ret.data = data.data;
		ret.displayColumns = data.display;
		return ret;
	}


	/**
	 * /upload 接口
	 *
	 * @param Request request
	 * @return AntOAApiColumnChangeResponse 详见CreateOrEditColumnChangeHook
	 */
	public NormalResponse uploadFile(MultipartFile file, String type, String col) throws Exception {
		init();
		String uid = this.getUserInfo();
		String _type = type;
		String _col = col;
		if ("create".equals(_type)) {
			GridCreateForm gridCreateForm = this.gridObj.getCreateForm();
			if (gridCreateForm == null)
				throw new Exception("非法请求");
			CreateColumnBase column = null;
			for (CreateColumnBase col1 : gridCreateForm.getCreateColumnList())
				if (col1.col.equals(_col) && col1.isColumnNeedDealApiUpload())
					column = col1;
			if (column == null)
				throw new Exception("非法请求");
		} else if ("edit".equals(_type)) {
			GridEditForm gridEditForm = this.gridObj.getEditForm();
			if (gridEditForm == null)
				throw new Exception("非法请求");
			EditColumnBase column = null;
			for (EditColumnBase col1 : gridEditForm.getEditColumnList())
				if (_col.equals(col1.col))
					column = col1;
			if (column == null)
				throw new Exception("非法请求");
		} else if ("easy_header".equals(_type)) {
			List<ListHeaderButtonBase> headerList = this.gridObj.getGridList().getHeaderButtonList();
			if (!this.checkHeaderButtonHasUploadButton(headerList, "header"))
				throw new Exception("非法请求");
		} else if ("easy_row".equals(_type)) {
			List<ListRowButtonBase> headerList = this.gridObj.getGridList().getRowButtonList();
			if (!this.checkRowButtonHasUploadButton(headerList, "row"))
				throw new Exception("非法请求");
		} else
			throw new Exception("非法请求");
		String filename = file.getOriginalFilename();
		if (filename == null)
			throw new Exception("文件名获取失败");
		boolean isExtValid = false;
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		for (String ext : new String[] { "png", "jpg", "gif", "zip", "xls", "xlsx", "jpeg", "webp", "webm", "mp4",
				"mp3", "3gp", "avi", "ppt", "doc", "pptx", "docx", "txt", "rar", "7z", "csv" })
			if (extension.equals(ext))
				isExtValid = true;
		if (!isExtValid)
			throw new Exception("未知或不支持的文件扩展名：" + extension);
		String basePath = ResourceUtils.getURL("classpath:").getPath();
		if(basePath == null || basePath.contains(".jar!"))
			basePath = getBasePath();
		File destDir = new File(basePath + "/public/antoa_uploads");
		String destFile = uid + "_" + System.currentTimeMillis() + md5(filename) + "." + extension;
		if (!destDir.exists())
			destDir.mkdirs();
		file.transferTo(new File(destDir, destFile));
		return new NormalResponse(1, "/antoa_uploads/" + destFile, null);
	}
	
	private String getBasePath() {
		ApplicationHome home = new ApplicationHome(getClass());
		File jarFile = home.getSource();
		return jarFile.getParentFile().getPath();
	}
	
    private boolean checkRowButtonHasUploadButton(List<ListRowButtonBase> buttonList, String type) {
        if ("header".equals(type)) {
            for(ListRowButtonBase headerButton : buttonList)
                if (headerButton.isColumnNeedApiUpload())
                    return true;
        } else if ("row".equals(type)) {
            for(ListRowButtonBase rowButton : buttonList)
                if (rowButton.isColumnNeedApiUpload())
                    return true;
        }
        return false;
	}

	/**
     * 判断按钮列表中是否包含上传按钮
     * @param buttonList
     * @param type 类型，header与row
     * @return boolean
     */
    private boolean checkHeaderButtonHasUploadButton(List<ListHeaderButtonBase> buttonList, String type) {
        if ("header".equals(type)) {
            for(ListHeaderButtonBase headerButton : buttonList)
                if (headerButton.isColumnNeedApiUpload())
                    return true;
        } else if ("row".equals(type)) {
            for(ListHeaderButtonBase rowButton : buttonList)
                if (rowButton.isColumnNeedApiUpload())
                    return true;
        }
        return false;
    }

    /**
     * 根据UID对控制器下所有接口进行鉴权
     * @param uid 用户UID
     * @return boolean 返回真则验权通过，否则验权失败
     */
    protected abstract boolean checkPower(String uid);
    
    


	@RequestMapping(value = { "/list" }, method = RequestMethod.POST)
	public String apiListResponse() throws Exception {
		try {
			return apiList().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/grid_config" }, method = RequestMethod.POST)
	public String apiGridConfigResponse() throws Exception {
		try {
			return apiGridConfig().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/create" }, method = RequestMethod.POST)
	public String apiCreateResponse() throws Exception {
		try {
			return apiCreate().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/detail" }, method = RequestMethod.POST)
	public String apiDetailResponse() throws Exception {
		try {
			return apiDetail().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public String apiSaveResponse() throws Exception {
		try {
			return apiSave().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/detail_column_list" }, method = RequestMethod.POST)
	public String apiDetailColumnListResponse() throws Exception {
		try {
			return apiDetailColumnList().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
	public String apiDeleteResponse() throws Exception {
		try {
			return apiDelete().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/column_change" }, method = RequestMethod.POST)
	public String apiColumnChangeResponse() throws Exception {
		try {
			return apiColumnChange().toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String apiUploadFileResponse(MultipartFile file, String type, String col) throws Exception {
		try {
			return uploadFile(file, type, col).toString();
		} catch (Exception e) {
			return this.error(e);
		}
	}
}
