package com.whuying.antoa.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.DigestUtils;

public class AuthService {
	public static Map<String, String> cacheMap = new ConcurrentHashMap<>();
	public String md5(String text) {
		return DigestUtils.md5DigestAsHex(text.getBytes()).toLowerCase();
	}
	private int rand(int min, int max) {
		return (int)Math.floor(Math.random() * (max - min) + min);
	}
	/**
     * 生成持久化的TOKEN
     * 
     * @param String uid 用户UID
     * @return String token字符串
     */
    public String makeTokenWithCache(String uid) {
        String token = cacheMap.get("ANTOA_USER_AUTH_TOKEN_" + uid);
        if (token == null)
            token = md5(md5(System.currentTimeMillis() + "SSSTXTTSADFFzfdsa") + rand(15, 158) + "AsWttRT878DXZDFDxz");
        cacheMap.put("ANTOA_USER_AUTH_TOKEN_" + uid, token);
        return uid + "_" + token;
    }

    /**
     * 从TOKEN获取UID
     * @param String token 用户TOKEN
     * @return String 查询到返回用户UID，否则返回null
     */
    public String getUidFromToken(String token) {
        if(token == null)
            return null;
        String[] arr = token.split("_");
        if(arr.length < 2)
        	return null;
        String uid = arr[0];
        token = cacheMap.get("ANTOA_USER_AUTH_TOKEN_" + uid);
        if (token == null)
            return null;
        if (!token.equals(arr[1]))
            return null;
        return uid;
    }

    /**
     * 删除TOKEN
     * 
     * @param String token 用户TOKEN
     */
    public void removeToken(String token) {
        String uid = this.getUidFromToken(token);
        cacheMap.remove("ANTOA_USER_AUTH_TOKEN_" + uid);
    }
}
