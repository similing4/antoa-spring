package com.whuying.antoa.utils;

import java.util.Map;

import com.whuying.antoa.db.DB;

public abstract class DBCreateOperator {
    public DB builder;

    public DBCreateOperator(DB builder) {
        this.builder = builder;
    }

    public int insert(Map<String, Object> values) {
        return this.builder.insert(values);
    }
}
