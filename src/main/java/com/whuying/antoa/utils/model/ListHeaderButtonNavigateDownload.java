package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;

/**
 * ClassName: ListHeaderButtonNavigateDownload
 * 描述: 列表页头部调用API按钮并跳转到结果URL实体类
 * 注：被调用的API返回值应为JSON格式且包含status字段，status字段为1时客户端成功展示data字段，否则客户端展示msg字段
 */
public abstract class ListHeaderButtonNavigateDownload extends ListHeaderButtonBase {

	private static final long serialVersionUID = -7464492692314577806L;
    public String type = "ListHeaderButtonNavigateDownload";

	public ListHeaderButtonNavigateDownload(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
	}
	
    public ListHeaderButtonNavigateDownload(String baseUrl, String buttonText, String buttonType) {
		super(baseUrl, buttonText, buttonType);
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
