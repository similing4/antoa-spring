package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class ListRowButtonCollection implements Serializable {
	private static final long serialVersionUID = -3677763093679436723L;

	private List<ListRowButtonBase> array = new ArrayList<>();

    public void addItem(ListRowButtonBase item) {
    	this.array.add(item);
    }

    public void removeItems(List<ListRowButtonBase> items) {
    	this.array.removeAll(items);
    }

    /**
     * 获取所有行按钮对象
     * @return List<ListRowButtonBase>
     */
    public List<ListRowButtonBase> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
