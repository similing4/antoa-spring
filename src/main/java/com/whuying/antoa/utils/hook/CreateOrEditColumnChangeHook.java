package com.whuying.antoa.utils.hook;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.model.CreateOrEditColumnChangeHookReturnData;

/**
 * Interface CreateOrEditColumnChangeHook
 * 创建页或编辑页指定数据变更钩子
 */
public abstract class CreateOrEditColumnChangeHook implements Serializable {
	private static final long serialVersionUID = -1861594667173430824L;

    /**
     * String 被监听的字段
     */
	public String col;

    /**
     * CreateOrEditColumnChangeHook constructor.
     * 
     * @param String col 被监听的字段
     */
    public CreateOrEditColumnChangeHook(String col) {
        this.col = col;
    }

    /**
     * 创建页或编辑页指定数据变更钩子，页面初次加载时及指定行数据变更时回调。
     * 
     * @param JSONObject formData 正在编辑的所有数据
     * @param JSONObject pageParam 当前页的页面参数
     * @return CreateOrEditColumnChangeHookReturnData 返回数据的data域将被Object.assign到form中填充字段，而display域将指定要展示的列数组（要包括自身），如果display字段为null或false则不改变当前已有状态。
     */
    public abstract CreateOrEditColumnChangeHookReturnData hook(JSONObject formData, JSONObject pageParam);

    @Override
	public String toString() {
    	JSONObject ret = new JSONObject();
    	ret.put("col", this.col);
    	return ret.toString();
    }
}
