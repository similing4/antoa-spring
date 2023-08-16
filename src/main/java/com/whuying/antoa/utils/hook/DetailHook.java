package com.whuying.antoa.utils.hook;

import com.whuying.antoa.bean.AntOAApiDetailResponse;

/**
 * Interface DetailHook
 * 详情接口后置钩子
 */
public interface DetailHook {
    /**
     * 详情接口后置钩子
     * @param AntOAApiDetailResponse response 详情页数据
     * @return AntOAApiDetailResponse 返回传入参数
     */
    public AntOAApiDetailResponse hook(AntOAApiDetailResponse response);
}
