package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;

public class ListFilterUID extends ListFilterBase {

	private static final long serialVersionUID = 946369145841748376L;
    public String type = "ListFilterUID";

	/**
     * 构造方法
     * @param String col 列对应的字段
     */
    public ListFilterUID(String col) {
        super(col, "");
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
        gridListDbObject.where(this.col, uid);
    }
}
