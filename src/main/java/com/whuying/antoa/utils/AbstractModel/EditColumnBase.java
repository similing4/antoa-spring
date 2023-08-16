package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.whuying.antoa.bean.AntOAApiListResponse;

/**
 * ClassName: EditColumnBase
 * 描述: 可用于编辑页的选项列对象
 */
public abstract class EditColumnBase implements Serializable {
	private static final long serialVersionUID = -3452508454751154878L;
	/**
     * 列对应的字段
     */
    public String col;
    /**
     * 列对应的字段Label
     */
    public String tip;
    /**
     * 列对应的默认值
     */
    @JSONField(name = "default")
    public String defaultVal;

    /**
     * 表列构造方法基类
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param string defaultVal 列对应默认值
     */
    public EditColumnBase(String col, String tip) {
    	this(col, tip, "");
    }
    
    /**
     * 表列构造方法基类
     * @param String col 列对应的字段
     * @param String tip 列对应的字段Label
     * @param string defaultVal 列对应默认值
     */
    public EditColumnBase(String col, String tip, String defaultVal) {
        this.col = col;
        this.tip = tip;
        this.defaultVal = defaultVal;
    }
    
	@Override
	public String toString() {
        return jsonSerialize().toString();
    }

    public JSONObject jsonSerialize() {
    	JSONObject ret = new JSONObject();
    	ret.put("col", col);
    	ret.put("tip", tip);
    	ret.put("default", defaultVal);
    	return ret;
    }

    /**
     * 当客户端数据传入时
     * @param JSONObject req
     * @return Object 返回需要接下来
     */
    public Object onGuestVal(JSONObject req, String uid){
        return req.get(col);
    }

    /**
     * 当服务端数据传出时
     * @param JSONObject res
     * @param String uid
     * @return Object 返回需要接下来
     */
    public Object onServerVal(JSONObject res, String uid){
        return res.get(col);
    }

    /**
     * 是否需要使用ApiDetailColumnList接口（接口中type为edit时才会调用）
     * @return boolean 是否需要
     */
    public boolean isColumnNeedDealApiDetailColumnList(){
        return false;
    }

    /**
     * 是否需要使用ApiUpload接口（接口中type为edit时才会调用）
     * @return boolean 是否需要
     */
    public boolean isColumnNeedDealApiUpload(){
        return false;
    }

    /**
     * @param HttpServletRequest request 请求数据
     * @param String uid 登录用户UID
     * @return AntOAApiListResponse 返回给前端的json数据
     */
    public AntOAApiListResponse dealApiDetailColumnList(HttpServletRequest request, String uid) throws Exception{
        throw new Exception("接口不存在");
    }

    /**
     * @param Request request 请求数据
     * @param String uid 登录用户UID
     * @return String 返回给前端的json数据
     * @throws Exception 
     */
    public String dealApiUpload(HttpServletRequest request, String uid) throws Exception{
    	throw new Exception("接口不存在");
    }
}

