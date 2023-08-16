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
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonBase;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;
import com.whuying.antoa.utils.hook.ListHook;

public abstract class ListHeaderButtonWithForm extends ListHeaderButtonBase {
    
    private static final long serialVersionUID = -7535070651688180610L;
    public GridCreateFormEasy gridCreateForm;
    public String type = "ListHeaderButtonWithForm";

    public ListHeaderButtonWithForm(String baseUrl, String buttonText, GridCreateFormEasy gridCreateForm) {
        this(baseUrl, buttonText, gridCreateForm, "primary");
    }
    
    /**
     * ListHeaderButtonWithForm constructor.
     * @param String baseUrl
     * @param String buttonText
     * @param GridCreateFormEasy gridCreateForm
     * @param String buttonType
     */
    public ListHeaderButtonWithForm(String baseUrl, String buttonText, GridCreateFormEasy gridCreateForm, String buttonType) {
        super(baseUrl, buttonText, buttonType);
        this.gridCreateForm = gridCreateForm;
    }
    

	@Override
	public JSONObject jsonSerialize() {
		JSONObject ret = super.jsonSerialize();
		ret.put("type", this.type);
		ret.put("gridCreateForm", this.gridCreateForm);
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

	public String readJSONFromRequest(HttpServletRequest request) throws Exception {
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
     * @return AntOAApiListResponse 返回给前端的json数据
     * @throws Exception 
     */
    @Override
    public AntOAApiListResponse dealApiDetailColumnList(HttpServletRequest request, String uid) throws Exception{
    	JSONObject req = JSONObject.parseObject(readJSONFromRequest(request));
        String column = request.getParameter("col");
        String vModelVal = request.getParameter("val");
        for (CreateColumnBase columnItem1 : this.gridCreateForm.getCreateColumnList()) {
            if (columnItem1.isColumnNeedDealApiDetailColumnList() && column != null && column.equals(columnItem1.col) && columnItem1 instanceof CreateColumnChildrenChoose) {
            	CreateColumnChildrenChoose columnItem = (CreateColumnChildrenChoose) columnItem1;
                List<UrlParamCalculatorParamItem> pageParams = new ArrayList<>();
                for (Entry<String, Object> entrySet : req.entrySet())
                    pageParams.add(new UrlParamCalculatorParamItem(entrySet.getKey(), entrySet.getValue()));
                UrlParamCalculator urlParamCalculator = new UrlParamCalculator(pageParams);
                GridListEasy gridList = columnItem.gridListEasy;
                DBListOperator gridListDbObject = gridList.getDBObject().doClone();
                for(ListFilterBase r : gridList.getFilterList())
                    r.onFilter(gridListDbObject, urlParamCalculator, uid);
                Map<String, Object> vModelValTipRow = gridListDbObject.doClone().where(columnItem.gridListVModelCol, vModelVal).first();
                String vModelValTip;
				if (vModelValTipRow == null)
                    vModelValTip = "";
                else
                    vModelValTip = vModelValTipRow.get(columnItem.gridListDisplayCol) + "";
                List<String> columns = new ArrayList<String>();
                for(ListTableColumnBase column1 : gridList.getTableColumnList()) {
                    if ((column1 instanceof ListTableColumnDisplay) || (column1 instanceof ListTableColumnRichDisplay))
                        continue;
                    columns.add(column1.col);
                }
                AntOAApiListResponse res = new AntOAApiListResponse(gridListDbObject.select(columns.toArray(new String[columns.size()])).paginate(8));
                for (Map<String, Object> searchResultItem : res.data) {
                	List<Boolean> BUTTON_CONDITION_DATA = new ArrayList<>();
                	List<String> BUTTON_FINAL_URL_DATA = new ArrayList<>();
                	List<UrlParamCalculatorParamItem> searchResultParams = new ArrayList<>();
                    for (ListTableColumnBase column1 : gridList.getTableColumnList()) {
                        if (column1 instanceof ListTableColumnDisplay || column1 instanceof ListTableColumnRichDisplay)
                        	searchResultItem.put(column1.col, "");
                        else
                            column1.onParse(searchResultItem, urlParamCalculator, uid);
                        searchResultParams.add(new UrlParamCalculatorParamItem(column1.col, searchResultItem.get(column1.col)));
                    }
                    UrlParamCalculator rowParamCalculator = new UrlParamCalculator(pageParams, searchResultParams);
                    for (ListRowButtonBase rowButtonItem : gridList.getRowButtonList()) {
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
    @Override
    public boolean isColumnNeedApiUpload() {
        for (CreateColumnBase column : this.gridCreateForm.getCreateColumnList()) {
            if (column.isColumnNeedDealApiUpload())
                return true;
        }
        return false;
    }
}
