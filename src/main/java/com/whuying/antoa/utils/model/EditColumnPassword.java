package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnPassword extends EditColumnBase {

	private static final long serialVersionUID = 7414713858498740663L;
    public String type = "EditColumnPassword";

	public EditColumnPassword(String col, String tip) {
		super(col, tip);
	}
	
    public EditColumnPassword(String col, String tip, String defaultVal) {
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

    /**
     * 当服务端数据传出时
     * @param JSONObject res
     * @param String uid
     * @return Object 返回需要接下来
     */
    @Override
    public Object onServerVal(JSONObject res, String uid){
        return "";
    }
}
