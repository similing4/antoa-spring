package com.whuying.antoa.db;

import java.util.HashMap;
import java.util.Map;

import com.whuying.antoa.utils.Utils;

public class Seeder {
	public static void seed() {
		if(DB.table("information_schema.TABLES").where("TABLE_NAME", "antoa_user").count() > 0)
			return;
		String pre = "";
        DB.queryUpdate("drop table if exists " + pre + "antoa_user");
        DB.queryUpdate("CREATE TABLE `" + pre + "antoa_user` (" +
             "`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID'," +
             "`username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名'," +
             "`password` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码'," +
             "`status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（0正常1禁用）'," +
             "`role` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户权限'," +
             "`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'," +
             "PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC");
        Map<String, Object> row = new HashMap<>();
        row.put("username", "admin");
        row.put("password", Utils.md5("admin"));//默认密码为admin，如果需要修改默认密码可以在这里修改
        row.put("role", "[\"1\"]");//默认有超级管理员角色
        DB.table("antoa_user").insert(row);
        DB.queryUpdate("CREATE TABLE `" + pre + "antoa_role` ("+
             "`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',"+
             "`name` varchar(200) NOT NULL COMMENT '角色名称',"+
             "PRIMARY KEY (`id`)"+
        ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8");
        row = new HashMap<>();
        row.put("id", 1);
        row.put("name", "超级管理员");
        DB.table("antoa_role").insert(row);
	}
}
