package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnPicture extends CreateColumnBase {
	private static final long serialVersionUID = 665423411357141411L;
    public String type = "CreateColumnPicture";

	public CreateColumnPicture(String col, String tip) {
		super(col, tip);
	}
    
    public CreateColumnPicture(String col, String tip, String defaultVal) {
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
