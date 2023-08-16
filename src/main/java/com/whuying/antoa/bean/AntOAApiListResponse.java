package com.whuying.antoa.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.db.PaginateResult;

public class AntOAApiListResponse {
	public List<Map<String, Object>> data = new ArrayList<>();
	public int current_page;
	public long last_page;
	public int per_page;
	public int to;
	public long total;
	public String path;
	public String first_page_url;
	public String last_page_url;
	public String next_page_url;
	public String prev_page_url;

	public int status;
	public String statistic;
	public String vModelValTip;

	public AntOAApiListResponse(PaginateResult paginateResult) {
		this.data = paginateResult.data;
		this.current_page = paginateResult.current_page;
		this.last_page = paginateResult.last_page;
		this.per_page = paginateResult.per_page;
		this.to = paginateResult.to;
		this.total = paginateResult.total;
		this.path = paginateResult.path;
		this.first_page_url = paginateResult.first_page_url;
		this.last_page_url = paginateResult.last_page_url;
		this.next_page_url = paginateResult.next_page_url;
		this.prev_page_url = paginateResult.prev_page_url;
	}

	/**
	 * 序列化对象
	 * 
	 * @return 序列化后的JSON
	 */
	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("data", data);
		ret.put("current_page", current_page);
		ret.put("last_page", last_page);
		ret.put("per_page", per_page);
		ret.put("to", to);
		ret.put("total", total);
		ret.put("path", path);
		ret.put("first_page_url", first_page_url);
		ret.put("last_page_url", last_page_url);
		ret.put("next_page_url", next_page_url);
		ret.put("prev_page_url", prev_page_url);
		ret.put("status", status);
		ret.put("statistic", statistic);
		ret.put("vModelValTip", vModelValTip);
		return ret.toString();
	}
}
