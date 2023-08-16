package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.DBListOperator;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;

public class ListFilterCascader extends ListFilterBase {
	private static final long serialVersionUID = 4941653204464429341L;
	/**
	 * 多选选项数组
	 */
    @JSONField(name = "enum")
	public List<CascaderNode> options;
    public String type = "ListFilterCascader";

	/**
	 * ListFilterCascader constructor
	 * 级联选择构造方法，v-model绑定的应为字符串数组，数组中每一项应为CascaderNode中的value字段
	 * 
	 * @param String             col 列对应的字段
	 * @param String             tip 列对应的字段Label
	 * @param List<CascaderNode> options 多选选项数组
	 * @param String             defaultVal 默认值
	 */
	public ListFilterCascader(String col, String tip, List<CascaderNode> options) {
		this(col, tip, options, "");
	}

	/**
	 * ListFilterCascader constructor
	 * 级联选择构造方法，v-model绑定的应为字符串数组，数组中每一项应为CascaderNode中的value字段
	 * 
	 * @param String             col 列对应的字段
	 * @param String             tip 列对应的字段Label
	 * @param List<CascaderNode> options 多选选项数组
	 * @param String             defaultVal 默认值
	 */
	public ListFilterCascader(String col, String tip, List<CascaderNode> options, String defaultVal) {
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

	@Override
	public void onFilter(DBListOperator gridListDbObject, UrlParamCalculator urlParamCalculator, String uid) {
		UrlParamCalculatorParamItem param = urlParamCalculator.getPageParamByKey(this.col);
		if (param != null && !"".equals(param.val + "")) {
			JSONArray array = JSONArray.parseArray(param.val + "");
			gridListDbObject.where(this.col, String.join(" ", array.toArray(new String[array.size()])));
		}
	}
}
