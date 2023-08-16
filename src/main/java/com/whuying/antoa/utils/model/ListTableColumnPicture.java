package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

/**
 * ClassName: ListTableColumnPicture
 * 描述:图片列，将查出的数据当做图片url解析成图片展示出来
 */
public class ListTableColumnPicture extends ListTableColumnBase {

	private static final long serialVersionUID = 1431475744405128836L;
    public String type = "ListTableColumnPicture";
	/**
     * 图片宽度（单位由字符串自身指定，如10vw或100px等）
     */
    public String width;
    /**
     * 图片高度（单位由字符串自身指定，如10vw或100px等）
     */
    public String height;

    /**
     * ListTableColumnPicture constructor.
     * @param String col 列对应的字段
     * @param String tip 在列表页该列的的表头名称
     * @param String width 图片宽度（单位由字符串自身指定，如10vw或100px等）
     * @param String height 图片高度（单位由字符串自身指定，如10vw或100px等）
     */
    public ListTableColumnPicture(String col, String tip, String width, String height) {
        super(col, tip);
        this.width = width;
        this.height = height;
    }
    
	@Override
	public JSONObject jsonSerialize() {
		JSONObject ret = super.jsonSerialize();
		ret.put("type", this.type);
		ret.put("width", width);
		ret.put("height", height);
		return ret;
	}

	@Override
	public String toString() {
		return this.jsonSerialize().toString();
	}
}
