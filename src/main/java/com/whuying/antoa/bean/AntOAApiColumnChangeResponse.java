package com.whuying.antoa.bean;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class AntOAApiColumnChangeResponse {
	public int status;
	public Map<String, Object> data;
	public List<String> displayColumns;
	
	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("status", status);
		ret.put("data", data);
		ret.put("displayColumns", displayColumns);
		return ret.toString();
	}
}
