package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnHidden extends EditColumnBase {

	private static final long serialVersionUID = -6801421341869717867L;
    public String type = "EditColumnHidden";

	/**
     * 构造方法
     * @param String col 列对应的字段
     */
    public EditColumnHidden(String col) {
        super(col, "");
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
