package com.whuying.antoa.utils.hook;

/**
 * Interface DeleteHook
 * 删除接口前置钩子
 */
public interface DeleteHook {
    /**
     * 删除接口前置钩子
     * @param String id 待删除的内容ID
     * @return String 返回传入参数，如果返回null则不进行删除操作。
     * @throws Exception
     */
    public String hook(String id);
}
