package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class CreateRowButtonBaseCollection implements Serializable {
	private static final long serialVersionUID = 3132564959462872564L;
	private List<CreateRowButtonBase> array = new ArrayList<>();

	public void addItem(CreateRowButtonBase item) {
        this.array.add(item);
    }

    public void removeItems(List<CreateRowButtonBase> items) {
    	this.array.removeAll(items);
    }

    /**
     * 获取所有行按钮对象
     * @return List<CreateRowButtonBase>
     */
    public List<CreateRowButtonBase> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
