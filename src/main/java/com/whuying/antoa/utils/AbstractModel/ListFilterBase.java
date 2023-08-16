package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.model.UrlParamCalculator;

public abstract class ListFilterBase implements Serializable {
    private static final long serialVersionUID = 90520349726655018L;
    
    /**
     * 列对应的字段
     */
    public String col;
    /**
     * 列对应的字段Label
     */
    public String tip;
    /**
     * 列对应的默认值
     */
    @JSONField(name = "default")
    public String defaultVal;

    /**
     * 表列构造方法基类
     * 
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     */
    public ListFilterBase(String col, String tip) {
    	this(col, tip, "");
    }
    
    /**
     * 表列构造方法基类
     * 
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param String defaultVal 列对应默认值
     */
    public ListFilterBase(String col, String tip, String defaultVal) {
        this.col = col;
        this.tip = tip;
        this.defaultVal = defaultVal;
    }
    
	@Override
    public String toString() {
        return jsonSerialize().toString();
    }

    public JSONObject jsonSerialize() {
    	JSONObject ret = new JSONObject();
    	ret.put("col", this.col);
    	ret.put("tip", this.tip);
    	ret.put("default", this.defaultVal);
        return ret;
    }
	
    public void onFilter(DBListOperator gridListDbObject, UrlParamCalculator urlParamCalculator, String uid){
    }
}
