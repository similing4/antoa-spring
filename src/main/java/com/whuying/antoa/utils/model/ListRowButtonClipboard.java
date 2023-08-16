package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonBase;

/**
 * ClassName: ListRowButtonClipboard
 * 描述: 列表页每行调用API复制内容按钮实体类
 * 注：被调用的API返回值应为JSON格式且包含status字段，status字段为1时客户端成功展示data字段，否则客户端展示msg字段
 */
public abstract class ListRowButtonClipboard extends ListRowButtonBase {

	private static final long serialVersionUID = -1643882280144446347L;
	public String type = "ListRowButtonClipboard";

	public ListRowButtonClipboard(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
		// TODO Auto-generated constructor stub
	}
	
	public ListRowButtonClipboard(String baseUrl, String buttonText, String buttonType) {
		super(baseUrl, buttonText, buttonType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject jsonSerialize() {
		JSONObject ret = super.jsonSerialize();
		ret.put("type", this.type);
		return ret;
	}

	@Override
	public String toString() {
		return this.jsonSerialize().toString();
	}
}
