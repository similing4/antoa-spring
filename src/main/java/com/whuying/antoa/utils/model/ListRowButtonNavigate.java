package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonBase;

/**
 * NameSpace: Modules\AntOA\Http\Utils\Model
 * ClassName: ListRowButtonNavigate
 * 描述: 列表页每行跳转页面按钮实体类
 */
public abstract class ListRowButtonNavigate extends ListRowButtonBase {

	private static final long serialVersionUID = -414419007008705294L;
	public String type = "ListRowButtonNavigate";

	public ListRowButtonNavigate(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
		// TODO Auto-generated constructor stub
	}
	
	public ListRowButtonNavigate(String baseUrl, String buttonText, String buttonType) {
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
