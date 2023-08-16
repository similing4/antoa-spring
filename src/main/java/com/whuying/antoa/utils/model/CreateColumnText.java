package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnText extends CreateColumnBase {

	private static final long serialVersionUID = -69425083823902279L;
    public String type = "CreateColumnText";

	public CreateColumnText(String col, String tip) {
		super(col, tip);
	}
    
    public CreateColumnText(String col, String tip, String defaultVal) {
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
