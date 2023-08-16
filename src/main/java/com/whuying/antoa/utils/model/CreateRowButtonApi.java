package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateRowButtonBase;

public abstract class CreateRowButtonApi extends CreateRowButtonBase {

	private static final long serialVersionUID = -4507074458695819021L;
    public String type = "CreateRowButtonApi";

	public CreateRowButtonApi(String bindCol, String apiUrl, String buttonText) {
		super(bindCol, apiUrl, buttonText);
	}

	public CreateRowButtonApi(String bindCol, String apiUrl, String buttonText, String buttonType) {
		super(bindCol, apiUrl, buttonText, buttonType);
	}
    
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
