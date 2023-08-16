package com.whuying.antoa.utils.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOrEditColumnChangeHookReturnData {
    /**
     * 键为form表单的列名，值为将被覆盖为的值
     */
    public Map<String, Object> data = new HashMap<>();
    /**
     * 包含所有需要显示的表单列名，如果为空数组或空值null或者为false时则不改变当前已有状态
     */
    public List<String> display = new ArrayList<String>();

    /**
     * 构造函数
     */
    public CreateOrEditColumnChangeHookReturnData() {}
    
    /**
     * 构造函数
     * @param data 键为form表单的列名，值为将被覆盖为的值
     * @param display 包含所有需要显示的表单列名，如果为空数组或空值null或者为false时则不改变当前已有状态
     */
    public CreateOrEditColumnChangeHookReturnData(Map<String, Object> data, List<String> display) {
        this.data = data;
        this.display = display;
    }
}
