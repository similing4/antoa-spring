package com.whuying.antoa.utils.hook;

import java.util.Map;

/**
 * Interface CreateHook
 * 创建接口前置钩子
 */
public interface CreateHook {
    /**
     * 创建接口前置钩子
     * @param Map<String, Object> p 传入参数
     * @return Map<String, Object> 返回传入参数，如果返回null则不进行插入操作。
     * @throws Exception
     */
    public Map<String, Object> hook(Map<String, Object> p);
}
