package com.whuying.antoa.bean;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.Grid;

public class CustomParamResponse {
	public static class Api {
		public String path;
		public String list;
		public String create;
		public String detail;
		public String save;
		public String delete;
		public String detail_column_list;
		public String api_column_change;
		public String api_upload;
		public String list_page;
		public String create_page;
		public String edit_page;
	}
	public Grid grid;
	public Api api;
	
	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("grid", grid);
		ret.put("api", api);
		return ret.toString();
	}
}
