package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnEnumRadio extends CreateColumnBase {
	
	private static final long serialVersionUID = 6314450432968467250L;
	/**
     * 单选选项数组
     */
    @JSONField(name = "enum")
    public List<EnumOption> options;
    public String type = "CreateColumnEnumRadio";

    /**
     * CreateColumnEnumRadio constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     * @param string defaultVal 默认值
     */
    public CreateColumnEnumRadio(String col, String tip, List<EnumOption> options) {
    	this(col,tip,options,"");
    }
    
    /**
     * CreateColumnEnumRadio constructor.
     * @param String $col 列对应的字段
     * @param String $tip 列对应的字段Label
     * @param List<EnumOption> $options 单选选项数组
     * @param string $defaultVal 默认值
     */
    public CreateColumnEnumRadio(String col, String tip, List<EnumOption> options, String defaultVal) {
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

