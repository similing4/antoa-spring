package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

/**
 * NameSpace: Modules\AntOA\Http\Utils\Model
 * ClassName: ListTableColumnRichDisplay
 * 描述: 用于展示的富文本表列，需要使用hook配置其内容值，否则展示内容为空
 */
public class ListTableColumnRichDisplay extends ListTableColumnBase {
    
	private static final long serialVersionUID = 8654983649569212582L;
    public String type = "ListTableColumnRichDisplay";

	public ListTableColumnRichDisplay(String col, String tip) {
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

    public boolean isTypeDisplay(){
        return true;
    }
}
