package com.whuying.antoa.bean;

public class JoinTable {
    public String table;
    public String tableAlias;
    public String id;
    public String originTable;
    public String originId;

    /**
     * 创建一个左连接表
     * @param String table 被连接的表名
     * @param String alias 被连接的表别名
     * @param String id 被连接的表的外键
     * @param String originTable 被连接表的外键指向的表别名
     * @param String originId 被连接表的外键指向的表键
     */
    public JoinTable(String table, String alias, String id, String originTable, String originId) {
        this.table = table;
        this.tableAlias = alias;
        this.id = id;
        this.originTable = originTable;
        this.originId = originId;
    }
}
