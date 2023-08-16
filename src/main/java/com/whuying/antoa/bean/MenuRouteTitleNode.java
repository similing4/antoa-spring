package com.whuying.antoa.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class MenuRouteTitleNode implements Serializable {
	
	private static final long serialVersionUID = -803670643659302146L;
	public String path = "";
	public String name = "";
	
	public MenuRouteTitleNode(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	public MenuRouteTitleNode(MenuRouteTreeNode node) {
		this.name = node.name;
		this.path = node.path;
	}
	
	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("path", path);
		ret.put("name", name);
		return ret.toString();
	}
}
