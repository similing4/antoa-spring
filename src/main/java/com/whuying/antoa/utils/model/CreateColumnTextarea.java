package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnTextarea extends CreateColumnBase {

	private static final long serialVersionUID = -4507074458695819021L;
    public String type = "CreateColumnTextarea";

	public CreateColumnTextarea(String col, String tip) {
		super(col, tip);
	}
    
    public CreateColumnTextarea(String col, String tip, String defaultVal) {
		super(col, tip, defaultVal);
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
