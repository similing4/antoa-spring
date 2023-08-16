package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnFiles extends EditColumnBase {

	private static final long serialVersionUID = 41254198411987266L;
    public String type = "EditColumnFiles";

	public EditColumnFiles(String col, String tip) {
		super(col, tip);
	}
	
    public EditColumnFiles(String col, String tip, String defaultVal) {
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
