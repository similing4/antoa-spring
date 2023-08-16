package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class EditColumnCollection implements Serializable {
	private static final long serialVersionUID = -4179902421208560269L;

	private List<EditColumnBase> array = new ArrayList<>();

    public void addItem(EditColumnBase item) {
        this.array.add(item);
    }

    /**
     * 获取编辑页项对象
     * @return List<EditColumnBase>
     */
    public List<EditColumnBase> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
