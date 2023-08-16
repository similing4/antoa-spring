package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class ListTableColumnCollection implements Serializable {
	private static final long serialVersionUID = -1960664491302126091L;

	private List<ListTableColumnBase> array = new ArrayList<>();

    public void addItem(ListTableColumnBase item) {
    	this.array.add(item);
    }

    /**
     * 获取所有列对象
     * @return List<ListTableColumnBase>
     */
    public List<ListTableColumnBase> getItems() {
        return array;
    }


    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
