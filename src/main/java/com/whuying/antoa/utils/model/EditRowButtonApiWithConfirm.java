package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditRowButtonBase;

public abstract class EditRowButtonApiWithConfirm extends EditRowButtonBase {

	private static final long serialVersionUID = -4367987248741752283L;
    public String type = "EditRowButtonApiWithConfirm";

	public EditRowButtonApiWithConfirm(String bindCol, String apiUrl, String buttonText) {
		super(bindCol, apiUrl, buttonText);
		// TODO Auto-generated constructor stub
	}
	public EditRowButtonApiWithConfirm(String bindCol, String apiUrl, String buttonText, String buttonType) {
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

