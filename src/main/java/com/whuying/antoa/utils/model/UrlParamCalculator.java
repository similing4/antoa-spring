package com.whuying.antoa.utils.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UrlParamCalculator
 * 描述: 使用页面跳转按钮时前端页面参数实体
 */
public class UrlParamCalculator {
    /**
     * List<UrlParamCalculatorParamItem> 跳转时对应的页面参数
     */
    public List<UrlParamCalculatorParamItem> pageParam = new ArrayList<>();
    /**
     * List<UrlParamCalculatorParamItem> 跳转时对应的行参数
     */
    public List<UrlParamCalculatorParamItem> rowParam = new ArrayList<>();
    
    /**
     * UrlParamCalculator constructor.
     */
    public UrlParamCalculator() {
        this(new ArrayList<UrlParamCalculatorParamItem>());
    }
    
    /**
     * UrlParamCalculator constructor.
     * @param List<UrlParamCalculatorParamItem> pageParam 页面参数
     */
    public UrlParamCalculator(List<UrlParamCalculatorParamItem> pageParam) {
        this(pageParam, new ArrayList<UrlParamCalculatorParamItem>());
    }

    /**
     * UrlParamCalculator constructor.
     * @param List<UrlParamCalculatorParamItem> pageParam 页面参数
     * @param List<UrlParamCalculatorParamItem> rowParam 行参数（只有是行按钮时需要传入）
     */
    public UrlParamCalculator(List<UrlParamCalculatorParamItem> pageParam, List<UrlParamCalculatorParamItem> rowParam) {
        this.pageParam = pageParam;
        this.rowParam = rowParam;
    }

    /**
     * 根据键查找对应的页面参数键值对对象
     * @param String key 键
     * @return UrlParamCalculatorParamItem 找到了参数返回对应的UrlParamCalculatorParamItem对象，否则返回null
     */
    public UrlParamCalculatorParamItem getPageParamByKey(String key){
        for (UrlParamCalculatorParamItem r : this.pageParam){
            if(key.equals(r.key))
                return r;
        }
        return null;
    }

    /**
     * 根据键查找对应的行参数键值对对象
     * @param String key 键
     * @return UrlParamCalculatorParamItem 找到了参数返回对应的UrlParamCalculatorParamItem对象，否则返回null
     */
    public UrlParamCalculatorParamItem getRowParamByKey(String key){
        for(UrlParamCalculatorParamItem r : this.rowParam){
            if(key.equals(r.key))
                return r;
        }
        return null;
    }
}
