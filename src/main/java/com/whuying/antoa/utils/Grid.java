package com.whuying.antoa.utils;

import com.whuying.antoa.utils.hook.CreateHook;
import com.whuying.antoa.utils.hook.DeleteHook;
import com.whuying.antoa.utils.hook.DetailHook;
import com.whuying.antoa.utils.hook.ListHook;
import com.whuying.antoa.utils.hook.SaveHook;

/**
 * NameSpace: Modules\AntOA\Http\Utils
 * ClassName: Grid
 * 描述: AntOA的CURD配置核心类，用于向用户开放CURD基础配置
 */
public class Grid {
	
    /**
     * GridList对象，用于列表页渲染
     */
    private GridList _list = null;

    /**
     * GridCreateForm对象，用于创建页渲染
     */
    private GridCreateForm _create_form = null;

    /**
     * GridEditForm对象，用于编辑页渲染
     */
    private GridEditForm _edit_form = null;
    /**
     * ListHook对象，列表页钩子
     */
    private ListHook _deal_list = null;
    /**
     * CreateHook对象，创建页钩子
     */
    private CreateHook _deal_create = null;
    /**
     * DetailHook对象，编辑页详情钩子
     */
    private DetailHook _deal_detail = null;
    /**
     * SaveHook对象，编辑页保存钩子
     */
    private SaveHook _deal_save = null;
    /**
     * DeleteHook对象，删除钩子
     */
    private DeleteHook _deal_delete = null;

    /**
     * 初始化GridList对象用于列表页及其接口渲染
     * 
     * @param DBListOperator table 表接口
     * @return GridList 返回GridList对象
     */
    public GridList list(DBListOperator table) {
        this._list = new GridList(table);
        return this._list;
    }

    /**
     * 初始化GridCreateForm对象用于创建页及其接口渲染
     * 
     * @param DBCreateOperator table 表接口
     * @return GridCreateForm 返回GridCreateForm对象
     */
    public GridCreateForm createForm(DBCreateOperator table) {
        this._create_form = new GridCreateForm(table);
        return this._create_form;
    }

    /**
     * 初始化GridEditForm对象用于编辑页及其接口渲染
     * 
     * @param DBEditOperator table 表接口
     * @return GridEditForm 返回GridEditForm对象
     */
    public GridEditForm editForm(DBEditOperator table) {
        this._edit_form = new GridEditForm(table);
        return this._edit_form;
    }

    /**
     * 获取GridList对象
     * @return GridList 返回GridList对象
     */
    public GridList getGridList() {
        return this._list;
    }

    /**
     * 获取GridList对象
     * @return GridCreateForm 返回GridCreateForm对象
     */
    public GridCreateForm getCreateForm() {
        return this._create_form;
    }

    /**
     * 获取GridList对象
     * @return GridEditForm 返回GridEditForm对象
     */
    public GridEditForm getEditForm() {
        return this._edit_form;
    }

    /**
     * 获取ListHook对象
     * @return ListHook
     */
    public ListHook getListHook() {
        return this._deal_list;
    }

    /**
     * 获取创建接口钩子
     * @return CreateHook
     */
    public CreateHook getCreateHook() {
        return this._deal_create;
    }

    /**
     * 获取详情接口钩子
     * @return DetailHook
     */
    public DetailHook getDetailHook() {
        return this._deal_detail;
    }

    /**
     * 获取保存接口钩子
     * @return SaveHook
     */
    public SaveHook getSaveHook() {
        return this._deal_save;
    }

    /**
     * 获取删除接口钩子
     * @return DeleteHook
     */
    public DeleteHook getDeleteHook() {
        return this._deal_delete;
    }

    /**
     * 列表接口后置钩子
     * @param ListHook func 列表接口后置钩子
     * @return Grid 返回this
     */
    public Grid hookList(ListHook func) {
        this._deal_list = func;
        return this;
    }

    /**
     * 创建接口前置钩子
     * @param CreateHook func 钩子函数
     * @return Grid 返回this
     */
    public Grid hookCreate(CreateHook func) {
        this._deal_create = func;
        return this;
    }

    /**
     * 详情接口后置钩子
     * @param DetailHook func 钩子函数
     * @return Grid 返回this
     */
    public Grid hookDetail(DetailHook func) {
        this._deal_detail = func;
        return this;
    }

    /**
     * 保存接口前置钩子
     * @param SaveHook func 钩子函数
     * @return Grid 返回this
     */
    public Grid hookSave(SaveHook func) {
        this._deal_save = func;
        return this;
    }

    /**
     * 删除接口前置钩子
     * @param DeleteHook func 钩子函数
     * @return Grid 返回this
     */
    public Grid hookDelete(DeleteHook func) {
        this._deal_delete = func;
        return this;
    }
}