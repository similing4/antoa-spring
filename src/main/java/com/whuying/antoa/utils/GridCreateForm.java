package com.whuying.antoa.utils;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.*;
import com.whuying.antoa.utils.hook.CreateOrEditColumnChangeHook;
import com.whuying.antoa.utils.model.*;

public class GridCreateForm implements Serializable {
	private static final long serialVersionUID = 3226885973272744266L;
	
	/**
     * DBCreateOperator对象
     */
    private DBCreateOperator _table;
    /**
     * 被用作跳转到编辑页及调用删除功能时传入的主键列名
     */
    public String primaryKey = "id";
    /**
     * 编辑页的所有表单项
     */
    private CreateColumnCollection _createColumnCollection;
    /**
     * 编辑页的每行自定义API按钮
     */
    private CreateRowButtonBaseCollection _createRowButtonBaseCollection;
    /**
     * 数据变更时的钩子
     */
    private CreateOrEditColumnChangeHookCollection _createOrEditColumnChangeHookCollection;

    /**
     * 构造方法
     * 
     * @param table 表接口
     */
    public GridCreateForm(DBCreateOperator table) {
        this._table = table;
        this._createColumnCollection = new CreateColumnCollection();
        this._createRowButtonBaseCollection = new CreateRowButtonBaseCollection();
        this._createOrEditColumnChangeHookCollection = new CreateOrEditColumnChangeHookCollection();
    }

    /**
     * 获取数据库操作对象
     * 
     * @return DBCreateOperator 数据库操作DB的Builder对象
     */
    public DBCreateOperator getDBObject() {
        return this._table;
    }

    /**
     * 获取内容变更钩子列表
     * 
     * @return List<CreateOrEditColumnChangeHook> 返回行数据变更时的钩子
     */
    @JSONField(name = "createOrEditColumnChangeHookCollection")
    public List<CreateOrEditColumnChangeHook> getChangeHookList() {
        return this._createOrEditColumnChangeHookCollection.getItems();
    }
    
    /**
     * 获取所有列对象
     * 
     * @return List<CreateColumnBase>
     */
    @JSONField(name = "createColumnCollection")
    public List<CreateColumnBase> getCreateColumnList() {
        return this._createColumnCollection.getItems();
    }
    
    @JSONField(name = "createRowButtonBaseCollection")
    public List<CreateRowButtonBase> getCreateRowButtonBaseList() {
		return _createRowButtonBaseCollection.getItems();
	}
    
    /**
     * 设置列表项
     * 
     * @param string primaryKey 被用作跳转到编辑页及调用删除功能时传入的主键列名
     * @return GridCreateForm
     */
    public GridCreateForm setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    /**
     * 序列化对象
     * 
     * @return array 序列化后的JSON
     */
    @Override
    public String toString() {
    	JSONObject ret = new JSONObject();
    	ret.put("primaryKey", this.primaryKey);
    	ret.put("createColumnCollection", this._createColumnCollection.getItems());
    	ret.put("createRowButtonBaseCollection", this._createRowButtonBaseCollection.getItems());
    	ret.put("createOrEditColumnChangeHookCollection", this._createOrEditColumnChangeHookCollection.getItems());
    	return ret.toString();
    }

	/**
     * 指定一列
     * 
     * @param CreateColumnBase columnItem 编辑页的行对象
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm column(CreateColumnBase columnItem) {
        this._createColumnCollection.addItem(columnItem);
        return this;
    }

    /**
     * 指定一个文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnText(String col, String colTip) {
        return this.columnText(col, colTip, "");
    }
    
    /**
     * 指定一个文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnText(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnText(col, colTip, defaultVal));
        return this;
    }

    /**
     * 指定一个进行预除运算的数字字段，提交时会乘回来
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnNumberDivide(String col, String colTip, double divide) {
        return this.columnNumberDivide(col, colTip, divide, "", "");
    }
    
    /**
     * 指定一个进行预除运算的数字字段，提交时会乘回来
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @param string unit 单位，默认为空
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnNumberDivide(String col, String colTip, double divide, String unit) {
    	return this.columnNumberDivide(col, colTip, divide, unit, "");
    }
    
    /**
     * 指定一个进行预除运算的数字字段，提交时会乘回来
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param Number divide 除数
     * @param string unit 单位，默认为空
     * @param String defaultVal 默认值（没除的数）
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnNumberDivide(String col, String colTip, double divide, String unit, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnDivideNumber(col, colTip, divide, unit, defaultVal));
        return this;
    }
    

    /**
     * 指定一个Textarea
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnTextarea(String col, String colTip) {
    	return this.columnTextarea(col, colTip, "");
    }

    /**
     * 指定一个Textarea
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnTextarea(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnTextarea(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个密码输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPassword(String col, String colTip) {
    	return this.columnPassword(col, colTip, "");
    }

    /**
     * 指定一个密码输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPassword(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnPassword(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个下拉单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnSelect(String col, String colTip, List<EnumOption> options) {
        return this.columnSelect(col, colTip, options, "");
    }

    /**
     * 指定一个下拉单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnSelect(String col, String colTip, List<EnumOption> options, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnEnum(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个Radio单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnRadio(String col, String colTip, List<EnumOption> options) {
    	return this.columnRadio(col,colTip,options,"");
    }

    /**
     * 指定一个Radio单选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 单选的选项
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnRadio(String col, String colTip, List<EnumOption> options, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnEnumRadio(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 多选的选项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnCheckbox(String col, String colTip, List<EnumOption> options) {
    	return this.columnCheckbox(col, colTip, options, "");
    }

    /**
     * 指定一个多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<EnumOption> options 多选的选项
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnCheckbox(String col, String colTip, List<EnumOption> options, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnEnumCheckBox(col, colTip, options, defaultVal));
        return this;
    }

    /**
     * 指定一个树形多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<TreeNode> options 详见https://www.antdv.com/components/tree-select-cn
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnTreeCheckbox(String col, String colTip, List<TreeNode> options) {
    	return this.columnTreeCheckbox(col, colTip, options, "");
    }
    
    /**
     * 指定一个树形多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<TreeNode> options 详见https://www.antdv.com/components/tree-select-cn
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnTreeCheckbox(String col, String colTip, List<TreeNode> options, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnEnumTreeCheckBox(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个时间选择输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnTimestamp(String col, String colTip) {
    	return this.columnTimestamp(col, colTip, "");
    }

    /**
     * 指定一个时间选择输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnTimestamp(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnTimestamp(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个时富文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnRichText(String col, String colTip) {
    	return this.columnRichText(col, colTip, "");
    }

    /**
     * 指定一个时富文本输入框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnRichText(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnRichText(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个图片选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPicture(String col, String colTip) {
    	return this.columnPicture(col, colTip, "");
    }

    /**
     * 指定一个图片选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPicture(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnPicture(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个文件选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFile(String col, String colTip) {
    	return this.columnFile(col, colTip, "");
    }

    /**
     * 指定一个文件选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFile(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnFile(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个图片多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPictures(String col, String colTip) {
    	return this.columnPictures(col, colTip, "");
    }

    /**
     * 指定一个图片多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPictures(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnPictures(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个文件多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFiles(String col, String colTip) {
    	return this.columnFiles(col, colTip, "");
    }

    /**
     * 指定一个文件多选框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFiles(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnFiles(col, colTip, defaultVal));
        return this;
    }

    /**
     * 指定一个级联选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<CascaderNode> options 详见https://www.antdv.com/components/cascader-cn
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnCascader(String col, String colTip, List<CascaderNode> options) {
    	return this.columnCascader(col, colTip, options, "");
    }

    /**
     * 指定一个级联选择框
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param List<CascaderNode> options 详见https://www.antdv.com/components/cascader-cn
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnCascader(String col, String colTip, List<CascaderNode> options, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnCascader(col, colTip, options, defaultVal));
        return this;
    }
    
    /**
     * 指定一个展示框，不会被提交
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnDisplay(String col, String colTip) {
    	return this.columnDisplay(col, colTip, "");
    }

    /**
     * 指定一个展示框，不会被提交
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 没有对应的查询值时的默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnDisplay(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnDisplay(col, colTip, defaultVal));
        return this;
    }

    /**
     * 指定一个隐藏的行，会被提交，可以用来接其它页面传来的参数
     * 
     * @param String col 数据库列名
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnHidden(String col) {
        return this.columnHidden(col, "");
    }

    /**
     * 指定一个隐藏的行，会被提交，可以用来接其它页面传来的参数
     * 
     * @param String col 数据库列名
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnHidden(String col, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnHidden(col, defaultVal));
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
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnChildrenChoose(String col, String colTip, GridListEasy gridListEasy, String gridListVModelCol, String gridListDisplayCol) {
    	return this.columnChildrenChoose(col, colTip, gridListEasy, gridListVModelCol, gridListDisplayCol, "");
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
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnChildrenChoose(String col, String colTip, GridListEasy gridListEasy, String gridListVModelCol, String gridListDisplayCol, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnChildrenChoose(col, colTip, gridListEasy, gridListVModelCol, gridListDisplayCol, defaultVal));
        return this;
    }

    /**
     * 指定一列后方请求数据的按钮
     * 
     * @param CreateRowButtonBase buttonItem 需要添加的行按钮对象
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm rowButton(CreateRowButtonBase buttonItem) {
        this._createRowButtonBaseCollection.addItem(buttonItem);
        return this;
    }

    /**
     * 添加内容变更钩子
     * 
     * @param CreateOrEditColumnChangeHook hook 行数据变更时的钩子
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm setChangeHook(CreateOrEditColumnChangeHook hook) {
        this._createOrEditColumnChangeHookCollection.addItem(hook);
        return this;
    }

    /**
     * 创建一个每行页面跳转按钮
     * 
     * @param CreateRowButtonNavigate createRowButtonItem 按钮项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm rowNavigateButton(CreateRowButtonNavigate createRowButtonItem) {
        this._createRowButtonBaseCollection.addItem(createRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行API调用按钮
     * 
     * @param CreateRowButtonApi createRowButtonItem 按钮项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm rowApiButton(CreateRowButtonApi createRowButtonItem) {
        this._createRowButtonBaseCollection.addItem(createRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行文件BLOB下载调用按钮
     * 
     * @param CreateRowButtonBlob createRowButtonItem 按钮项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm rowBlobButton(CreateRowButtonBlob createRowButtonItem) {
        this._createRowButtonBaseCollection.addItem(createRowButtonItem);
        return this;
    }

    /**
     * 创建一个需要弹窗确认的每行API调用按钮
     * 
     * @param CreateRowButtonApiWithConfirm createRowButtonItem 按钮项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm rowApiButtonWithConfirm(CreateRowButtonApiWithConfirm createRowButtonItem) {
        this._createRowButtonBaseCollection.addItem(createRowButtonItem);
        return this;
    }

    /**
     * 创建一个每行弹窗展示富文本的模态框的按钮
     * 
     * @param CreateRowButtonRichText createRowButtonItem 按钮项
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm rowRichTextButton(CreateRowButtonRichText createRowButtonItem) {
        this._createRowButtonBaseCollection.addItem(createRowButtonItem);
        return this;
    }
    
    /**
     * 指定一个图片选择框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPictureLocal(String col, String colTip) {
    	return this.columnPictureLocal(col, colTip, "");
    }

    /**
     * 指定一个图片选择框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPictureLocal(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnPictureLocal(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个文件选择框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFileLocal(String col, String colTip) {
    	return this.columnFileLocal(col, colTip, "");
    }

    /**
     * 指定一个文件选择框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFileLocal(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnFileLocal(col, colTip, defaultVal));
        return this;
    }
    

    /**
     * 指定一个图片多选框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPicturesLocal(String col, String colTip) {
    	return this.columnPicturesLocal(col, colTip, "");
    }

    /**
     * 指定一个图片多选框并上传到服务器本地public/antoa_uploads/下
     * 
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnPicturesLocal(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnPicturesLocal(col, colTip, defaultVal));
        return this;
    }
    
    /**
     * 指定一个文件多选框并上传到服务器本地public/antoa_uploads/下
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFilesLocal(String col, String colTip) {
    	this.columnFilesLocal(col, colTip, "");
    	return this;
    }

    /**
     * 指定一个文件多选框并上传到服务器本地public/antoa_uploads/下
     * @param String col 数据库列名
     * @param String colTip 在列表页该列的的表头名称
     * @param String defaultVal 默认值
     * @return GridCreateForm 返回this以便链式调用
     */
    public GridCreateForm columnFilesLocal(String col, String colTip, String defaultVal) {
        this._createColumnCollection.addItem(new CreateColumnFilesLocal(col, colTip, defaultVal));
        return this;
    }
}
