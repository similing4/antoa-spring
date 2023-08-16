package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class CreateColumnCollection implements Serializable {
	private static final long serialVersionUID = -5044658981880360398L;
	private List<CreateColumnBase> array = new ArrayList<>();

    public void addItem(CreateColumnBase item) {
        this.array.add(item);
    }

    /**
     * 获取编辑页项对象
     * @return List<CreateColumnBase>
     */
    public List<CreateColumnBase> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
