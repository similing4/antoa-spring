package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;

/**
 * NameSpace: Modules\AntOA\Http\Utils\Model
 * ClassName: ListHeaderButtonNavigate
 * 描述: 列表页头部跳转页面按钮实体类
 */
public abstract class ListHeaderButtonNavigate extends ListHeaderButtonBase {

	private static final long serialVersionUID = -7885807284928801041L;
    public String type = "ListHeaderButtonNavigate";

	public ListHeaderButtonNavigate(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
	}
	
    public ListHeaderButtonNavigate(String baseUrl, String buttonText, String buttonType) {
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
