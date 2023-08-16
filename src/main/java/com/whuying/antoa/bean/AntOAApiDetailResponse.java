package com.whuying.antoa.bean;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class AntOAApiDetailResponse {
	public int status;
	public Map<String, Object> data = new HashMap<>();
	public AntOAApiDetailResponse(int status, Map<String, Object> data){
		this.status = status;
		this.data = data;
	}
	
	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("status", status);
		ret.put("data", data);
		return ret.toString();
	}
}
