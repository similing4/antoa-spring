package com.whuying.antoa.utils.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.AbstractModel.ListFilterBase;
import com.whuying.antoa.utils.AbstractModel.ListFilterCollection;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonCollection;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonBase;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonCollection;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnBase;
import com.whuying.antoa.utils.AbstractModel.ListTableColumnCollection;

public class GridListEasy implements Serializable {
	private static final long serialVersionUID = -5359655567286688043L;
	/**
     * 列表的所有筛选列
     */
    private ListFilterCollection listFilterCollection;
    /**
     * 列表的所有列
     */
    private ListTableColumnCollection listTableColumnCollection;
    /**
     * 顶部所有按钮
     */
    private ListHeaderButtonCollection listHeaderButtonCollection;
    /**
     * 每行选中外所有按钮
     */
    private ListRowButtonCollection listRowButtonCollection;
    
    private DBListOperator _list = null;

    /**
     * 构造方法
     * @param DBListOperator table 表接口
     */
    public GridListEasy(DBListOperator table) {
        this.listFilterCollection = new ListFilterCollection();
        this.listHeaderButtonCollection = new ListHeaderButtonCollection();
        this.listTableColumnCollection = new ListTableColumnCollection();
        this.listRowButtonCollection = new ListRowButtonCollection();
        this._list = table;
    }

    /**
     * 获取数据库操作对象
     * @return DBListOperator 数据库操作DB的Builder对象
     */
    public DBListOperator getDBObject() {
        return this._list;
    }

    /**
     * 获取所有筛选对象
     * @return List<ListFilterBase>
     */
    @JSONField(name = "listFilterCollection")
    public List<ListFilterBase> getFilterList() {
        return this.listFilterCollection.getItems();
    }

    /**
     * 获取所有列对象
     * @return List<ListTableColumnBase>
     */
    @JSONField(name = "listTableColumnCollection")
    public List<ListTableColumnBase> getTableColumnList() {
        return this.listTableColumnCollection.getItems();
    }

    /**
     * 获取所有页面顶部按钮对象
     * @return List<ListHeaderButtonBase>
     */
    @JSONField(name = "listHeaderButtonCollection")
    public List<ListHeaderButtonBase> getHeaderButtonList() {
        return this.listHeaderButtonCollection.getItems();
    }
    /**
     * 删除指定页面顶部按钮对象
     * @param List<ListHeaderButtonBase> items
     */
    public void removeHeaderButtons(List<ListHeaderButtonBase> items) {
        this.listHeaderButtonCollection.removeItems(items);
    }

    /**
     * 获取所有列对象
     * @return List<ListRowButtonBase>
     */
    @JSONField(name = "listRowButtonCollection")
    public List<ListRowButtonBase> getRowButtonList() {
        return this.listRowButtonCollection.getItems();
    }
    
    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }

    /**
     * 序列化对象
     * @return array 序列化后的JSON
     */
    public JSONObject jsonSerialize() {
    	JSONObject ret = new JSONObject();
    	ret.put("listFilterCollection"       , this.listFilterCollection);
    	ret.put("listTableColumnCollection"  , this.listTableColumnCollection);
    	ret.put("listHeaderButtonCollection" , this.listHeaderButtonCollection);
    	ret.put("listRowButtonCollection"    , this.listRowButtonCollection);
    	return ret;
    }

    /**
     * 指定一列
     * @param ListTableColumnBase column 列对象实例
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy column(ListTableColumnBase column) {
        this.listTableColumnCollection.addItem(column);
        return this;
    }

    /**
     * 指定文本列
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnText(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnText(col, colTip));
        return this;
    }

    /**
     * 指定展示列，可通过HOOK设置其展示值
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnDisplay(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnDisplay(col, colTip));
        return this;
    }

    /**
     * 指定富文本展示列，可通过HOOK设置其展示值
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnRichDisplay(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnRichDisplay(col, colTip));
        return this;
    }

    /**
     * 指定图片列
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number width 图片的展示宽度，不填写单位的情况下默认单位为px
     * @param Number height 图片的展示宽度，不填写单位的情况下默认单位为px
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnPicture(String col, String colTip, String width, String height) {
        try {
    		Double.parseDouble(width);
    		width = width + "px";
    	}catch(Exception ignore) { }
    	try {
    		Double.parseDouble(height);
    		height = height + "px";
    	}catch(Exception ignore) { }
    	this.listTableColumnCollection.addItem(new ListTableColumnPicture(col, colTip, width, height));
        return this;
    }

    /**
     * 指定单选列
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options ENUM的选项数组
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnEnum(String col, String colTip, List<EnumOption> options) {
        this.listTableColumnCollection.addItem(new ListTableColumnEnum(col, colTip, options));
        return this;
    }

    /**
     * 指定富文本列
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnRichText(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnRichText(col, colTip));
        return this;
    }

    /**
     * 隐藏列，查询出来但不展示
     * @param String col 列名
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnHidden(String col) {
        this.listTableColumnCollection.addItem(new ListTableColumnHidden(col));
        return this;
    }

    /**
     * 指定除以指定数值后展示的列
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnDivideNumber(String col, String colTip, double divide) {
    	return this.columnDivideNumber(col, colTip, divide, "");
    }

    /**
     * 指定除以指定数值后展示的列
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @param String unit 单位，默认为空
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy columnDivideNumber(String col, String colTip, double divide, String unit) {
        this.listTableColumnCollection.addItem(new ListTableColumnDivideNumber(col, colTip, divide, unit));
        return this;
    }

    /**
     * 指定隐藏类型筛选列，用于外部传入
     * @param String col 筛选的列名
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy filterHidden(String col) {
        this.listFilterCollection.addItem(new ListFilterHidden(col));
        return this;
    }

    /**
     * 指定文本类型筛选列
     * @param String col 筛选的列名
     * @param String colTip 筛选项的名称
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy filterText(String col, String colTip) {
        this.listFilterCollection.addItem(new ListFilterText(col, colTip));
        return this;
    }

    /**
     * 指定开始时间筛选列
     * @param String col 筛选的列名
     * @param String colTip 筛选项的名称
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy filterStartTime(String col, String colTip) {
        this.listFilterCollection.addItem(new ListFilterStartTime(col, colTip));
        return this;
    }

    /**
     * 指定结束时间筛选列
     * @param String col 筛选的列名
     * @param String colTip 筛选项的名称
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy filterEndTime(String col, String colTip) {
        this.listFilterCollection.addItem(new ListFilterEndTime(col, colTip));
        return this;
    }

    /**
     * 指定单选类型筛选列
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options ENUM的选项数组
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy filterEnum(String col, String colTip, List<EnumOption> options) {
        this.listFilterCollection.addItem(new ListFilterEnum(col, colTip, options));
        return this;
    }

    /**
     * 创建一个筛选项
     * @param ListFilterBase filterItem 筛选对象
     * @return GridListEasy 返回this以便链式调用
     * @deprecated
     */
    public GridListEasy filter(ListFilterBase filterItem) {
        this.listFilterCollection.addItem(filterItem);
        return this;
    }

    /**
     * 设置uid筛选项
     * @param String col 数据库列名
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy filterUid(String col) {
        this.listFilterCollection.addItem(new ListFilterUID(col));
        return this;
    }

    /**
     * 创建一个头部页面跳转按钮
     * @param ListHeaderButtonNavigate listHeaderButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy headerNavigateButton(ListHeaderButtonNavigate listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个每行页面跳转按钮
     * @param ListRowButtonNavigate listRowButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy rowNavigateButton(ListRowButtonNavigate listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个头部API调用按钮
     * @param ListHeaderButtonApi listHeaderButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy headerApiButton(ListHeaderButtonApi listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个每行API调用按钮
     * @param ListRowButtonApi listRowButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy rowApiButton(ListRowButtonApi listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个头部文件BLOB下载调用按钮
     * @param ListHeaderButtonBlob listHeaderButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy headerBlobButton(ListHeaderButtonBlob listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个每行文件BLOB下载调用按钮
     * @param ListRowButtonBlob listRowButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy rowBlobButton(ListRowButtonBlob listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗确认的头部API调用按钮
     * @param ListHeaderButtonApiWithConfirm listHeaderButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy headerApiButtonWithConfirm(ListHeaderButtonApiWithConfirm listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗确认的每行API调用按钮
     * @param ListRowButtonApiWithConfirm listRowButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy rowApiButtonWithConfirm(ListRowButtonApiWithConfirm listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个头部弹窗展示富文本的模态框的按钮
     * @param ListHeaderButtonRichText listHeaderButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy richTextButton(ListHeaderButtonRichText listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个每行弹窗展示富文本的模态框的按钮
     * @param ListRowButtonRichText listRowButtonItem 按钮项
     * @return GridListEasy 返回this以便链式调用
     */
    public GridListEasy rowRichTextButton(ListRowButtonRichText listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }
}
