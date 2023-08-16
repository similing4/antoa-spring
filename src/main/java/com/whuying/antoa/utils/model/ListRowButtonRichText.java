package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonBase;

/**
 * ClassName: ListRowButtonRichText
 * 描述: 列表页每行按钮调用API并将其值作为富文本进行模态框展示的实体类
 * 注：被调用的API返回值应为JSON格式且包含status字段，status字段为1时客户端成功并将data字段作为富文本展示，否则客户端展示msg字段
 */
public abstract class ListRowButtonRichText extends ListRowButtonBase {

	private static final long serialVersionUID = -919242484311747679L;
	public String type = "ListRowButtonRichText";

	public ListRowButtonRichText(String baseUrl, String buttonText) {
		super(baseUrl, buttonText);
		// TODO Auto-generated constructor stub
	}
	
    public ListRowButtonRichText(String baseUrl, String buttonText, String buttonType) {
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
