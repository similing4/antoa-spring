package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;

/**
 * ClassName: ListTableColumnDivideNumber
 * 描述: 当查出的数值需要除以一定的数值（如分转元）时可以使用该类的实例，该实例会将数据值除以指定值并可选附加单位
 */
public class ListTableColumnDivideNumber extends ListTableColumnBase {
	
	private static final long serialVersionUID = -7069697097757754452L;
    public String type = "ListTableColumnDivideNumber";
	
	/**
     * 除数
     */
    public double divide;
    /**
     * 单位，默认为空
     */
    public String unit;
    
    /**
     * @param String col 列名
     * @param String tip 在列表页该列的的表头名称
     * @param double divide 除数
     * @param String unit 单位，默认为空
     */
    public ListTableColumnDivideNumber(String col, String tip, double divide) {
    	this(col, tip, divide, "");
    }

    /**
     * @param String col 列名
     * @param String tip 在列表页该列的的表头名称
     * @param double divide 除数
     * @param String unit 单位，默认为空
     */
    public ListTableColumnDivideNumber(String col, String tip, double divide, String unit) {
        super(col, tip);
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
