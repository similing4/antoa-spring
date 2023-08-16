package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

/**
 * ClassName: ListTableColumnText
 * 描述:普通表列，直接展示对应的数据值
 */
public class ListTableColumnText extends ListTableColumnBase {

	private static final long serialVersionUID = -3929003640192482920L;
	public String type = "ListTableColumnText";

	public ListTableColumnText(String col, String tip) {
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
