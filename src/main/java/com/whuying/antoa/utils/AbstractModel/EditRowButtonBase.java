package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.model.UrlParamCalculator;

public abstract class EditRowButtonBase implements Serializable {
	private static final long serialVersionUID = 956227119219580870L;

	public String bindCol;
    public String apiUrl;
    public String buttonText;
    public String buttonType;
    
    /**
     * 构造函数
     * @param String $bindCol 按钮在编辑页所处行的列名
     * @param String $apiUrl 按钮点击后需要调用的不带参数的API地址。或者是直接的跳转页面的地址，受浏览器限制，不支持根据页面参数拼接跳转的页面地址
     * @param String $buttonText 按钮显示的文本
     */
    public EditRowButtonBase(String bindCol, String apiUrl, String buttonText) {
    	this(bindCol, apiUrl, buttonText, "primary");
    }
    
    /**
     * 构造函数
     * @param String $bindCol 按钮在编辑页所处行的列名
     * @param String $apiUrl 按钮点击后需要调用的不带参数的API地址。或者是直接的跳转页面的地址，受浏览器限制，不支持根据页面参数拼接跳转的页面地址
     * @param String $buttonText 按钮显示的文本
     * @param String $buttonType 按钮类型
     */
    public EditRowButtonBase(String bindCol, String apiUrl, String buttonText, String buttonType) {
        this.bindCol = bindCol;
        this.apiUrl = apiUrl;
        this.buttonText = buttonText;
        this.buttonType = buttonType;
    }

    /**
     * 根据页面参数计算当前按钮是否显示（只能获取到页面参数）
     * @param UrlParamCalculator $calculator 传入的页面参数的实例
     * @return boolean 返回真则显示，否则不显示
     */
    abstract public boolean judgeIsShow(UrlParamCalculator calculator);

	@Override
    public String toString() {
        return jsonSerialize().toString();
    }
	
    public JSONObject jsonSerialize() {
    	JSONObject ret = new JSONObject();
    	ret.put("bindCol", bindCol);
    	ret.put("apiUrl", apiUrl);
    	ret.put("buttonText", buttonText);
    	ret.put("buttonType", buttonType);
        return ret;
    }
}
