package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

public class ListTableColumnHidden extends ListTableColumnBase {

	private static final long serialVersionUID = -7681490227315523470L;
	public String type = "ListTableColumnHidden";

	/**
     * 构造方法
     * @param String col 列对应的字段
     */
    public ListTableColumnHidden(String col) {
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
