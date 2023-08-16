package com.whuying.antoa.utils.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.utils.AbstractModel.CreateColumnBase;
import com.whuying.antoa.utils.AbstractModel.CreateColumnCollection;
import com.whuying.antoa.utils.AbstractModel.CreateOrEditColumnChangeHookCollection;
import com.whuying.antoa.utils.AbstractModel.CreateRowButtonBase;
import com.whuying.antoa.utils.AbstractModel.CreateRowButtonBaseCollection;
import com.whuying.antoa.utils.hook.CreateOrEditColumnChangeHook;

public class GridCreateFormEasy implements Serializable {
	private static final long serialVersionUID = -4000114832853410324L;
	/**
	 * 编辑页的所有表单项
	 */
	private CreateColumnCollection createColumnCollection;
	/**
	 * 编辑页的每行自定义API按钮
	 */
	private CreateRowButtonBaseCollection createRowButtonBaseCollection;
	/**
	 * 数据变更时的钩子
	 */
	private CreateOrEditColumnChangeHookCollection createOrEditColumnChangeHookCollection;

	/**
	 * 构造方法
	 */
	public GridCreateFormEasy() {
		this.createColumnCollection = new CreateColumnCollection();
		this.createRowButtonBaseCollection = new CreateRowButtonBaseCollection();
		this.createOrEditColumnChangeHookCollection = new CreateOrEditColumnChangeHookCollection();
	}

    @JSONField(name = "createRowButtonBaseCollection")
	public List<CreateRowButtonBase> getCreateRowButtonBaseList() {
		return createRowButtonBaseCollection.getItems();
	}

	/**
	 * 获取所有列对象
	 * 
	 * @return List<CreateColumnBase>
	 */
    @JSONField(name = "createColumnCollection")
	public List<CreateColumnBase> getCreateColumnList() {
		return this.createColumnCollection.getItems();
	}

	/**
	 * 获取内容变更钩子列表
	 * 
	 * @return List<CreateOrEditColumnChangeHook> 返回行数据变更时的钩子
	 */
    @JSONField(name = "createOrEditColumnChangeHookCollection")
	public List<CreateOrEditColumnChangeHook> getChangeHookList() {
		return this.createOrEditColumnChangeHookCollection.getItems();
	}

	/**
	 * 序列化对象
	 * 
	 * @return JSONObject 序列化后的JSON
	 */
	public JSONObject jsonSerialize() {
		JSONObject ret = new JSONObject();
		ret.put("createColumnCollection", this.createColumnCollection);
		ret.put("createRowButtonBaseCollection", this.createRowButtonBaseCollection);
		ret.put("createOrEditColumnChangeHookCollection", this.createOrEditColumnChangeHookCollection);
		return ret;
	}

	@Override
	public String toString() {
		return this.jsonSerialize().toString();
	}

	/**
	 * 指定一列
	 * 
	 * @param CreateColumnBase columnItem 编辑页的行对象
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy column(CreateColumnBase columnItem) {
		this.createColumnCollection.addItem(columnItem);
		return this;
	}

	/**
	 * 指定一个文本输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnText(String col, String colTip) {
		return this.columnText(col, colTip, "");
	}

	/**
	 * 指定一个文本输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnText(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnText(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个进行预除运算的数字字段，提交时会乘回来
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param double divide 除数
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnNumberDivide(String col, String colTip, double divide) {
		return this.columnNumberDivide(col, colTip, divide, "");
	}

	/**
	 * 指定一个进行预除运算的数字字段，提交时会乘回来
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param double divide 除数
	 * @param String unit 单位，默认为空
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnNumberDivide(String col, String colTip, double divide, String unit) {
		return this.columnNumberDivide(col, colTip, divide, unit, "");
	}

	/**
	 * 指定一个进行预除运算的数字字段，提交时会乘回来
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param double divide 除数
	 * @param String unit 单位，默认为空
	 * @param String defaultVal 默认值（没除的数）
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnNumberDivide(String col, String colTip, double divide, String unit,
			String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnDivideNumber(col, colTip, divide, unit, defaultVal));
		return this;
	}

	/**
	 * 指定一个Textarea
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnTextarea(String col, String colTip) {
		return this.columnTextarea(col, colTip, "");
	}

	/**
	 * 指定一个Textarea
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnTextarea(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnTextarea(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个密码输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPassword(String col, String colTip) {
		return this.columnPassword(col, colTip, "");
	}

	/**
	 * 指定一个密码输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPassword(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnPassword(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个下拉单选框
	 * 
	 * @param String           col 数据库列名
	 * @param String           colTip 在列表页该列的的表头名称
	 * @param List<EnumOption> options 单选的选项
	 * @param String           defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnSelect(String col, String colTip, List<EnumOption> options) {
		return columnSelect(col, colTip, options, "");
	}

	/**
	 * 指定一个下拉单选框
	 * 
	 * @param String           col 数据库列名
	 * @param String           colTip 在列表页该列的的表头名称
	 * @param List<EnumOption> options 单选的选项
	 * @param String           defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnSelect(String col, String colTip, List<EnumOption> options, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnEnum(col, colTip, options, defaultVal));
		return this;
	}

	/**
	 * 指定一个Radio单选框
	 * 
	 * @param String           col 数据库列名
	 * @param String           colTip 在列表页该列的的表头名称
	 * @param List<EnumOption> options 单选的选项
	 * @param String           defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnRadio(String col, String colTip, List<EnumOption> options) {
		return this.columnRadio(col, colTip, options, "");
	}

	/**
	 * 指定一个Radio单选框
	 * 
	 * @param String           col 数据库列名
	 * @param String           colTip 在列表页该列的的表头名称
	 * @param List<EnumOption> options 单选的选项
	 * @param String           defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnRadio(String col, String colTip, List<EnumOption> options, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnEnumRadio(col, colTip, options, defaultVal));
		return this;
	}

	/**
	 * 指定一个多选框
	 * 
	 * @param String           col 数据库列名
	 * @param String           colTip 在列表页该列的的表头名称
	 * @param List<EnumOption> options 多选的选项
	 * @param String           defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnCheckbox(String col, String colTip, List<EnumOption> options) {
		return this.columnCheckbox(col, colTip, options, "");
	}

	/**
	 * 指定一个多选框
	 * 
	 * @param String           col 数据库列名
	 * @param String           colTip 在列表页该列的的表头名称
	 * @param List<EnumOption> options 多选的选项
	 * @param String           defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnCheckbox(String col, String colTip, List<EnumOption> options, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnEnumCheckBox(col, colTip, options, defaultVal));
		return this;
	}

	/**
	 * 指定一个树形多选框
	 * 
	 * @param String         col 数据库列名
	 * @param String         colTip 在列表页该列的的表头名称
	 * @param List<TreeNode> options
	 *                       详见https://www.antdv.com/components/tree-select-cn
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnTreeCheckbox(String col, String colTip, List<TreeNode> options) {
		return this.columnTreeCheckbox(col, colTip, options, "");
	}

	/**
	 * 指定一个树形多选框
	 * 
	 * @param String         col 数据库列名
	 * @param String         colTip 在列表页该列的的表头名称
	 * @param List<TreeNode> options
	 *                       详见https://www.antdv.com/components/tree-select-cn
	 * @param String         defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnTreeCheckbox(String col, String colTip, List<TreeNode> options, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnEnumTreeCheckBox(col, colTip, options, defaultVal));
		return this;
	}

	/**
	 * 指定一个时间选择输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnTimestamp(String col, String colTip) {
		return this.columnTimestamp(col, colTip, "");
	}

	/**
	 * 指定一个时间选择输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnTimestamp(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnTimestamp(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个时富文本输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnRichText(String col, String colTip) {
		return this.columnRichText(col, colTip, "");
	}

	/**
	 * 指定一个时富文本输入框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnRichText(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnRichText(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个图片选择框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPicture(String col, String colTip) {
		return this.columnPicture(col, colTip, "");
	}

	/**
	 * 指定一个图片选择框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPicture(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnPicture(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个文件选择框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFile(String col, String colTip) {
		return this.columnFile(col, colTip, "");
	}

	/**
	 * 指定一个文件选择框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFile(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnFile(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个图片多选框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPictures(String col, String colTip) {
		return this.columnPicture(col, colTip, "");
	}

	/**
	 * 指定一个图片多选框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPictures(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnPictures(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个文件多选框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFiles(String col, String colTip) {
		return this.columnFiles(col, colTip, "");
	}

	/**
	 * 指定一个文件多选框
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFiles(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnFiles(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个级联选择框
	 * 
	 * @param String             col 数据库列名
	 * @param String             colTip 在列表页该列的的表头名称
	 * @param List<CascaderNode> options
	 *                           详见https://www.antdv.com/components/cascader-cn
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnCascader(String col, String colTip, List<CascaderNode> options) {
		return this.columnCascader(col, colTip, options, "");
	}

	/**
	 * 指定一个级联选择框
	 * 
	 * @param String             col 数据库列名
	 * @param String             colTip 在列表页该列的的表头名称
	 * @param List<CascaderNode> options
	 *                           详见https://www.antdv.com/components/cascader-cn
	 * @param String             defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnCascader(String col, String colTip, List<CascaderNode> options, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnCascader(col, colTip, options, defaultVal));
		return this;
	}

	/**
	 * 指定一个展示框，不会被提交
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnDisplay(String col, String colTip) {
		return this.columnDisplay(col, colTip, "");
	}

	/**
	 * 指定一个展示框，不会被提交
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 没有对应的查询值时的默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnDisplay(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnDisplay(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个隐藏的行，会被提交，可以用来接其它页面传来的参数
	 * 
	 * @param String col 数据库列名
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnHidden(String col) {
		return this.columnHidden(col, "");
	}

	/**
	 * 指定一个隐藏的行，会被提交，可以用来接其它页面传来的参数
	 * 
	 * @param String col 数据库列名
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnHidden(String col, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnHidden(col, defaultVal));
		return this;
	}

	/**
	 * 指定一个子表选择行，子表的第一个设置的列会被作为最终选择的值
	 * 
	 * @param String       col 数据库列名
	 * @param String       colTip 在列表页该列的的表头名称
	 * @param GridListEasy gridListEasy 用于选择的GridList实例
	 * @param String       gridListVModelCol 选中列表项后需要作为表单值的列表列（如列表为select
	 *                     uid,username from user，那么此值为uid时将会将对应选中行的uid值作为对应表单值传给后端）
	 * @param String       gridListDisplayCol 选中列表项后展示在表单中的值对应的列表列（如列表为select
	 *                     uid,username from
	 *                     user，那么此值为username时将会将对应选中行的username值展示在表单上）
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnChildrenChoose(String col, String colTip, GridListEasy gridListEasy,
			String gridListVModelCol, String gridListDisplayCol) {
		return this.columnChildrenChoose(col, colTip, null, gridListVModelCol, gridListDisplayCol, "");
	}

	/**
	 * 指定一个子表选择行，子表的第一个设置的列会被作为最终选择的值
	 * 
	 * @param String       col 数据库列名
	 * @param String       colTip 在列表页该列的的表头名称
	 * @param GridListEasy gridListEasy 用于选择的GridList实例
	 * @param String       gridListVModelCol 选中列表项后需要作为表单值的列表列（如列表为select
	 *                     uid,username from user，那么此值为uid时将会将对应选中行的uid值作为对应表单值传给后端）
	 * @param String       gridListDisplayCol 选中列表项后展示在表单中的值对应的列表列（如列表为select
	 *                     uid,username from
	 *                     user，那么此值为username时将会将对应选中行的username值展示在表单上）
	 * @param String       defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnChildrenChoose(String col, String colTip, GridListEasy gridListEasy,
			String gridListVModelCol, String gridListDisplayCol, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnChildrenChoose(col, colTip, gridListEasy, gridListVModelCol,
				gridListDisplayCol, defaultVal));
		return this;
	}

	/**
	 * 指定一列后方请求数据的按钮
	 * 
	 * @param CreateRowButtonBase buttonItem 需要添加的行按钮对象
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy rowButton(CreateRowButtonBase buttonItem) {
		this.createRowButtonBaseCollection.addItem(buttonItem);
		return this;
	}

	/**
	 * 添加内容变更钩子
	 * 
	 * @param CreateOrEditColumnChangeHook hook 行数据变更时的钩子
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy setChangeHook(CreateOrEditColumnChangeHook hook) {
		this.createOrEditColumnChangeHookCollection.addItem(hook);
		return this;
	}

	/**
	 * 创建一个每行页面跳转按钮
	 * 
	 * @param CreateRowButtonNavigate createRowButtonItem 按钮项
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy rowNavigateButton(CreateRowButtonNavigate createRowButtonItem) {
		this.createRowButtonBaseCollection.addItem(createRowButtonItem);
		return this;
	}

	/**
	 * 创建一个每行API调用按钮
	 * 
	 * @param CreateRowButtonApi createRowButtonItem 按钮项
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy rowApiButton(CreateRowButtonApi createRowButtonItem) {
		this.createRowButtonBaseCollection.addItem(createRowButtonItem);
		return this;
	}

	/**
	 * 创建一个每行文件BLOB下载调用按钮
	 * 
	 * @param CreateRowButtonBlob createRowButtonItem 按钮项
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy rowBlobButton(CreateRowButtonBlob createRowButtonItem) {
		this.createRowButtonBaseCollection.addItem(createRowButtonItem);
		return this;
	}

	/**
	 * 创建一个需要弹窗确认的每行API调用按钮
	 * 
	 * @param CreateRowButtonApiWithConfirm createRowButtonItem 按钮项
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy rowApiButtonWithConfirm(CreateRowButtonApiWithConfirm createRowButtonItem) {
		this.createRowButtonBaseCollection.addItem(createRowButtonItem);
		return this;
	}

	/**
	 * 创建一个每行弹窗展示富文本的模态框的按钮
	 * 
	 * @param CreateRowButtonRichText createRowButtonItem 按钮项
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy rowRichTextButton(CreateRowButtonRichText createRowButtonItem) {
		this.createRowButtonBaseCollection.addItem(createRowButtonItem);
		return this;
	}

	/**
	 * 指定一个图片选择框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPictureLocal(String col, String colTip) {
		return this.columnPictureLocal(col, colTip, "");
	}

	/**
	 * 指定一个图片选择框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPictureLocal(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnPictureLocal(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个文件选择框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFileLocal(String col, String colTip) {
		return this.columnFileLocal(col, colTip, "");
	}

	/**
	 * 指定一个文件选择框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFileLocal(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnFileLocal(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个图片多选框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPicturesLocal(String col, String colTip) {
		return this.columnPicturesLocal(col, colTip, "");
	}

	/**
	 * 指定一个图片多选框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnPicturesLocal(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnPicturesLocal(col, colTip, defaultVal));
		return this;
	}

	/**
	 * 指定一个文件多选框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFilesLocal(String col, String colTip) {
		return this.columnFilesLocal(col, colTip, "");
	}

	/**
	 * 指定一个文件多选框并上传到服务器本地public/antoa_uploads/下
	 * 
	 * @param String col 数据库列名
	 * @param String colTip 在列表页该列的的表头名称
	 * @param String defaultVal 默认值
	 * @return GridCreateFormEasy 返回this以便链式调用
	 */
	public GridCreateFormEasy columnFilesLocal(String col, String colTip, String defaultVal) {
		this.createColumnCollection.addItem(new CreateColumnFilesLocal(col, colTip, defaultVal));
		return this;
	}
}
