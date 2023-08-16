package com.whuying.antoa.utils.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnEnumTreeCheckBox extends EditColumnBase {

	private static final long serialVersionUID = -2338468569130648545L;
    public String type = "EditColumnEnumTreeCheckBox";
	/**
     * List<TreeNode> 多选选项数组
     */
    @JSONField(name = "enum")
    public List<TreeNode> options;
    
    /**
     * EditColumnEnumTreeCheckBox constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<TreeNode> options 多选选项数组
     * @param String defaultVal 默认值
     */
    public EditColumnEnumTreeCheckBox(String col, String tip, List<TreeNode> options) {
    	this(col,tip,options,"");
    }

    /**
     * EditColumnEnumTreeCheckBox constructor.
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<TreeNode> options 多选选项数组
     * @param String defaultVal 默认值
     */
    public EditColumnEnumTreeCheckBox(String col, String tip, List<TreeNode> options, String defaultVal) {
        super(col, tip, defaultVal);
        this.options = options;
    }

    @Override
    public JSONObject jsonSerialize() {
    	JSONObject ret = super.jsonSerialize();
    	ret.put("type", "EditColumnEnumTreeCheckBox");
    	ret.put("enum", options);
    	return ret;
    }
    
    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }
}
