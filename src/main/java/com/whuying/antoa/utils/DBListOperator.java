package com.whuying.antoa.utils;

import java.util.Map;

import com.whuying.antoa.db.DB;
import com.whuying.antoa.db.PaginateResult;

public abstract class DBListOperator implements Cloneable {
    /**
     * Builder
     */
    public DB builder; //DB类产生的对象，于构造方法中传入

    public DBListOperator(DB builder) {
        this.builder = builder;
    }

    public DBListOperator doClone() throws CloneNotSupportedException {
    	DBListOperator clone = (DBListOperator) this.clone();
    	clone.builder = builder.clone();
    	return clone;
    }

    //where方法，设置的对应column会作为条件传入。你可以根据column自定义设置传入条件内容
    public DBListOperator where(String key, Object value) {
        this.where(key,"=" ,value);
        return this;
    }
    
    //where方法，设置的对应column会作为条件传入。你可以根据column自定义设置传入条件内容
    public DBListOperator where(String key, String operator, Object value) {
        this.builder.where(key, operator, value);
        return this;
    }

    //whereIn方法，设置的对应column会作为条件传入。你可以根据column自定义设置传入条件内容
    public DBListOperator whereIn(String key, Object[] values) {
        this.builder.whereIn(key, values);
        return this;
    }

    //orderBy方法，你可以在这里自定义设置排序规则。
    public DBListOperator orderBy(String column, String direction) {
        this.builder.orderBy(column, direction);
        return this;
    }

    //select方法，如果你有连接查询你可以在这里将查询字段格式化为正确的字段解决冲突。
    public DBListOperator select(String... columns) {
        this.builder.setFields(columns);
        return this;
    }

    //分页方法，不建议直接重写本方法，建议直接通过hook修改结果。
    public PaginateResult paginate(int pageCount, int currentPage) {
        return this.builder.paginate(pageCount, currentPage);
    }

    //当编辑页或创建页使用column为COLUMN_CHILDREN_CHOOSE类型时，extra需要使用本方法。
    public Map<String, Object> first() {
        return this.builder.first();
    }

    //detail判断、删除时判断
    public Map<String, Object> find(int id) {
    	return this.builder.where("id", id).first();
    }

    //删除时进行的操作，除了重写这里之外，你也可以直接重写AntOAController的delete方法
    public int delete(int id) {
        return this.builder.delete();
    }
}
