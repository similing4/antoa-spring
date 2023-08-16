package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;

/**
 * ClassName: ListFilterHidden
 * 描述: 根据页面传入的参数进行筛选
 */
public class ListFilterHidden extends ListFilterBase {
	
	private static final long serialVersionUID = -5840980980223742554L;
    public String type = "ListFilterHidden";

	public ListFilterHidden(String col) {
		this(col, "");
	}
	
    public ListFilterHidden(String col, String defaultVal) {
        super(col, "", defaultVal);
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
            gridListDbObject.where(this.col, param.val);
    }
}
