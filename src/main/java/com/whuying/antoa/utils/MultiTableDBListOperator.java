package com.whuying.antoa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.whuying.antoa.bean.JoinTable;
import com.whuying.antoa.bean.TableColumns;
import com.whuying.antoa.db.DB;

public class MultiTableDBListOperator extends DBListOperator {
    private String mainTable = "";
    private String mainTableAlias = "";
    private List<JoinTable> joinTables = new ArrayList<>();
    private List<TableColumns> tableColumnsMap = new ArrayList<>();
    private String primaryKey = "";

    /**
     * 用于列表页的左连接查询DBListOperator.
     * @param String mainTable 主表名
     * @param String mainTableAlias 主表别名
     * @param List<JoinTable> joinTables 待查询的所有的表及对应关系
     * @param List<TableColumns> tableColumnsMap 待查询的所有的表的字段对应关系
     * @throws Exception
     */
    public MultiTableDBListOperator(String mainTable, String mainTableAlias, List<JoinTable> joinTables, List<TableColumns> tableColumnsMap) {
        super(null);
        this.mainTable = mainTable;
        this.mainTableAlias = mainTableAlias;
        this.joinTables = joinTables;
        this.tableColumnsMap = tableColumnsMap;
        String database = DB.querySelect("select database() as db").get(0).get("db") + "";
        List<Map<String, Object>> mainTableColumns = DB.querySelect("SELECT TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_SCHEMA = '" + database + "' AND TABLE_NAME = '" + mainTable + "'");
        if (mainTableColumns.size() == 0)
            throw new RuntimeException("表" + this.mainTable + "不存在或主键不存在");
        this.primaryKey = mainTableColumns.get(0).get("COLUMN_NAME") + "";
        this.builder = DB.table(this.mainTable + " as " + this.mainTableAlias);
        for (JoinTable joinTable : this.joinTables)
            builder.leftJoin(joinTable.table + " as " + joinTable.tableAlias, joinTable.tableAlias + "." + joinTable.id, "=", joinTable.originTable + "." + joinTable.originId);
    }

    /**
     * 根据传入的列名搜索对应的TableColumns
     * @param String columnName 待搜索的列名
     * @return TableColumns|null 搜索到的TableColumns对象，如果不存在则返回null
     */
    private TableColumns getTableColumnsByColumnName(String columnName) {
        for(TableColumns tableColumns : this.tableColumnsMap) {
            if (tableColumns.isTableColumnsContainsColumn(columnName))
                return tableColumns;
        }
        return null;
    }

    /**
     * 重写父类where方法，改为分表where
     * @param string|array column
     * @param mixed operator
     * @param mixed value
     * @param string boolean
     * @return DBListOperator
     * @throws Exception
     */
    public MultiTableDBListOperator where(String column, String operator, Object value) {
        TableColumns tableColumns = this.getTableColumnsByColumnName(column);
        if (tableColumns != null) {
            super.where(tableColumns.tableAlias + '.' + tableColumns.tableColumnsMap.get(column), operator, value);
            return this;
        }
        throw new RuntimeException("列" + column + "不存在，请在tableColumnsMap中配置！");
    }

    /**
     * 重写父类whereIn方法，改为分表whereIn
     * @param string column
     * @param mixed values
     * @param string boolean
     * @param bool not
     * @return DBListOperator
     * @throws Exception
     */
    public MultiTableDBListOperator whereIn(String column, Object[] values) {
    	TableColumns tableColumns = this.getTableColumnsByColumnName(column);
        if (tableColumns != null) {
            super.whereIn(tableColumns.tableAlias + '.' + tableColumns.tableColumnsMap.get(column), values);
            return this;
        }
        throw new RuntimeException("列" + column + "不存在，请在tableColumnsMap中配置！");
    }

    /**
     * 重写父类方法，改为分表select
     * @param columns
     * @return DBListOperator
     * @throws Exception 字段不存在时报错
     */
    public MultiTableDBListOperator select(String... columns) {
        List<String> needs = new ArrayList<>();
        for(String r : columns) {
        	TableColumns tableColumns = this.getTableColumnsByColumnName(r);
            if (tableColumns == null)
                throw new RuntimeException("字段" + r + "不存在");
            needs.add(tableColumns.tableAlias + "." + tableColumns.tableColumnsMap.get(r) + " as " + r);
        }
        super.select(needs.toArray(new String[needs.size()]));
        return this;
    }

    public Map<String, Object> find(int id) {
        return this.builder.where(this.mainTableAlias + "." + this.primaryKey, id).first();
    }

    public int delete(int id) {
        return DB.table(this.mainTable).where(this.primaryKey, id).delete();
    }

    public DBListOperator setPrimaryId(String table, String column) {
        this.primaryKey = table + "." + column;
        return this;
    }
}
