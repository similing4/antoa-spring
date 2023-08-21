package com.whuying.antoa.utils.AbstractModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.bean.AntOAApiListResponse;
import com.whuying.antoa.utils.model.UrlParamCalculator;
import com.whuying.antoa.utils.model.UrlParamCalculatorParamItem;

public abstract class ListRowButtonBase implements Serializable {
	private static final long serialVersionUID = -830722548090534588L;

	public String buttonText;
    public String buttonType;
    public String baseUrl;
    public String finalUrl;

    /**
     * 构造函数
     * 
     * @param String baseUrl 按钮点击后需要跳转到或需要调用API的不带参数的URL
     * @param String buttonText 按钮显示的文本
     * @param String buttonType 按钮类型
     */
    public ListRowButtonBase(String baseUrl, String buttonText) {
    	this(baseUrl, buttonText, "primary");
    }
    
    /**
     * 构造函数
     * 
     * @param String baseUrl 按钮点击后需要跳转到或需要调用API的不带参数的URL
     * @param String buttonText 按钮显示的文本
     * @param String buttonType 按钮类型
     */
    public ListRowButtonBase(String baseUrl, String buttonText, String buttonType) {
        this.buttonText = buttonText;
        this.buttonType = buttonType;
        this.baseUrl = baseUrl;
    }

    /**
     * 根据页面参数计算实际调用地址，返回值会被赋值到finalUrl中
     * @param UrlParamCalculator calculator 传入的页面参数的实例
     * @return string 并入到baseURL的URL参数
     */
    public String calcButtonFinalUrl(UrlParamCalculator calculator){
        try {
            List<String> param = new ArrayList<>();
            for(UrlParamCalculatorParamItem item : this.calcButtonParam(calculator))
                param.add(item.key + "=" + item.val);
            return this.baseUrl + "?" + String.join("&", param);
        }catch (Exception exception){
            return null;
        }
    }

    /**
     * 根据页面参数计算实际调用地址，返回值将会被用作finalUrl参数
     * @param UrlParamCalculator $calculator 传入的页面参数的实例
     * @return List<UrlParamCalculatorParamItem> 并入到baseURL的URL参数
     */
    abstract public List<UrlParamCalculatorParamItem> calcButtonParam(UrlParamCalculator calculator);

    /**
     * 根据页面参数计算当前按钮是否显示
     * @param UrlParamCalculator $calculator 传入的页面参数的实例
     * @return boolean 返回真则显示，否则不显示
     */
    abstract public boolean judgeIsShow(UrlParamCalculator $calculator);


	@Override
    public String toString() {
        return jsonSerialize().toString();
    }

    public JSONObject jsonSerialize() {
		JSONObject ret = new JSONObject();
		ret.put("buttonText", buttonText);
		ret.put("buttonType", buttonType);
		ret.put("baseUrl", baseUrl);
		ret.put("finalUrl", finalUrl);
		return ret;
	}

    /**
     * 是否需要使用ApiDetailColumnList接口
     * @return boolean 是否需要
     */
    public boolean isColumnNeedDealApiDetailColumnList(){
        return false;
    }

    /**
     * 是否需要使用ApiUpload接口
     * @return boolean 是否需要
     */
    public boolean isColumnNeedApiUpload(){
        return false;
    }


    /**
     * @param HttpServletRequest request 请求数据
     * @param String uid 登录用户UID
     * @return String 返回给前端的json数据
     * @throws Exception 
     */
    public AntOAApiListResponse dealApiDetailColumnList(HttpServletRequest request, String uid) throws Exception{
    	throw new Exception("接口不存在");
    }
}
