package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class ListHeaderButtonCollection implements Serializable {
	private static final long serialVersionUID = -5969868045747130485L;

	private List<ListHeaderButtonBase> array = new ArrayList<>();

    public void addItem(ListHeaderButtonBase item) {
    	this.array.add(item);
    }

    public void removeItems(List<ListHeaderButtonBase> items) {
    	this.array.removeAll(items);
    }

    /**
     * 获取所有行按钮对象
     * @return List<ListHeaderButtonBase>
     */
    public List<ListHeaderButtonBase> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
