package com.whuying.antoa.utils;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.*;
import com.whuying.antoa.utils.model.*;

public class GridList implements Serializable {
	private static final long serialVersionUID = -1971645727929665202L;
	/**
     * 被用作跳转到编辑页及调用删除功能时传入的主键列名
     */
    public String primaryKey = "id";
    /**
     * 列表的所有筛选列
     */
    private ListFilterCollection listFilterCollection;
    /**
     * 列表的所有列
     */
    private ListTableColumnCollection listTableColumnCollection;
    /**
     * 顶部创建外所有按钮
     */
    private ListHeaderButtonCollection listHeaderButtonCollection;
    /**
     * 每行编辑与删除外所有按钮
     */
    private ListRowButtonCollection listRowButtonCollection;
    /**
     * DBListOperator 对象
     */
    private DBListOperator _list = null;
    /**
     * 列表页是否有创建按钮
     */
    public boolean hasCreate = true;
    /**
     * 列表页是否有编辑按钮
     */
    public boolean hasEdit = true;
    /**
     * 列表页是否有删除按钮
     */
    public boolean hasDelete = true;

    /**
     * 构造方法
     * 
     * @param table 表接口
     */
    public GridList(DBListOperator table) {
        this.listFilterCollection = new ListFilterCollection();
        this.listHeaderButtonCollection = new ListHeaderButtonCollection();
        this.listTableColumnCollection = new ListTableColumnCollection();
        this.listRowButtonCollection = new ListRowButtonCollection();
        this._list = table;
    }

    /**
     * 获取数据库操作对象
     * 
     * @return 数据库操作DB的Builder对象
     */
    public DBListOperator getDBObject() {
        return this._list;
    }

    /**
     * 设置列表项
     * 
     * @param 被用作跳转到编辑页及调用删除功能时传入的主键列名
     * @return
     */
    public GridList setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    /**
     * 获取所有筛选对象
     * 
     * @return
     */
    @JSONField(name = "listFilterCollection")
    public List<ListFilterBase> getFilterList() {
        return this.listFilterCollection.getItems();
    }

    /**
     * 获取所有列对象
     * 
     * @return
     */
    @JSONField(name = "listTableColumnCollection")
    public List<ListTableColumnBase> getTableColumnList() {
        return this.listTableColumnCollection.getItems();
    }

    /**
     * 获取所有页面顶部按钮对象
     * 
     * @return
     */
    @JSONField(name = "listHeaderButtonCollection")
    public List<ListHeaderButtonBase> getHeaderButtonList() {
        return this.listHeaderButtonCollection.getItems();
    }

    /**
     * 删除指定页面顶部按钮对象
     * 
     * @param items
     */
    public void removeHeaderButtons(List<ListHeaderButtonBase> items) {
        this.listHeaderButtonCollection.removeItems(items);
    }

    /**
     * 获取所有列对象
     * 
     * @return
     */
    @JSONField(name = "listRowButtonCollection")
    public List<ListRowButtonBase> getRowButtonList() {
        return this.listRowButtonCollection.getItems();
    }

    /**
     * 序列化对象
     * 
     * @return 序列化后的JSON
     */
    @Override
    public String toString() {
    	JSONObject ret = new JSONObject();
    	ret.put("primaryKey", this.primaryKey);
    	ret.put("listFilterCollection", this.listFilterCollection.getItems());
    	ret.put("listTableColumnCollection", this.listTableColumnCollection.getItems());
    	ret.put("listHeaderButtonCollection", this.listHeaderButtonCollection.getItems());
    	ret.put("listRowButtonCollection", this.listRowButtonCollection.getItems());
    	ret.put("hasCreate", this.hasCreate);
    	ret.put("hasEdit", this.hasEdit);
    	ret.put("hasDelete", this.hasDelete);
    	return ret.toString();
    }

    /**
     * 指定一列
     * 
     * @param column 列对象实例
     * @return 返回this以便链式调用
     */
    public GridList column(ListTableColumnBase column) {
        this.listTableColumnCollection.addItem(column);
        return this;
    }

    /**
     * 指定文本列
     * 
     * @param col 数据库列名
     * @param colTip 在列表页该列的的表头名称
     * @return 返回this以便链式调用
     */
    public GridList columnText(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnText(col, colTip));
        return this;
    }

    /**
     * 指定展示列，可通过HOOK设置其展示值
     * 
     * @param col 列名
     * @param colTip 在列表页该列的的表头名称
     * @return 返回this以便链式调用
     */
    public GridList columnDisplay(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnDisplay(col, colTip));
        return this;
    }

    /**
     * 指定富文本展示列，可通过HOOK设置其展示值
     * 
     * @param col 列名
     * @param colTip 在列表页该列的的表头名称
     * @return 返回this以便链式调用
     */
    public GridList columnRichDisplay(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnRichDisplay(col, colTip));
        return this;
    }

    /**
     * 指定图片列
     * 
     * @param col 列名
     * @param colTip 在列表页该列的的表头名称
     * @param width 图片的展示宽度，不填写单位的情况下默认单位为px
     * @param height 图片的展示宽度，不填写单位的情况下默认单位为px
     * @return 返回this以便链式调用
     */
    public GridList columnPicture(String col, String colTip, String width, String height) {
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
     * 
     * @param col 列名
     * @param colTip 在列表页该列的的表头名称
     * @param options ENUM的选项数组
     * @return 返回this以便链式调用
     */
    public GridList columnEnum(String col, String colTip, List<EnumOption> options) {
        this.listTableColumnCollection.addItem(new ListTableColumnEnum(col, colTip, options));
        return this;
    }

    /**
     * 指定富文本列
     * 
     * @param col 列名
     * @param colTip 在列表页该列的的表头名称
     * @return 返回this以便链式调用
     */
    public GridList columnRichText(String col, String colTip) {
        this.listTableColumnCollection.addItem(new ListTableColumnRichText(col, colTip));
        return this;
    }

    /**
     * 隐藏列，查询出来但不展示
     * 
     * @param col 列名
     * @return 返回this以便链式调用
     */
    public GridList columnHidden(String col) {
        this.listTableColumnCollection.addItem(new ListTableColumnHidden(col));
        return this;
    }
    
    /**
     * 指定除以指定数值后展示的列
     * 
     * @param col 列名
     * @param colTip 在列表页该列的的表头名称
     * @param divide 除数
     * @return 返回this以便链式调用
     */
    public GridList columnDivideNumber(String col, String colTip, double divide) {
        return this.columnDivideNumber(col, colTip, divide, "");
    }

    /**
     * 指定除以指定数值后展示的列
     * 
     * @param col 列名
     * @param colTip 在列表页该列的的表头名称
     * @param divide 除数
     * @param unit 单位，默认为空
     * @return 返回this以便链式调用
     */
    public GridList columnDivideNumber(String col, String colTip, double divide, String unit) {
        this.listTableColumnCollection.addItem(new ListTableColumnDivideNumber(col, colTip, divide, unit));
        return this;
    }

    /**
     * 指定隐藏类型筛选列，用于外部传入
     * 
     * @param col 筛选的列名
     * @return 返回this以便链式调用
     */
    public GridList filterHidden(String col) {
        this.listFilterCollection.addItem(new ListFilterHidden(col));
        return this;
    }

    /**
     * 指定文本类型筛选列
     * 
     * @param String col 筛选的列名
     * @param String colTip 筛选项的名称
     * @return GridList 返回this以便链式调用
     */
    public GridList filterText(String col, String colTip) {
        this.listFilterCollection.addItem(new ListFilterText(col, colTip));
        return this;
    }

    /**
     * 指定开始时间筛选列
     * 
     * @param String col 筛选的列名
     * @param String colTip 筛选项的名称
     * @return GridList 返回this以便链式调用
     */
    public GridList filterStartTime(String col, String colTip) {
        this.listFilterCollection.addItem(new ListFilterStartTime(col, colTip));
        return this;
    }

    /**
     * 指定结束时间筛选列
     * 
     * @param String col 筛选的列名
     * @param String colTip 筛选项的名称
     * @return GridList 返回this以便链式调用
     */
    public GridList filterEndTime(String col, String colTip) {
        this.listFilterCollection.addItem(new ListFilterEndTime(col, colTip));
        return this;
    }

    /**
     * 指定单选类型筛选列
     * 
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options ENUM的选项数组
     * @return GridList 返回this以便链式调用
     */
    public GridList filterEnum(String col, String colTip, List<EnumOption> options) {
        this.listFilterCollection.addItem(new ListFilterEnum(col, colTip, options));
        return this;
    }

    /**
     * 指定多选类型筛选列
     * 
     * @param String col 列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options ENUM的选项数组
     * @return GridList 返回this以便链式调用
     */
    public GridList filterMultiSelect(String col, String colTip, List<EnumOption> options) {
        this.listFilterCollection.addItem(new ListFilterMultiSelect(col, colTip, options));
        return this;
    }

    /**
     * 指定级联筛选列
     * 
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param List<CascaderNode> options 多选选项数组
     * @param string defaultVal 默认值
     */
    public GridList filterCascader(String col, String colTip, List<CascaderNode> options) {
        this.listFilterCollection.addItem(new ListFilterCascader(col, colTip, options));
        return this;
    }

    /**
     * 创建一个筛选项
     * 
     * @param ListFilterBase filterItem 筛选对象
     * @return GridList 返回this以便链式调用
     */
    public GridList filter(ListFilterBase filterItem) {
        this.listFilterCollection.addItem(filterItem);
        return this;
    }

    /**
     * 设置uid筛选项
     * 
     * @param String col 数据库列名
     * @return GridList 返回this以便链式调用
     */
    public GridList filterUid(String col) {
        this.listFilterCollection.addItem(new ListFilterUID(col));
        return this;
    }

    /**
     * 设置是否显示创建按钮及使用创建页面
     * 
     * @param boolean b 为真时开启创建功能，否则关闭创建功能
     * @return GridList 返回this以便链式调用
     */
    public GridList useCreate(boolean b) {
        this.hasCreate = b;
        return this;
    }

    /**
     * 设置是否显示编辑按钮及使用编辑页面
     * 
     * @param boolean b 为真时开启编辑功能，否则关闭编辑功能
     * @return GridList 返回this以便链式调用
     */
    public GridList useEdit(boolean b) {
        this.hasEdit = b;
        return this;
    }

    /**
     * 设置是否显示删除按钮
     * 
     * @param boolean b 为真时开启删除功能，否则关闭删除功能
     * @return GridList 返回this以便链式调用
     */
    public GridList useDelete(boolean b) {
        this.hasDelete = b;
        return this;
    }

    /**
     * 创建一个头部页面跳转按钮
     * 
     * @param ListHeaderButtonNavigate listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerNavigateButton(ListHeaderButtonNavigate listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个头部页面导出并跳转按钮
     * 
     * @param ListHeaderButtonNavigateDownload listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerNavigateButtonDownload(ListHeaderButtonNavigateDownload listHeaderButtonItem){
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个头部API调用按钮
     * 
     * @param ListHeaderButtonApi listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerApiButton(ListHeaderButtonApi listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个头部API复制按钮
     * 
     * @param ListHeaderButtonClipboard listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerClipboardButton(ListHeaderButtonClipboard listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个头部文件BLOB下载调用按钮
     * 
     * @param ListHeaderButtonBlob listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerBlobButton(ListHeaderButtonBlob listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗确认的头部API调用按钮
     * 
     * @param ListHeaderButtonApiWithConfirm listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerApiButtonWithConfirm(ListHeaderButtonApiWithConfirm listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个头部弹窗展示富文本的模态框的按钮
     * 
     * @param ListHeaderButtonRichText listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerRichTextButton(ListHeaderButtonRichText listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗输入内容的模态框的顶部按钮
     * 
     * @param ListHeaderButtonWithForm listHeaderButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList headerApiButtonWithForm(ListHeaderButtonWithForm listHeaderButtonItem) {
        this.listHeaderButtonCollection.addItem(listHeaderButtonItem);
        return this;
    }

    /**
     * 创建一个每行页面跳转按钮
     * 
     * @param ListRowButtonNavigate listRowButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList rowNavigateButton(ListRowButtonNavigate listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行API复制按钮
     * 
     * @param ListRowButtonClipboard listRowButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList rowClipboardButton(ListRowButtonClipboard listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行API调用按钮
     * 
     * @param ListRowButtonApi listRowButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList rowApiButton(ListRowButtonApi listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行文件BLOB下载调用按钮
     * 
     * @param ListRowButtonBlob listRowButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList rowBlobButton(ListRowButtonBlob listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗确认的每行API调用按钮
     * 
     * @param ListRowButtonApiWithConfirm listRowButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList rowApiButtonWithConfirm(ListRowButtonApiWithConfirm listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行弹窗展示富文本的模态框的按钮
     * 
     * @param ListRowButtonRichText listRowButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList rowRichTextButton(ListRowButtonRichText listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗输入内容的模态框的按钮
     * 
     * @param ListRowButtonWithForm listRowButtonItem 按钮项
     * @return GridList 返回this以便链式调用
     */
    public GridList rowApiButtonWithForm(ListRowButtonWithForm listRowButtonItem) {
        this.listRowButtonCollection.addItem(listRowButtonItem);
        return this;
    }
}
