package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnDisplay extends CreateColumnBase {
	
	private static final long serialVersionUID = 8712250001355215261L;

	public String type = "CreateColumnDisplay";

	public CreateColumnDisplay(String col, String tip) {
		super(col, tip);
	}

	public CreateColumnDisplay(String col, String tip, String defaultVal) {
		super(col, tip, defaultVal);
	}

	public JSONObject jsonSerialize() {
    	JSONObject ret = super.jsonSerialize();
        ret.put("type", this.type);
        return ret;
    }
	
	public String toString() {
		return this.jsonSerialize().toString();
	}
}
