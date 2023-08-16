package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class ListFilterCollection implements Serializable {
	private static final long serialVersionUID = 4332063603761657248L;
	private List<ListFilterBase> array = new ArrayList<>();

    public void addItem(ListFilterBase item) {
        this.array.add(item);
    }

    /**
     * 获取所有筛选项对象
     * @return List<ListFilterBase>
     */
    public List<ListFilterBase> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
