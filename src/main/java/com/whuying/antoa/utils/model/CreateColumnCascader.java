package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

/**
 * ClassName: CreateColumnCascader 描述: 级联选择
 */
public class CreateColumnCascader extends CreateColumnBase {
	private static final long serialVersionUID = 6570356143557377938L;

	/**
	 * List<CascaderNode> 多选选项数组
	 */
    @JSONField(name = "enum")
	public List<CascaderNode> options;
	
    public String type = "CreateColumnCascader";

	/**
	 * CreateColumnCascader constructor
	 * 级联选择构造方法，v-model绑定的应为字符串数组，数组中每一项应为CascaderNode中的value字段
	 * 
	 * @param String             col 列对应的字段
	 * @param String             tip 列对应的字段Label
	 * @param List<CascaderNode> options 多选选项数组
	 */
	public CreateColumnCascader(String col, String tip, List<CascaderNode> options) {
		this(col, tip, options, "");
	}

	/**
	 * CreateColumnCascader constructor
	 * 级联选择构造方法，v-model绑定的应为字符串数组，数组中每一项应为CascaderNode中的value字段
	 * 
	 * @param String             col 列对应的字段
	 * @param String             tip 列对应的字段Label
	 * @param List<CascaderNode> options 多选选项数组
	 * @param String             defaultVal 默认值
	 */
	public CreateColumnCascader(String col, String tip, List<CascaderNode> options, String defaultVal) {
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
