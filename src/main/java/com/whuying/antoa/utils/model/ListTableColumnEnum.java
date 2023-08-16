package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

public class ListTableColumnEnum extends ListTableColumnBase {
	
	private static final long serialVersionUID = -7660520393883425925L;
    public String type = "ListTableColumnEnum";
	
	/**
     * 单选选项数组
     */
    @JSONField(name = "enum")
    public List<EnumOption> options;

    /**
     * ListTableColumnEnum constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     */
    public ListTableColumnEnum(String col, String tip, List<EnumOption> options) {
        super(col, tip);
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
}
