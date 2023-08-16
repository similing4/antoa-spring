package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnPictures extends CreateColumnBase {

	private static final long serialVersionUID = 4682366435607490138L;
    public String type = "CreateColumnPictures";

	public CreateColumnPictures(String col, String tip) {
		super(col, tip);
	}
    
    public CreateColumnPictures(String col, String tip, String defaultVal) {
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
