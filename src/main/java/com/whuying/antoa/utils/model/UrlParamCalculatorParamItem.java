package com.whuying.antoa.utils.model;

/**
 * ClassName: UrlParamCalculatorParamItem
 * 描述: 页面请求的单个参数实体表示
 */
public class UrlParamCalculatorParamItem {
    /**
     * 键
     */
    public String key;
    /**
     * 值
     */
    public Object val;

    /**
     * UrlParamCalculatorParamItem constructor.
     * @param String key 参数键
     * @param Object val 参数值
     */
    public UrlParamCalculatorParamItem(String key, Object val) {
        this.key = key;
        this.val = val;
    }
}
