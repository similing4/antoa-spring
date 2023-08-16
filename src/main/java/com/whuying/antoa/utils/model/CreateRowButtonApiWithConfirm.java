package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateRowButtonBase;

public abstract class CreateRowButtonApiWithConfirm extends CreateRowButtonBase {
	
	private static final long serialVersionUID = 7263838699126339060L;
    public String type = "CreateRowButtonApiWithConfirm";

	public CreateRowButtonApiWithConfirm(String bindCol, String apiUrl, String buttonText) {
		super(bindCol, apiUrl, buttonText);
	}

	public CreateRowButtonApiWithConfirm(String bindCol, String apiUrl, String buttonText, String buttonType) {
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
