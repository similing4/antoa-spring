package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnHidden extends CreateColumnBase {

	private static final long serialVersionUID = 7113528161453930035L;
    public String type = "CreateColumnHidden";

	public CreateColumnHidden(String col) {
		super(col, "");
	}
    
    public CreateColumnHidden(String col, String defaultVal) {
		super(col, "", defaultVal);
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
