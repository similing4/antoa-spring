package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;

public abstract class ListHeaderButtonApiWithConfirm extends ListHeaderButtonBase {

	private static final long serialVersionUID = 1680119545590454981L;
    public String type = "ListHeaderButtonApiWithConfirm";

	public ListHeaderButtonApiWithConfirm(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
	}
	
    public ListHeaderButtonApiWithConfirm(String baseUrl, String buttonText, String buttonType) {
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
