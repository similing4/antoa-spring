package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnRichText extends CreateColumnBase {

	private static final long serialVersionUID = 1127095107773435520L;
    public String type = "CreateColumnRichText";

	public CreateColumnRichText(String col, String tip) {
		super(col, tip);
	}
	
    public CreateColumnRichText(String col, String tip, String defaultVal) {
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
