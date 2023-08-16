package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnFile extends EditColumnBase {

	private static final long serialVersionUID = -6372651842938027359L;
    public String type = "EditColumnFile";

	public EditColumnFile(String col, String tip) {
		super(col, tip);
	}
	
    public EditColumnFile(String col, String tip, String defaultVal) {
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
