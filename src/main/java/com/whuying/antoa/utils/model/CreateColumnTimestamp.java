package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnTimestamp extends CreateColumnBase {

	private static final long serialVersionUID = 3996465781782270973L;
    public String type = "CreateColumnTimestamp";

	public CreateColumnTimestamp(String col, String tip) {
		super(col, tip);
	}
    
    public CreateColumnTimestamp(String col, String tip, String defaultVal) {
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
