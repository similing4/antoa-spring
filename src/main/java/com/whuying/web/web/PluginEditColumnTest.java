package com.whuying.web.web;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class PluginEditColumnTest extends EditColumnBase {
	
	private static final long serialVersionUID = -8937185006090287881L;
	public String type = "PluginEditColumnTest";
	public PluginEditColumnTest(String col, String tip) {
		super(col, tip);
	}
    public PluginEditColumnTest(String col, String tip, String defaultVal) {
		super(col, tip, defaultVal);
	}
    
    public Object onGuestVal(JSONObject req, String uid){
        return req.get(this.col);
    }

    public Object onServerVal(JSONObject res, String uid){
        return res.get(this.col);
    }
}