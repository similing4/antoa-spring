package com.whuying.antoa.bean;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;

public class RoleLimitCallback {
	private String role;

	public RoleLimitCallback(String role) {
		this.role = role;
	}

	public boolean call(Map<String, Object> user) {
		return JSONArray.parseArray(user.get("role") + "").contains(role);
	}
}
