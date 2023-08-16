package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

/**
 * ClassName: EditColumnCascader
 * 描述: 级联选择
 */
public class EditColumnCascader extends EditColumnBase {
	
	private static final long serialVersionUID = -3485706980088981039L;
	
	/**
     * 多选选项数组
     */
    @JSONField(name = "enum")
    public List<CascaderNode> options;
    
    public String type = "EditColumnCascader";

    /**
     * EditColumnCascader constructor 级联选择构造方法，v-model绑定的应为字符串数组，数组中每一项应为CascaderNode中的value字段
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<CascaderNode> options 多选选项数组
     */
    public EditColumnCascader(String col, String tip, List<CascaderNode> options) {
    	this(col, tip, options, "");
    }

    /**
     * EditColumnCascader constructor 级联选择构造方法，v-model绑定的应为字符串数组，数组中每一项应为CascaderNode中的value字段
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<CascaderNode> options 多选选项数组
     * @param String defaultVal 默认值
     */
    public EditColumnCascader(String col, String tip, List<CascaderNode> options, String defaultVal) {
        super(col, tip, defaultVal);
        this.options = options;
    }

    public JSONObject jsonSerialize() {
    	JSONObject ret = super.jsonSerialize();
    	ret.put("type", this.type);
    	ret.put("enum", this.options);
    	return ret;
    }
    
    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }
}
