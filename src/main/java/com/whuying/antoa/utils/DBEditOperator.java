package com.whuying.antoa.utils;

import java.util.Map;

import com.whuying.antoa.db.DB;

public abstract class DBEditOperator {
	public DB builder;

    public DBEditOperator(DB builder) {
        this.builder = builder;
    }

    public Map<String, Object> find(String id) {
		return this.builder.where("id", id).first();
    }

    public int onUpdate(String primaryKey, Map<String, Object> param) {
        return this.builder.where(primaryKey, param.get(primaryKey)).update(param);
    }
}
