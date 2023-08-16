package com.whuying.antoa.bean;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "antoa.config")
public class MenuRouteTreeNodeCollection {
	public List<MenuRouteTreeNode> getMenu_routes() {
		return menu_routes;
	}

	public void setMenu_routes(List<MenuRouteTreeNode> menu_routes) {
		this.menu_routes = menu_routes;
	}

	public List<MenuRouteTreeNode> menu_routes;
}
