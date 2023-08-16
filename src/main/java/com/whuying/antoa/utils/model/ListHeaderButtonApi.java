package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;

public abstract class ListHeaderButtonApi extends ListHeaderButtonBase {

	private static final long serialVersionUID = 2176907064595264145L;
    public String type = "ListHeaderButtonApi";

	public ListHeaderButtonApi(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
	}
	
    public ListHeaderButtonApi(String baseUrl, String buttonText, String buttonType) {
		super(baseUrl, buttonText, buttonType);
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
}
