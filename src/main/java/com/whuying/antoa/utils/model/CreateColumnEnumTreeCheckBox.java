package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnEnumTreeCheckBox extends CreateColumnBase {
	private static final long serialVersionUID = 5885241116863191100L;
	/**
     * 多选选项数组
     */
    @JSONField(name = "enum")
    public List<TreeNode> options;
    public String type = "CreateColumnEnumTreeCheckBox";

    /**
     * CreateColumnEnumCheckBox constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<TreeNode> options 单选选项数组
     */
    public CreateColumnEnumTreeCheckBox(String col, String tip, List<TreeNode> options) {
    	this(col,tip,options,"");
    }
    
    /**
     * CreateColumnEnumTreeCheckBox constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<TreeNode> options 多选选项数组
     * @param string defaultVal 默认值
     */
    public CreateColumnEnumTreeCheckBox(String col, String tip, List<TreeNode> options, String defaultVal) {
    	super(col, tip, defaultVal);
        this.options = options;
    }
    
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
