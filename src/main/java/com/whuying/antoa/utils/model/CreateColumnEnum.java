package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnEnum extends CreateColumnBase {
	private static final long serialVersionUID = -9118067183320316004L;
	/**
     * 单选选项数组
     */
    @JSONField(name = "enum")
    public List<EnumOption> options;
    
    public String type = "CreateColumnEnum";

    /**
     * CreateColumnEnum constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     * @param string defaultVal 默认值
     */
    public CreateColumnEnum(String col, String tip, List<EnumOption> options) {
    	this(col,tip,options,"");
    }
    
    /**
     * CreateColumnEnum constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     * @param string defaultVal 默认值
     */
    public CreateColumnEnum(String col, String tip, List<EnumOption> options, String defaultVal) {
        super(col, tip, defaultVal);
        this.options = options;
    }

    public JSONObject jsonSerialize() {
    	JSONObject ret = super.jsonSerialize();
    	ret.put("type", this.type);
    	ret.put("enum", options);
    	return ret;
    }
    
    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }
}
