package com.whuying.antoa.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

public class RoleLimitCallback {
	private String role;

	public RoleLimitCallback(String role) {
		this.role = role;
	}

	public boolean call(Map<String, Object> user) {
		List<String> roleIds = new ArrayList<>();
		String rolesJSONString = user.get("role") + "";
		JSONArray arr = JSONArray.parseArray(rolesJSONString);
		for(int i=0;i<arr.size();i++)
			roleIds.add(arr.get(i) + "");
		return roleIds.contains(role);
	}
}
