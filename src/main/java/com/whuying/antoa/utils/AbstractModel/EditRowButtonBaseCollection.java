package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.json.JSONArray;

public class EditRowButtonBaseCollection implements Serializable {
	private static final long serialVersionUID = 1646067775585558157L;
	
	private List<EditRowButtonBase> array = new ArrayList<>();

    public void addItem(EditRowButtonBase item) {
    	this.array.add(item);
    }

    public void removeItems(List<EditRowButtonBase> items) {
    	this.array.removeAll(items);
    }

    /**
     * 获取所有行按钮对象
     * @return List<EditRowButtonBase>
     */
    public List<EditRowButtonBase> getItems() {
        return array;
    }

    @Override
    public String toString() {
        return JSONArray.toJSONString(getItems());
    }
}
