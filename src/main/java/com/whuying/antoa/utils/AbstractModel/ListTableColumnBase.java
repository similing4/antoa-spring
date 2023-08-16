package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.model.UrlParamCalculator;

public abstract class ListTableColumnBase implements Serializable {
	private static final long serialVersionUID = -6759440403159963729L;
	/**
     * 列对应的字段
     */
    public String col;
    /**
     * 列对应的字段Label
     */
    public String tip;

    /**
     * 表列构造方法基类
     * 
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     */
    public ListTableColumnBase(String col, String tip) {
        this.col = col;
        this.tip = tip;
    }


	@Override
    public String toString() {
        return jsonSerialize().toString();
    }

    /**
     * 实例对象序列化方法，子类需重写该方法以确定给前端页面的json对象
     * 
     * @return array JSON对应的PHP数组
     */
    public JSONObject jsonSerialize() {
    	JSONObject ret = new JSONObject();
    	ret.put("col" , this.col);
    	ret.put("tip" , this.tip);
    	return ret;
    }

    /**
     * 是否是展示类型的实例，默认不是。如果是展示类型的实例，那么查询时将不对该实例对应字段进行查询
     */
    public boolean isTypeDisplay(){
        return false;
    }

    /**
     * 当列表页接口查询出结果后，可以重写该方法对对应控制的字段内容进行修改，比如可以通过这个方法将数字1分变成0.01元
     * @param Map<String, Object> searchResultItem 查询结果对应的行数据，可通过 searchResultItem[this.col] 直接获取并修改响应值，当然你也可以额外配置一些特殊字段。
     * @param UrlParamCalculator urlParamCalculator 页面参数实例
     * @param String uid 当前登录用户的UID
     */
    public void onParse(Map<String, Object> searchResultItem, UrlParamCalculator urlParamCalculator, String uid) {
    }
}
