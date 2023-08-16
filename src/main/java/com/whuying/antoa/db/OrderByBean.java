package com.whuying.antoa.db;

public class OrderByBean {
	public static enum OrderByType {
		ASC,
		DESC
	}
	public String column;
	public OrderByType type;
	public OrderByBean(String column, OrderByType type) {
		this.column = column;
		this.type = type;
	}
}
