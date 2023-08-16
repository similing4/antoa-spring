package com.whuying.antoa.utils.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class TreeNode implements Serializable {
	
	private static final long serialVersionUID = 871953626513399733L;

	public List<TreeNode> children = new ArrayList<>();
    public String title;
    public String value;
    public String key;
    public boolean disabled;
    
    public TreeNode(String value, String title) {
    	this(value,title, new ArrayList<TreeNode>());
    }

    public TreeNode(String value, String title, List<TreeNode> children) {
    	this(value,title,children, false);
    }

    public TreeNode(String value, String title, List<TreeNode> children, boolean disabled) {
        this.value = value;
        this.key = value;
        this.title = title;
        this.children = children;
        this.disabled = disabled;
    }

    public JSONObject jsonSerialize() {
    	JSONObject ret = new JSONObject();
    	ret.put("title", title);
    	ret.put("value", value);
    	ret.put("key", key);
        if (this.disabled)
            ret.put("disabled", disabled);
        if (this.children.size() > 0)
            ret.put("children", children);
        return ret;
    }
    
    @Override
    public String toString() {
        return this.jsonSerialize().toString();
    }

    public void addChild(TreeNode node) {
        this.children.add(node);
    }
}
