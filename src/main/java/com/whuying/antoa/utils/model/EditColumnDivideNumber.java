package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditColumnBase;

public class EditColumnDivideNumber extends EditColumnBase {

	private static final long serialVersionUID = -360704017507781296L;
    public String type = "EditColumnDivideNumber";
	/**
     * 除数
     */
    public double divide;
    /**
     * 单位，默认为空
     */
    public String unit = "";

    /**
     * @param String col 列名
     * @param String tip 在列表页该列的的表头名称
     * @param double divide 除数
     */
    public EditColumnDivideNumber(String col, String tip, double divide) {
    	this(col,tip,divide, "");
    }
    
    /**
     * @param String col 列名
     * @param String tip 在列表页该列的的表头名称
     * @param double divide 除数
     * @param String unit 单位，默认为空
     */
    public EditColumnDivideNumber(String col, String tip, double divide, String unit) {
    	this(col,tip,divide,unit,"");
    }
    
    /**
     * @param String col 列名
     * @param String tip 在列表页该列的的表头名称
     * @param double divide 除数
     * @param String unit 单位，默认为空
     * @param string defaultVal 默认值
     */
    public EditColumnDivideNumber(String col, String tip, double divide, String unit, String defaultVal) {
        super(col, tip, defaultVal);
        this.divide = divide;
        this.unit = unit;
    }

    @Override
    public JSONObject jsonSerialize() {
    	JSONObject ret = super.jsonSerialize();
    	ret.put("type", this.type);
    	ret.put("divide", divide);
    	ret.put("unit", unit);
    	return ret;
    }
    
    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }
}
