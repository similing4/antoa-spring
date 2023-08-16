package com.whuying.antoa.utils.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: FormButtonApiUrlReturnJSON
 * 描述: 编辑或创建页面API按钮返回值实例（BLOB除外）
 */
public class FormButtonApiUrlReturnJSON {
	public Map<String, String> form;
    public String msg;
    public List<String> displayColumns;
    
    /**
     * FormButtonApiUrlReturnJSON constructor.
     * @param String msg 返回提示参数，如果为空串则不提示
     * @param Map<String, String> $form 赋值到表单上的键值对数组
     * @param List<String> displayColumns 变更显示的所有列，如果不改变则可以传空数组
     */
    public FormButtonApiUrlReturnJSON(String msg, Map<String, String> form) {
    	this(msg, form, new ArrayList<String>());
    }

    /**
     * FormButtonApiUrlReturnJSON constructor.
     * @param String msg 返回提示参数，如果为空串则不提示
     * @param Map<String, String> $form 赋值到表单上的键值对数组
     * @param List<String> displayColumns 变更显示的所有列，如果不改变则可以传空数组
     */
    public FormButtonApiUrlReturnJSON(String msg, Map<String, String> form, List<String> displayColumns) {
        this.msg = msg;
        this.form = form;
        this.displayColumns = displayColumns;
    }
}
