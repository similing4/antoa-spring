package com.whuying.antoa.bean;

import com.alibaba.fastjson.JSONObject;

public class NormalResponse {
	public int status;
	public String msg;
	public String data;
	public NormalResponse(int status, String data, String msg){
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("status", status);
		ret.put("data", data);
		if (msg != null)
			ret.put("msg", msg);
		return ret.toString();
	}
}
