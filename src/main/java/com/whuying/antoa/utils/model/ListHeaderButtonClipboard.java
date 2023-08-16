package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;

public abstract class ListHeaderButtonClipboard extends ListHeaderButtonBase {

	private static final long serialVersionUID = -2623981776130467882L;
    public String type = "ListHeaderButtonClipboard";

	public ListHeaderButtonClipboard(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
	}
	
    public ListHeaderButtonClipboard(String baseUrl, String buttonText, String buttonType) {
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
