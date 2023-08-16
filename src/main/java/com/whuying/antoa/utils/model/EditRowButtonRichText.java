package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditRowButtonBase;

public abstract class EditRowButtonRichText extends EditRowButtonBase {

	private static final long serialVersionUID = 8257778313957402466L;
    public String type = "EditRowButtonRichText";

	public EditRowButtonRichText(String bindCol, String apiUrl, String buttonText) {
		super(bindCol, apiUrl, buttonText);
	}

	public EditRowButtonRichText(String bindCol, String apiUrl, String buttonText, String buttonType) {
		super(bindCol, apiUrl, buttonText, buttonType);
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
