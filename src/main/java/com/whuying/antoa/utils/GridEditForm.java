package com.whuying.antoa.utils;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.*;
import com.whuying.antoa.utils.hook.CreateOrEditColumnChangeHook;
import com.whuying.antoa.utils.model.*;

public class GridEditForm implements Serializable {
	private static final long serialVersionUID = -1682843746183030684L;
	/**
     * DBEditOperator对象
     */
    private DBEditOperator _table;
    /**
     * 被用作跳转到编辑页及调用删除功能时传入的主键列名
     */
    public String primaryKey = "id";
    /**
     * 编辑页的所有表单项
     */
    private EditColumnCollection _editColumnCollection;
    /**
     * 编辑页的每行自定义API按钮
     */
    private EditRowButtonBaseCollection _editRowButtonBaseCollection;

	/**
     * 数据变更时的钩子
     */
    private CreateOrEditColumnChangeHookCollection _createOrEditColumnChangeHookCollection;

    /**
     * 构造方法
     * @param DBEditOperator table 表接口
     */
    public GridEditForm(DBEditOperator table) {
        this._table = table;
        this._editColumnCollection = new EditColumnCollection();
        this._editRowButtonBaseCollection = new EditRowButtonBaseCollection();
        this._createOrEditColumnChangeHookCollection = new CreateOrEditColumnChangeHookCollection();
    }

    /**
     * 获取数据库操作对象
     * @return DBEditOperator 数据库操作DB的Builder对象
     */
    public DBEditOperator getDBObject() {
        return this._table;
    }

    /**
     * 获取内容变更钩子列表
     * @return List<CreateOrEditColumnChangeHook> 返回行数据变更时的钩子
     */
    @JSONField(name = "createOrEditColumnChangeHookCollection")
    public List<CreateOrEditColumnChangeHook> getChangeHookList() {
        return this._createOrEditColumnChangeHookCollection.getItems();
    }
    
    /**
     * 获取所有列对象
     * @return List<EditColumnBase>
     */
    @JSONField(name = "editColumnCollection")
    public List<EditColumnBase> getEditColumnList() {
        return this._editColumnCollection.getItems();
    }
    
    @JSONField(name = "editRowButtonBaseCollection")
    public List<EditRowButtonBase> getEditRowButtonBaseList() {
		return _editRowButtonBaseCollection.getItems();
	}

    /**
     * 设置列表项
     * @param primaryKey 被用作跳转到编辑页及调用删除功能时传入的主键列名
     * @return GridEditForm
     */
    public GridEditForm setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    /**
     * 序列化对象
     * @return array 序列化后的JSON
     */
    @Override
    public String toString() {
        JSONObject ret = new JSONObject();
        ret.put("primaryKey", this.primaryKey);
        ret.put("editColumnCollection", this._editColumnCollection.getItems());
        ret.put("editRowButtonBaseCollection", this._editRowButtonBaseCollection.getItems());
        ret.put("createOrEditColumnChangeHookCollection", this._createOrEditColumnChangeHookCollection.getItems());
        return ret.toString();
    }

    /**
     * 指定一列
     * @param EditColumnBase columnItem 编辑页的行对象
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm column(EditColumnBase columnItem) {
        this._editColumnCollection.addItem(columnItem);
        return this;
    }
    
    /**
     * 指定一个文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnText(String col, String colTip) {
    	return this.columnText(col, colTip, "");
    }

    /**
     * 指定一个文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnText(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnText(col, colTip, defaultVal));
        return this;
    }

    /**
     * 指定一个进行预除运算的数字字段，提交时会乘回来
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnNumberDivide(String col, String colTip, double divide) {
    	return this.columnNumberDivide(col, colTip, divide, "");
    }

    /**
     * 指定一个进行预除运算的数字字段，提交时会乘回来
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @param string unit 单位，默认为空
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnNumberDivide(String col, String colTip, double divide, String unit) {
    	return this.columnNumberDivide(col, colTip, divide, "", "");
    }

    /**
     * 指定一个进行预除运算的数字字段，提交时会乘回来
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @param string unit 单位，默认为空
     * @param String defaultVal 默认值（没除的数）
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnNumberDivide(String col, String colTip, double divide, String unit, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnDivideNumber(col, colTip, divide, unit, defaultVal));
        return this;
    }
    /**
     * 指定一个Textarea
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnTextarea(String col, String colTip) {
    	return this.columnTextarea(col, colTip, "");
    }

    /**
     * 指定一个Textarea
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnTextarea(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnTextarea(col, colTip, defaultVal));
        return this;
    }
    /**
     * 指定一个密码输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPassword(String col, String colTip) {
    	return this.columnPassword(col, colTip, "");
    }

    /**
     * 指定一个密码输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPassword(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnPassword(col, colTip, defaultVal));
        return this;
    }
    

    /**
     * 指定一个下拉单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnSelect(String col, String colTip, List<EnumOption> options) {
    	return this.columnSelect(col, colTip, options, "");
    }

    /**
     * 指定一个下拉单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnSelect(String col, String colTip, List<EnumOption> options, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnEnum(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个Radio单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnRadio(String col, String colTip, List<EnumOption> options) {
        return this.columnRadio(col,colTip,options,"");
    }

    /**
     * 指定一个Radio单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnRadio(String col, String colTip, List<EnumOption> options, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnEnumRadio(col, colTip, options, defaultVal));
        return this;
    }

    /**
     * 指定一个多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 多选的选项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnCheckbox(String col, String colTip, List<EnumOption> options) {
        return this.columnCheckbox(col, colTip,options, "");
    }

    /**
     * 指定一个多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 多选的选项
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnCheckbox(String col, String colTip, List<EnumOption> options, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnEnumCheckBox(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个树形多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<TreeNode> options 详见https://www.antdv.com/components/tree-select-cn
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnTreeCheckbox(String col, String colTip, List<TreeNode> options) {
    	return this.columnTreeCheckbox(col, colTip, options, "");
    }
    
    /**
     * 指定一个树形多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<TreeNode> options 详见https://www.antdv.com/components/tree-select-cn
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnTreeCheckbox(String col, String colTip, List<TreeNode> options, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnEnumTreeCheckBox(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个时间选择输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnTimestamp(String col, String colTip) {
    	return columnTimestamp(col, colTip, "");
    }

    /**
     * 指定一个时间选择输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnTimestamp(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnTimestamp(col, colTip, defaultVal));
        return this;
    }


    /**
     * 指定一个时富文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnRichText(String col, String colTip) {
    	return this.columnRichText(col, colTip, "");
    }
    
    /**
     * 指定一个时富文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnRichText(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnRichText(col, colTip, defaultVal));
        return this;
    }

    /**
     * 指定一个图片选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPicture(String col, String colTip) {
    	return this.columnPicture(col, colTip, "");
    }
    
    /**
     * 指定一个图片选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPicture(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnPicture(col, colTip, defaultVal));
        return this;
    }

    /**
     * 指定一个文件选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFile(String col, String colTip) {
    	return this.columnFile(col, colTip, "");
    }
    
    /**
     * 指定一个文件选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFile(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnFile(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个图片多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPictures(String col, String colTip) {
    	return this.columnPictures(col, colTip, "");
    }

    /**
     * 指定一个图片多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPictures(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnPictures(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个文件多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFiles(String col, String colTip) {
    	return this.columnFiles(col, colTip, "");
    }

    /**
     * 指定一个文件多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFiles(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnFiles(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个级联选择框
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<CascaderNode> options 详见https://www.antdv.com/components/cascader-cn
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnCascader(String col, String colTip, List<CascaderNode> options) {
    	return this.columnCascader(col, colTip, options, "");
    }

    /**
     * 指定一个级联选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<CascaderNode> options 详见https://www.antdv.com/components/cascader-cn
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnCascader(String col, String colTip, List<CascaderNode> options, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnCascader(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个展示框，不会被提交
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnDisplay(String col, String colTip) {
    	return this.columnDisplay(col, colTip, "");
    }

    /**
     * 指定一个展示框，不会被提交
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 没有对应的查询值时的默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnDisplay(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnDisplay(col, colTip, defaultVal));
        return this;
    }

    /**
     * 指定一个隐藏的行，会被提交，可以用来接其它页面传来的参数
     * 
     * @param String col 数据库列名
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnHidden(String col) {
        this._editColumnCollection.addItem(new EditColumnHidden(col));
        return this;
    }
    
    /**
     * 指定一个子表选择行，子表的第一个设置的列会被作为最终选择的值
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param GridListEasy gridListEasy 用于选择的GridList实例
     * @param String gridListVModelCol 选中列表项后需要作为表单值的列表列（如列表为select uid,username from user，那么此值为uid时将会将对应选中行的uid值作为对应表单值传给后端）
     * @param String gridListDisplayCol 选中列表项后展示在表单中的值对应的列表列（如列表为select uid,username from user，那么此值为username时将会将对应选中行的username值展示在表单上）
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnChildrenChoose(String col, String colTip, GridListEasy gridListEasy, String gridListVModelCol, String gridListDisplayCol) {
    	return this.columnChildrenChoose(col,colTip,gridListEasy,gridListVModelCol,gridListDisplayCol, "");
    }

    /**
     * 指定一个子表选择行，子表的第一个设置的列会被作为最终选择的值
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param GridListEasy gridListEasy 用于选择的GridList实例
     * @param String gridListVModelCol 选中列表项后需要作为表单值的列表列（如列表为select uid,username from user，那么此值为uid时将会将对应选中行的uid值作为对应表单值传给后端）
     * @param String gridListDisplayCol 选中列表项后展示在表单中的值对应的列表列（如列表为select uid,username from user，那么此值为username时将会将对应选中行的username值展示在表单上）
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnChildrenChoose(String col, String colTip, GridListEasy gridListEasy, String gridListVModelCol, String gridListDisplayCol, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnChildrenChoose(col, colTip, gridListEasy, gridListVModelCol, gridListDisplayCol, defaultVal));
        return this;
    }

    /**
     * 指定一列后方请求数据的按钮
     * 
     * @param EditRowButtonBase buttonItem 需要添加的行按钮对象
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm rowButton(EditRowButtonBase buttonItem) {
        this._editRowButtonBaseCollection.addItem(buttonItem);
        return this;
    }

    /**
     * 添加内容变更钩子
     * 
     * @param CreateOrEditColumnChangeHook hook 行数据变更时的钩子
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm setChangeHook(CreateOrEditColumnChangeHook hook) {
        this._createOrEditColumnChangeHookCollection.addItem(hook);
        return this;
    }

    /**
     * 创建一个每行页面跳转按钮
     * 
     * @param EditRowButtonNavigate editRowButtonItem 按钮项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm rowNavigateButton(EditRowButtonNavigate editRowButtonItem) {
        this._editRowButtonBaseCollection.addItem(editRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行API调用按钮
     * 
     * @param EditRowButtonApi editRowButtonItem 按钮项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm rowApiButton(EditRowButtonApi editRowButtonItem) {
        this._editRowButtonBaseCollection.addItem(editRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行文件BLOB下载调用按钮
     * 
     * @param EditRowButtonBlob editRowButtonItem 按钮项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm rowBlobButton(EditRowButtonBlob editRowButtonItem) {
        this._editRowButtonBaseCollection.addItem(editRowButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗确认的每行API调用按钮
     * 
     * @param EditRowButtonApiWithConfirm editRowButtonItem 按钮项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm rowApiButtonWithConfirm(EditRowButtonApiWithConfirm editRowButtonItem) {
        this._editRowButtonBaseCollection.addItem(editRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行弹窗展示富文本的模态框的按钮
     * 
     * @param EditRowButtonRichText editRowButtonItem 按钮项
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm rowRichTextButton(EditRowButtonRichText editRowButtonItem) {
        this._editRowButtonBaseCollection.addItem(editRowButtonItem);
        return this;
    }
    
    /**
     * 指定一个图片选择框并上传到服务器本地/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPictureLocal(String col, String colTip) {
    	return this.columnPictureLocal(col, colTip, "");
    }

    /**
     * 指定一个图片选择框并上传到服务器本地/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPictureLocal(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnPictureLocal(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个文件选择框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFileLocal(String col, String colTip) {
    	return this.columnFileLocal(col, colTip, "");
    }

    /**
     * 指定一个文件选择框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFileLocal(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnFileLocal(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个图片多选框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPicturesLocal(String col, String colTip) {
    	return this.columnPicturesLocal(col, colTip, "");
    }

    /**
     * 指定一个图片多选框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnPicturesLocal(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnPicturesLocal(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个文件多选框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFilesLocal(String col, String colTip) {
    	return columnFilesLocal(col, colTip, "");
    }

    /**
     * 指定一个文件多选框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridEditForm 返回this以便链式调用
     */
    public GridEditForm columnFilesLocal(String col, String colTip, String defaultVal) {
        this._editColumnCollection.addItem(new EditColumnFilesLocal(col, colTip, defaultVal));
        return this;
    }
}
