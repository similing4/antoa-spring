package com.whuying.antoa.utils.hook;

import com.whuying.antoa.bean.AntOAApiListResponse;

/**
 * Interface ListHook
 * 列表接口后置钩子
 */
public interface ListHook {
    /**
     * 列表接口后置钩子
     * @param AntOAApiListResponse response 列表paginate经过数组化的数据
     * @return AntOAApiListResponse 返回传入参数
     */
    public AntOAApiListResponse hook(AntOAApiListResponse response);
}
