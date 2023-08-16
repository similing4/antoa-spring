package com.whuying.antoa.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.whuying.antoa.db.DB;
import com.whuying.antoa.utils.AuthService;
import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.bean.*;

@RestController
public class AntOAUserController {
    /**
     * AuthService对象，如果你需要自己实现授权Token，你可以重写之
     */
    protected AuthService auth = null;

    protected HttpServletRequest request() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "RequestAttributes不能为null");
        return servletRequestAttributes.getRequest();
    }

	public String readJSONFromRequest(HttpServletRequest request) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		return sb.toString();
	}

    public AntOAUserController() {
        this.auth = new AuthService();
    }
    
    /**
     * 根据TOKEN获取UID，失败时抛出异常，只对API有效！
     * @param Request request
     * @return string UID
     * @throws Exception token不合法时登录失效
     */
    protected String getUserInfo() throws Exception {
        String token = request().getHeader("Authorization");
        if (token == null)
            throw new Exception("登录失效");
        String uid = this.auth.getUidFromToken(token);
        if (uid == null || "".equals(uid))
            throw new Exception("登录失效");
        Map<String, Object> user = DB.table("antoa_user").where("id",uid).first();
        if ("0".equals(user.get("status") + ""))
            throw new Exception("账户已被封禁");
        return uid;
    }

    @RequestMapping(value = { "/api/antoa/user/change_password" }, method = RequestMethod.POST)
    public String changePassword() {
        try {
            String uid = this.getUserInfo();
    		JSONObject req = JSONObject.parseObject(readJSONFromRequest(request()));
            String password = req.getString("password");
            if (password == null || "".equals(password))
                throw new Exception("新密码不能为空");
            Map<String, Object> params = new HashMap<>();
            params.put("password", auth.md5(password));
            DB.table("antoa_user").where("id", uid).update(params);
            return new NormalResponse(1, "密码修改成功", null) + "";
        } catch (Exception e) {
            return new ErrorResponse(0, e.getMessage(), null) + "";
        }
    }
}