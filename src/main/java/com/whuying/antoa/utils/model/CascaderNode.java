package com.whuying.antoa.utils.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class CascaderNode implements Serializable {
	private static final long serialVersionUID = 2422254534500914259L;

	public List<CascaderNode> children = new ArrayList<>();
    public String label;
    public String value;
    public boolean disabled;

    public CascaderNode(String value, String label) {
        this(value,label,new ArrayList<CascaderNode>(), false);
    }
    public CascaderNode(String value, String label, List<CascaderNode> children) {
        this(value,label,children, false);
    }

    public CascaderNode(String value, String label, List<CascaderNode> children, boolean disabled) {
        this.value = value;
        this.label = label;
        this.children = children;
        this.disabled = disabled;
    }

    @Override
    public String toString() {
    	JSONObject ret = new JSONObject();
    	ret.put("label", label);
    	ret.put("value", value);
        if (this.disabled)
            ret.put("disabled", disabled);
        if (this.children.size() > 0)
            ret.put("children", children);
        return ret.toString();
    }

    public void addChild(CascaderNode node) {
        this.children.add(node);
    }
}
