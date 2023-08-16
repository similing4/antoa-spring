package com.whuying.antoa.bean;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.GridCreateForm;
import com.whuying.antoa.utils.GridEditForm;
import com.whuying.antoa.utils.GridList;

public class AntOAApiGridConfigResponse {
	public static class AntOAApiGridConfigResponseGrid {
		public GridList list;
		public GridCreateForm create;
		public GridEditForm edit;
	}

	public int status;
	public AntOAApiGridConfigResponseGrid grid;
	public CustomParamResponse.Api api;

	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("status", status);
		ret.put("api", api);
		JSONObject grid = new JSONObject();
		grid.put("list", null);
		grid.put("create", null);
		grid.put("edit", null);
		if (this.grid.list != null)
			grid.put("list", JSONObject.parse(this.grid.list.toString()));
		if (this.grid.create != null)
			grid.put("create", JSONObject.parse(this.grid.create.toString()));
		if (this.grid.edit != null)
			grid.put("edit", JSONObject.parse(this.grid.edit.toString()));
		ret.put("grid", grid);
		return ret.toString();
	}
}