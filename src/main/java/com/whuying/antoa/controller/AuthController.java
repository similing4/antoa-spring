package com.whuying.antoa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.StringMap;
import com.whuying.antoa.bean.ErrorResponse;
import com.whuying.antoa.bean.MenuRouteTitleNode;
import com.whuying.antoa.bean.MenuRouteTreeNode;
import com.whuying.antoa.bean.MenuRouteTreeNodeCollection;
import com.whuying.antoa.bean.NormalResponse;
import com.whuying.antoa.bean.RoleLimitCallback;
import com.whuying.antoa.db.DB;
import com.whuying.antoa.utils.AuthService;
import com.whuying.antoa.utils.Utils;

@RestController
public class AuthController {
    
    protected AuthService auth = null;
    
    @Autowired
    private MenuRouteTreeNodeCollection routesConfigCollection;

    public AuthController() {
        this.auth = new AuthService();
    }

    /**
     * 登录授权
     * username 用户名
     * password 密码
     * @return String 登录成功时data字段为token，否则msg字段为错误信息
     */
    @RequestMapping(value = { "/api/antoa/auth/login" }, method = RequestMethod.POST)
    public String login() {
        try {
            boolean isLoginDiy = Utils.config("antoa.login_diy", false, Boolean.class);
            if(isLoginDiy)
                throw new Error("自定义登录情况下默认登录接口禁用");
    		JSONObject req = JSONObject.parseObject(Utils.readJSONFromRequest(Utils.request()));
            String username = req.getString("username");
            String password = req.getString("password");
            if (username == null || password == null || "".equals(username) || "".endsWith(password))
                throw new Exception("用户名或密码不能为空");
            Map<String, Object> result = DB.table("antoa_user").where("username", username).first();
            if (result == null)
                throw new Exception("用户名或密码错误");
            if (!Utils.md5(password).equals(result.get("password")))
                throw new Exception("用户名或密码错误");
            if ("0".equals(result.get("status") + ""))
                throw new Exception("您的账户已被禁用，如有疑问请联系管理员！");
            AuthService auth = this.auth;
            String token = auth.makeTokenWithCache(result.get("id") + "");
            return new NormalResponse(1, token, null).toString();
        } catch (Exception e) {
        	return new ErrorResponse(0, e.getMessage(), null).toString();
        }
    }

    /**
     * TOKEN验权
     * @return String 通用成功失败返回
     */
    @RequestMapping(value = { "/api/antoa/auth/auth" }, method = RequestMethod.POST)
    public String auth() {
    	AuthService auth = this.auth;
        String uid = auth.getUidFromToken(Utils.request().getHeader("Authorization"));
        if (uid != null) {
        	JSONObject ret = new JSONObject();
        	ret.put("status", 1);
        	ret.put("data", uid);
        	ret.put("role", DB.table("antoa_user").where("id", uid).first().get("role"));
        	return ret.toString();
        }
        return new ErrorResponse(0, "登录失效", null).toString();
    }

    /**
     * 获取七牛云等配置API
     * @return String 通用成功失败返回
     */
    @RequestMapping(value = { "/api/antoa/auth/config" }, method = RequestMethod.GET)
    public String api_config() {
        try {
            String uid = this.auth.getUidFromToken(Utils.request().getHeader("Authorization"));
            if (uid == null || "".equals(uid))
                throw new Exception("登录失效");
            String accessKey = Utils.config("antoa.config.qiniu.access_key", "", String.class);
            String secretKey = Utils.config("antoa.config.qiniu.secret_key", "", String.class);
            String bucketName = Utils.config("antoa.config.qiniu.bucket", "", String.class);
            String qiniuUrl = Utils.config("antoa.config.qiniu.url", "", String.class);
            com.qiniu.util.Auth auth = com.qiniu.util.Auth.create(accessKey, secretKey);
            StringMap map = new StringMap();
            map.put("detectMime", 3);
            map.put("saveKey", "$(etag)$(ext)");
            String token = auth.uploadToken(bucketName, null, 300, map, true);
            List<MenuRouteTreeNode> routes = this.makeRoutes(uid);
            List<MenuRouteTitleNode> titleMap = this.makeTitleMap();
            JSONObject ret = new JSONObject();
            ret.put("status", 1);
            ret.put("host", qiniuUrl);
            ret.put("token", token);
            ret.put("routes", routes);
            ret.put("title_map", titleMap);
            ret.put("home_page", Utils.config("antoa.home_page", "", String.class));
            return ret.toString();
        } catch (Exception e) {
        	e.printStackTrace();
            return new ErrorResponse(0, e.getMessage(), null).toString();
        }
    }
    
    private List<MenuRouteTreeNode> menuRouteTreeNodeDFS(MenuRouteTreeNode item) {
    	List<MenuRouteTreeNode> ret = new ArrayList<>();
        if (item.children != null && item.children.size() != 0)
            for(MenuRouteTreeNode r : item.children)
            	ret.addAll(menuRouteTreeNodeDFS(r));
        if (item.path == null || "".equals(item.path))
            return ret;
        if (item.name == null || "".equals(item.name))
            return ret;
        ret.add(item);
    	return ret;
    }

    private List<MenuRouteTitleNode> makeTitleMap() throws Exception {
    	List<MenuRouteTreeNode> configRoutes = routesConfigCollection.menu_routes;
    	if(configRoutes == null)
    		throw new Exception("Config antoa.menu_routes unset!");
        List<MenuRouteTreeNode> ret = new ArrayList<>();
        for(MenuRouteTreeNode r : configRoutes)
        	ret.addAll(menuRouteTreeNodeDFS(r));
        MenuRouteTreeNode node = new MenuRouteTreeNode();
        node.path = "/antoa/user/change_password";
        node.name = "修改密码";
        ret.add(node);
        List<MenuRouteTitleNode> ret2 = new ArrayList<>();
        for(MenuRouteTreeNode node1 : ret)
        	ret2.add(new MenuRouteTitleNode(node1));
        return ret2;
    }

    private List<MenuRouteTreeNode> makeRoutes(String uid) throws Exception {
    	Map<String, Object> user = DB.table("antoa_user").where("id", uid).first();
        if (user == null)
            throw new Exception("登录失效");
        List<MenuRouteTreeNode> configRoutes = routesConfigCollection.menu_routes;
    	if(configRoutes == null)
    		throw new Exception("Config antoa.menu_routes unset!");
        MenuRouteTreeNode nodeChangePasswordChild = new MenuRouteTreeNode();
        MenuRouteTreeNode nodeChangePassword = new MenuRouteTreeNode();
        nodeChangePasswordChild.name = "修改密码";
        nodeChangePasswordChild.path = "/antoa/user/change_password";
        nodeChangePasswordChild.visible = false;
        nodeChangePassword.name = "修改密码";
        nodeChangePassword.visible = false;
        nodeChangePassword.children.add(nodeChangePasswordChild);
        configRoutes.add(nodeChangePassword);
        for(MenuRouteTreeNode configRoutesItem : configRoutes) {
            if (configRoutesItem.children != null && configRoutesItem.children.size() > 0) {
                configRoutesItem.children = configRoutesItem.children.stream().filter(r -> {
                	boolean limitVailed = true;
                    if (r.role_limit != null)
                        limitVailed = r.role_limit.call(user);
                    else if(r.getRole_limit_id() != -1) {
                    	r.role_limit = new RoleLimitCallback(r.getRole_limit_id() + "");
                    	limitVailed = r.role_limit.call(user);
                    }
                    return limitVailed;
                }).collect(Collectors.toList());
            }
            if (configRoutesItem.isHome()) {
                configRoutesItem.children = new ArrayList<>();
                configRoutesItem.path = "/home";
            }
        }
        configRoutes = configRoutes.stream().filter(r->{
            boolean limitVailed = true;
            if (r.role_limit != null)
                limitVailed = r.role_limit.call(user);
            else if(r.getRole_limit_id() != -1) {
            	r.role_limit = new RoleLimitCallback(r.getRole_limit_id() + "");
            	limitVailed = r.role_limit.call(user);
            }
            return r.visible && limitVailed;
        }).collect(Collectors.toList());
        return configRoutes;
    }
}
