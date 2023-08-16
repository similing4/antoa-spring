package com.whuying.antoa.utils.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.bean.AntOAApiListResponse;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonBase;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;
import com.whuying.antoa.utils.hook.ListHook;

/**
 * ClassName: ListRowButtonWithForm
 * 描述: 列表页每行调用API按钮实体类（要求确定后才会请求）
 * 注：被调用的API返回值应为JSON格式且包含status字段，status字段为1时客户端成功展示data字段，否则客户端展示msg字段
 */
public abstract class ListRowButtonWithForm extends ListRowButtonBase {
	private static final long serialVersionUID = -1756937009763260514L;
	
	public GridCreateFormEasy gridCreateForm;
    public String type = "ListRowButtonWithForm";

    /**
     * ListRowButtonWithForm constructor.
     * 
     * @param String baseUrl
     * @param String buttonText
     * @param GridCreateFormEasy gridCreateForm
     */
    public ListRowButtonWithForm(String baseUrl, String buttonText, GridCreateFormEasy gridCreateForm) {
    	this(baseUrl, buttonText, gridCreateForm, "");
    }
    
    /**
     * ListRowButtonWithForm constructor.
     * 
     * @param String baseUrl
     * @param String buttonText
     * @param GridCreateFormEasy gridCreateForm
     * @param String buttonType
     */
    public ListRowButtonWithForm(String baseUrl, String buttonText, GridCreateFormEasy gridCreateForm, String buttonType) {
        super(baseUrl, buttonText, buttonType);
        this.gridCreateForm = gridCreateForm;
    }

    @Override
    public JSONObject jsonSerialize() {
    	JSONObject ret = super.jsonSerialize();
    	ret.put("type"           , this.type);
    	ret.put("gridCreateForm" , this.gridCreateForm);
    	return ret;
    }

    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }
    

    /**
     * 是否需要使用ApiDetailColumnList接口（接口中type为edit时才会调用）
     * @return boolean 是否需要
     */
    @Override
    public boolean isColumnNeedDealApiDetailColumnList() {
        return true;
    }
    
    private String readJSONFromRequest(HttpServletRequest request) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		return sb.toString();
	}

    /**
     * @param HttpServletRequest request 请求数据
     * @param String uid 登录用户UID
     * @return String 返回给前端的json数据
     * @throws Exception 
     */
    @Override
    public AntOAApiListResponse dealApiDetailColumnList(HttpServletRequest request, String uid) throws Exception {
    	JSONObject req = JSONObject.parseObject(readJSONFromRequest(request));
        String column = request.getParameter("col");
        String vModelVal = request.getParameter("val");
        for(CreateColumnBase columnItemBase : this.gridCreateForm.getCreateColumnList()) {
            if (columnItemBase.isColumnNeedDealApiDetailColumnList() && column != null && column.equals(columnItemBase.col) && columnItemBase instanceof CreateColumnChildrenChoose) {
            	CreateColumnChildrenChoose columnItem = (CreateColumnChildrenChoose) columnItemBase;
                List<UrlParamCalculatorParamItem> pageParams = new ArrayList<>();
                for(Entry<String, Object> set : req.entrySet())
                    pageParams.add(new UrlParamCalculatorParamItem(set.getKey(), set.getValue()));
                UrlParamCalculator urlParamCalculator = new UrlParamCalculator(pageParams);
                GridListEasy gridList = columnItem.gridListEasy;
                DBListOperator gridListDbObject = gridList.getDBObject().doClone();
                for(ListFilterBase r : gridList.getFilterList())
                    r.onFilter(gridListDbObject, urlParamCalculator, uid);
                String vModelValTip;
                Map<String, Object> vModelValTipRow = gridListDbObject.doClone().where(columnItem.gridListVModelCol, vModelVal).first();
                if (vModelValTipRow == null)
                    vModelValTip = "";
                else
                    vModelValTip = vModelValTipRow.get(columnItem.gridListDisplayCol) + "";
                List<String> columns = new ArrayList<>();
                for(ListTableColumnBase column1 : gridList.getTableColumnList()) {
                    if ((column1 instanceof ListTableColumnDisplay) || (column1 instanceof ListTableColumnRichDisplay))
                        continue;
                    columns.add(column1.col);
                }
                AntOAApiListResponse res = new AntOAApiListResponse(gridListDbObject.select(columns.toArray(new String[columns.size()])).paginate(8));
                for(Map<String, Object> searchResultItem : res.data) {
                	List<Boolean> BUTTON_CONDITION_DATA = new ArrayList<>();
                	List<String> BUTTON_FINAL_URL_DATA = new ArrayList<>();
                    List<UrlParamCalculatorParamItem> searchResultParams = new ArrayList<>();
                    for(ListTableColumnBase column1 : gridList.getTableColumnList()) {
                        if (column1 instanceof ListTableColumnDisplay || column1 instanceof ListTableColumnRichDisplay)
                            searchResultItem.put(column1.col, "");
                        else
                            column1.onParse(searchResultItem, urlParamCalculator, uid);
                        searchResultParams.add(new UrlParamCalculatorParamItem(column1.col, searchResultItem.get(column1.col)));
                    }
                    UrlParamCalculator rowParamCalculator = new UrlParamCalculator(pageParams, searchResultParams);
                    for(ListRowButtonBase rowButtonItem : gridList.getRowButtonList()) {
                    	BUTTON_FINAL_URL_DATA.add(rowButtonItem.calcButtonFinalUrl(rowParamCalculator));
                        BUTTON_CONDITION_DATA.add(rowButtonItem.judgeIsShow(rowParamCalculator));
                    }
                    searchResultItem.put("BUTTON_FINAL_URL_DATA", BUTTON_FINAL_URL_DATA);
                    searchResultItem.put("BUTTON_CONDITION_DATA", BUTTON_CONDITION_DATA);
                }
                res.status = 1;
                res.vModelValTip = vModelValTip;
                ListHook hook = columnItem.getHook();
                if (hook != null)
                    return hook.hook(res);
                return res;
            }
        }
        throw new Exception("未知错误");
    }

    /**
     * 是否需要使用ApiUpload接口
     * @return boolean 是否需要
     */
    public boolean isColumnNeedApiUpload() {
        for(CreateColumnBase column : this.gridCreateForm.getCreateColumnList()) {
            if (column.isColumnNeedDealApiUpload())
                return true;
        }
        return false;
    }
}
