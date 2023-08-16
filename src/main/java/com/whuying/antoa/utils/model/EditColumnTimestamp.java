package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnTimestamp extends EditColumnBase {

	private static final long serialVersionUID = 2023902995529271796L;
    public String type = "EditColumnTimestamp";

	public EditColumnTimestamp(String col, String tip) {
		super(col, tip);
		// TODO Auto-generated constructor stub
	}

	public EditColumnTimestamp(String col, String tip, String defaultVal) {
		super(col, tip, defaultVal);
		// TODO Auto-generated constructor stub
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
