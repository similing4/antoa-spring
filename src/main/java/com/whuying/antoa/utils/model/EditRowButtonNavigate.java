package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditRowButtonBase;

public abstract class EditRowButtonNavigate extends EditRowButtonBase {
	
	private static final long serialVersionUID = 8640504410793428030L;
    public String type = "EditRowButtonNavigate";

	public EditRowButtonNavigate(String bindCol, String apiUrl, String buttonText) {
		super(bindCol, apiUrl, buttonText);
		// TODO Auto-generated constructor stub
	}
	public EditRowButtonNavigate(String bindCol, String apiUrl, String buttonText, String buttonType) {
		super(bindCol, apiUrl, buttonText, buttonType);
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
}
