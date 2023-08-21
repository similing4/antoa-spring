package com.whuying.antoa.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

public class MenuRouteTreeNode implements Serializable, ObjectDeserializer {

	private static final long serialVersionUID = -4813030815581330240L;
	
	public String path = "";
	public String name = "";
	public boolean visible = true;
	@JSONField(name = "isHome")
	public boolean home = false;
	public RoleLimitCallback role_limit = null;
	public List<MenuRouteTreeNode> children = new ArrayList<>();
	private int role_limit_id = -1;
	
	public MenuRouteTreeNode clone() {
		MenuRouteTreeNode ret = new MenuRouteTreeNode();
		ret.setPath(getPath());
		ret.setName(getName());
		ret.setVisible(isVisible());
		ret.setHome(isHome());
		ret.setRole_limit(getRole_limit());
		ret.setRole_limit_id(getRole_limit_id());
		List<MenuRouteTreeNode> new_children = new ArrayList<>();
		for(MenuRouteTreeNode item : getChildren())
			new_children.add(item.clone());
		ret.setChildren(new_children);
		return ret;
	}
	
	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		ret.put("path", path);
		ret.put("name", name);
		ret.put("visible", visible);
		ret.put("isHome", home);
		ret.put("children", children);
		return ret.toString();
	}

	@Override
	public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFastMatchToken() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setHome(boolean isHome) {
		this.home = isHome;
	}

	public void setChildren(List<MenuRouteTreeNode> children) {
		this.children = children;
	}

	public void setRole_limit_id(int role_limit_id) {
		this.role_limit_id = role_limit_id;
	}

	public int getRole_limit_id() {
		return role_limit_id;
	}

	public RoleLimitCallback getRole_limit() {
		return role_limit;
	}

	public void setRole_limit(RoleLimitCallback role_limit) {
		this.role_limit = role_limit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isHome() {
		return home;
	}

	public List<MenuRouteTreeNode> getChildren() {
		return children;
	}
}
