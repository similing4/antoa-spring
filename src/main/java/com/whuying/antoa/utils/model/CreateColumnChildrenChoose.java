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

public class CreateColumnChildrenChoose extends CreateColumnBase {
	private static final long serialVersionUID = 7106121090726101137L;
	/**
     * 用于选择的GridList实例
     */
    public GridListEasy gridListEasy;
    /**
     * 选中列表项后需要作为表单值的列表列（如列表为select uid,username from user，那么此值为uid时将会将对应选中行的uid值作为对应表单值传给后端）
     */
    public String gridListVModelCol;
    /**
     * 选中列表项后展示在表单中的值对应的列表列（如列表为select uid,username from user，那么此值为username时将会将对应选中行的username值展示在表单上）
     */
    public String gridListDisplayCol;
    /**
     * 查询结果钩子，需要手动设置
     */
    public ListHook hook;

    public String type = "CreateColumnChildrenChoose";

    /**
     * CreateColumnChildrenChoose constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param GridListEasy gridListEasy 用于选择的GridList实例
     * @param String gridListVModelCol 选中列表项后需要作为表单值的列表列（如列表为select uid,username from user，那么此值为uid时将会将对应选中行的uid值作为对应表单值传给后端）
     * @param String gridListDisplayCol 选中列表项后展示在表单中的值对应的列表列（如列表为select uid,username from user，那么此值为username时将会将对应选中行的username值展示在表单上）
     */
    public CreateColumnChildrenChoose(String col, String tip, GridListEasy gridListEasy, String gridListVModelCol, String gridListDisplayCol) {
    	this(col, tip, gridListEasy, gridListVModelCol, gridListDisplayCol, "");
    }
    
    /**
     * CreateColumnChildrenChoose constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param GridListEasy gridListEasy 用于选择的GridList实例
     * @param String gridListVModelCol 选中列表项后需要作为表单值的列表列（如列表为select uid,username from user，那么此值为uid时将会将对应选中行的uid值作为对应表单值传给后端）
     * @param String gridListDisplayCol 选中列表项后展示在表单中的值对应的列表列（如列表为select uid,username from user，那么此值为username时将会将对应选中行的username值展示在表单上）
     * @param String defaultVal 默认值
     */
    public CreateColumnChildrenChoose(String col, String tip, GridListEasy gridListEasy, String gridListVModelCol, String gridListDisplayCol, String defaultVal) {
        super(col, tip, defaultVal);
        this.gridListEasy = gridListEasy;
        this.gridListVModelCol = gridListVModelCol;
        this.gridListDisplayCol = gridListDisplayCol;
    }

    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }
    
    public JSONObject jsonSerialize() {
    	JSONObject ret = super.jsonSerialize();
    	ret.put("type" , this.type);
    	ret.put("gridListEasy" , gridListEasy);
    	ret.put("gridListVModelCol" , gridListVModelCol);
    	ret.put("gridListDisplayCol" , gridListDisplayCol);
    	return ret;
    }

    /**
     * @param ListHook hook 设置查询结果钩子
     */
    public void setHook(ListHook hook) {
        this.hook = hook;
    }

    /**
     * @return ListHook hook 获取查询结果钩子
     */
    public ListHook getHook() {
        return this.hook;
    }

    /**
     * 是否需要使用ApiDetailColumnList接口
     * @return boolean 是否需要
     */
    @Override
    public boolean isColumnNeedDealApiDetailColumnList(){
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
     * @param Request request 请求数据
     * @param string uid 登录用户UID
     * @return string 返回给前端的json数据
     * @throws Exception 
     */
    public AntOAApiListResponse dealApiDetailColumnList(HttpServletRequest request, String uid) throws Exception{
        String vModelVal = request.getParameter("val");
        JSONObject req = JSONObject.parseObject(readJSONFromRequest(request));
        List<UrlParamCalculatorParamItem> pageParams = new ArrayList<>();
        for(Entry<String, Object> p : req.entrySet())
            pageParams.add(new UrlParamCalculatorParamItem(p.getKey(), p.getValue()));
        UrlParamCalculator urlParamCalculator = new UrlParamCalculator(pageParams);
        GridListEasy gridList = this.gridListEasy;
        DBListOperator gridListDbObject = gridList.getDBObject().doClone();
        for(ListFilterBase r : gridList.getFilterList())
            r.onFilter(gridListDbObject, urlParamCalculator, uid);
        Map<String, Object> vModelValTipRow = gridListDbObject.doClone().where(this.gridListVModelCol, vModelVal).first();
        String vModelValTip;
        if (vModelValTipRow == null)
            vModelValTip = "";
        else
            vModelValTip = vModelValTipRow.get(this.gridListDisplayCol) + "";
        List<String> columns = new ArrayList<>();
        for(ListTableColumnBase column : gridList.getTableColumnList())
            if (!column.isTypeDisplay())
                columns.add(column.col);
        AntOAApiListResponse res = new AntOAApiListResponse(gridListDbObject.select(columns.toArray(new String[columns.size()])).paginate(8));
        for(Map<String, Object> searchResultItem : res.data) {
        	List<Boolean> BUTTON_CONDITION_DATA = new ArrayList<>();
        	List<String> BUTTON_FINAL_URL_DATA = new ArrayList<>();
            List<UrlParamCalculatorParamItem> searchResultParams = new ArrayList<>();
            for(ListTableColumnBase column : gridList.getTableColumnList()) {
                if (column.isTypeDisplay())
                    searchResultItem.put(column.col, "");
                else
                    column.onParse(searchResultItem, urlParamCalculator, uid);
                searchResultParams.add(new UrlParamCalculatorParamItem(column.col, searchResultItem.get(column.col)));
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
        hook = this.getHook();
        if (hook != null)
            return hook.hook(res);
        return res;
    }
}
