package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

/**
 * ClassName: ListTableColumnRichText
 * 描述:富文本表列
 */
public class ListTableColumnRichText extends ListTableColumnBase {
	private static final long serialVersionUID = 8294486384767325082L;
	public String type = "ListTableColumnRichText";

	public ListTableColumnRichText(String col, String tip) {
		super(col, tip);
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
