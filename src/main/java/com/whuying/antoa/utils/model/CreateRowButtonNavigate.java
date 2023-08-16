package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateRowButtonBase;

public abstract class CreateRowButtonNavigate extends CreateRowButtonBase {

	private static final long serialVersionUID = -3264695985335059135L;
    public String type = "CreateRowButtonNavigate";

	public CreateRowButtonNavigate(String bindCol, String apiUrl, String buttonText) {
		super(bindCol, apiUrl, buttonText);
	}

	public CreateRowButtonNavigate(String bindCol, String apiUrl, String buttonText, String buttonType) {
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
