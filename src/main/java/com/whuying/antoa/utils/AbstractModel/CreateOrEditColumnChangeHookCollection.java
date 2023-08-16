package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

import com.whuying.antoa.utils.hook.CreateOrEditColumnChangeHook;

public class CreateOrEditColumnChangeHookCollection implements Serializable {
	private static final long serialVersionUID = -5562333399317694L;
	
	private List<CreateOrEditColumnChangeHook> array = new ArrayList<>();

    public void addItem(CreateOrEditColumnChangeHook item) {
        this.array.add(item);
    }

    /**
     * 获取所有行按钮对象
     * @return List<CreateOrEditColumnChangeHook>
     */
    public List<CreateOrEditColumnChangeHook> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
