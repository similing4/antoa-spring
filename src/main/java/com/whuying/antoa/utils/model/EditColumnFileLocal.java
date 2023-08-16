package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnFileLocal extends EditColumnBase {

	private static final long serialVersionUID = -6148711806607013807L;
    public String type = "EditColumnFileLocal";

	public EditColumnFileLocal(String col, String tip) {
		super(col, tip);
	}
	
    public EditColumnFileLocal(String col, String tip, String defaultVal) {
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
     * 是否需要使用ApiUpload接口（接口中type为edit时才会调用）
     * @return boolean 是否需要
     */
    @Override
    public boolean isColumnNeedDealApiUpload(){
        return true;
    }
}
