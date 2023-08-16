package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;

public class ListFilterEndTime extends ListFilterBase {

	private static final long serialVersionUID = 4576371054231528067L;
    public String type = "ListFilterEndTime";

	public ListFilterEndTime(String col, String tip) {
		super(col, tip);
		// TODO Auto-generated constructor stub
	}
	
	public ListFilterEndTime(String col, String tip, String defaultVal) {
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
        UrlParamCalculatorParamItem param = urlParamCalculator.getPageParamByKey(this.col + "_endtime");
        if (param != null && !"".equals(param.val + ""))
            gridListDbObject.where(this.col, "<", param.val);
	}
}
