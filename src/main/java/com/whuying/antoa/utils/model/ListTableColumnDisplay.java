package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

/**
 * ClassName: ListTableColumnDisplay
 * 描述: 用于展示的表列，需要使用hook配置其内容值，否则展示内容为空
 */
public class ListTableColumnDisplay extends ListTableColumnBase {

	private static final long serialVersionUID = -3235452156976771121L;
	public String type = "ListTableColumnDisplay";

	public ListTableColumnDisplay(String col, String tip) {
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
	
    @Override
    public boolean isTypeDisplay(){
        return true;
    }
}
