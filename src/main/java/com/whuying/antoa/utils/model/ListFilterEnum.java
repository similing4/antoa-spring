package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.DBListOperator;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;

/**
 * ClassName: ListFilterEnum
 * 描述: 根据下拉单选框进行筛选
 */
public class ListFilterEnum extends ListFilterBase {

	private static final long serialVersionUID = 3051763009084367468L;
	/**
     * 单选选项数组
     */
    @JSONField(name = "enum")
    public List<EnumOption> options;
    public String type = "ListFilterEnum";
    
    /**
     * ListTableColumnEnum constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     * @param String defaultVal 默认值
     */
    public ListFilterEnum(String col, String tip, List<EnumOption> options) {
    	this(col, tip, options, "");
    }

    /**
     * ListTableColumnEnum constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<EnumOption> options 单选选项数组
     * @param String defaultVal 默认值
     */
    public ListFilterEnum(String col, String tip, List<EnumOption> options, String defaultVal) {
        super(col, tip, defaultVal);
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
	
    @Override
	public void onFilter(DBListOperator gridListDbObject, UrlParamCalculator urlParamCalculator, String uid) {
    	UrlParamCalculatorParamItem param = urlParamCalculator.getPageParamByKey(this.col);
        if (param != null && !"".equals(param.val + ""))
            gridListDbObject.where(this.col, param.val);
    }
}
