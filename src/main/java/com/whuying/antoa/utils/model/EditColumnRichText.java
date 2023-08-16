package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnRichText extends EditColumnBase {

	private static final long serialVersionUID = -2559055162802375278L;
    public String type = "EditColumnRichText";

	public EditColumnRichText(String col, String tip) {
		super(col, tip);
	}
	
    public EditColumnRichText(String col, String tip, String defaultVal) {
		super(col, tip, defaultVal);
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
