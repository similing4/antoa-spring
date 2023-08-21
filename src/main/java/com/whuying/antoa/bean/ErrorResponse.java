package com.whuying.antoa.bean;

import com.alibaba.fastjson.JSONObject;

public class ErrorResponse {
	public int status;
	public String msg;
	public String data;

	public ErrorResponse(int status, String msg, String data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public ErrorResponse(Exception e) {
		this.status = 0;
		this.msg = e.getMessage();
		this.data = null;
	}

	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("status", status);
		ret.put("msg", msg);
		if (data != null)
			ret.put("data", data);
		return ret.toString();
	}
}
