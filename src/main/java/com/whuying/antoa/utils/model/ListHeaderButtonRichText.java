package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;

public abstract class ListHeaderButtonRichText extends ListHeaderButtonBase {

	private static final long serialVersionUID = 2938295758734099832L;
    public String type = "ListHeaderButtonRichText";

	public ListHeaderButtonRichText(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
	}
	
    public ListHeaderButtonRichText(String baseUrl, String buttonText, String buttonType) {
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
