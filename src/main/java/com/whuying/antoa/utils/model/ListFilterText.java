package com.whuying.antoa.utils.model;

import org.jooq.impl.DSL;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;

/**
 * ClassName: ListFilterText
 * 描述: 根据输入的文本对指定字段进行筛选
 */
public class ListFilterText extends ListFilterBase {

	private static final long serialVersionUID = 5400330764949020726L;
    public String type = "ListFilterText";

	public ListFilterText(String col, String tip) {
		super(col, tip);
		// TODO Auto-generated constructor stub
	}
	
	public ListFilterText(String col, String tip, String defaultVal) {
		super(col, tip, defaultVal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject jsonSerialize() {
		JSONObject ret = super.jsonSerialize();
		ret.put("type", this.type);
		return ret;
	}

	@Override
	public String toString() {
		return this.jsonSerialize().toString();
	}
	
    @Override
	public void onFilter(DBListOperator gridListDbObject, UrlParamCalculator urlParamCalculator, String uid) {
    	UrlParamCalculatorParamItem param = urlParamCalculator.getPageParamByKey(this.col);
        if (param != null && !"".equals(param.val + ""))
            gridListDbObject.where(this.col, "like", DSL.concat(DSL.inline("%"), DSL.value(param.val), DSL.inline("%")));
    }
}
