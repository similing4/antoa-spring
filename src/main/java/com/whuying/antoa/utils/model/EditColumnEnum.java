package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnEnum extends EditColumnBase {
	
	private static final long serialVersionUID = -747055332306335009L;
    public String type = "EditColumnEnum";
	/**
     * List<EnumOption> 单选选项数组
     */
    @JSONField(name = "enum")
    public List<EnumOption> options;

    /**
     * EditColumnEnum constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     */
    public EditColumnEnum(String col, String tip, List<EnumOption> options) {
    	this(col, tip, options, "");
    }
    
    /**
     * EditColumnEnum constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     * @param string defaultVal 默认值
     */
    public EditColumnEnum(String col, String tip, List<EnumOption> options, String defaultVal) {
        super(col, tip, defaultVal);
        this.options = options;
    }

    @Override
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
