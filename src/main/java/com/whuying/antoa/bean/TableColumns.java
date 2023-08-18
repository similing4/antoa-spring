package com.whuying.antoa.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whuying.antoa.db.DB;

public class TableColumns {
    public String table; // 需要的被查询表名
    public String tableAlias; // 需要的被查询表别名
    public Map<String, Object> tableColumnsMap = new HashMap<>(); //需要查询的值对应的 查询结果名=>原名

    /**
     * @param String table 表名
     * @param String alias 表别名
     * @param Map<String, Object> columns 表对应需要的列的 查询结果名=>原名 键值对数组，如果查询*那么传入null
     * @return TableColumns
     * @throws Exception 如果表字段已经设置过了会直接抛出异常
     */
    public TableColumns(String table, String alias, Map<String, Object> columns) {
        this.table = table;
        this.tableAlias = alias;
        this.table(columns);
    }

    /**
     * 对传入的列进行验证并填充
     * @param columns
     * @return this
     * @throws Exception
     */
    public TableColumns table(Map<String, Object> columns) {
        String table = this.table;
        if (columns == null) {
            List<Map<String, Object>> columnsList = DB.querySelect("DESC " + table);
            Map<String, Object> a = new HashMap<>();
            for (Map<String, Object> column : columnsList)
                a.put(column.get("Field") + "", column.get("Field"));
            this.table(a);
            return this;
        }
        this.tableColumnsMap = columns;
        return this;
    }

    /**
     * 判断别名字段是否在该查询结果中
     * @param String aliasName 别名字段
     * @return boolean 存在返回真，否则返回假
     */
    public boolean isTableColumnsContainsColumn(String aliasName) {
        return this.tableColumnsMap.containsKey(aliasName);
    }
}
