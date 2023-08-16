package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;

public class CreateColumnDivideNumber extends CreateColumnBase {

	private static final long serialVersionUID = 3853834245564509811L;
	
	/**
	 * 除数
	 */
	public double divide;
	/**
	 * 单位，默认为空
	 */
	public String unit;

	public String type = "CreateColumnDivideNumber";

	/**
	 * @param String col 列名
	 * @param String tip 在列表页该列的的表头名称
	 * @param Number divide 除数
	 */
	public CreateColumnDivideNumber(String col, String tip, double divide) {
		this(col, tip, divide, "");
	}

	/**
	 * @param String col 列名
	 * @param String tip 在列表页该列的的表头名称
	 * @param Number divide 除数
	 * @param String unit 单位，默认为空
	 */
	public CreateColumnDivideNumber(String col, String tip, double divide, String unit) {
		this(col, tip, divide, unit, "");
	}

	/**
	 * @param String col 列名
	 * @param String tip 在列表页该列的的表头名称
	 * @param Number divide 除数
	 * @param String unit 单位，默认为空
	 * @param string defaultVal 默认值
	 */
	public CreateColumnDivideNumber(String col, String tip, double divide, String unit, String defaultVal) {
		super(col, tip, defaultVal);
		this.divide = divide;
		this.unit = unit;
	}

	public String toString() {
		return this.jsonSerialize().toString();
	}

	public JSONObject jsonSerialize() {
		JSONObject ret = this.jsonSerialize();
		ret.put("type", this.type);
		ret.put("divide", this.divide);
		ret.put("unit", this.unit);
		return ret;
	}
}
