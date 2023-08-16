package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListHeaderButtonBase;

public abstract class ListHeaderButtonBlob extends ListHeaderButtonBase {
	
	private static final long serialVersionUID = -9201682129253776345L;
    public String type = "ListHeaderButtonBlob";
	
	public String downloadFilename;
    /**
     * ListHeaderButtonBlob constructor.
     * @param $baseUrl 调用接口的url
     * @param $buttonText 按钮文本
     * @param $downloadFilename 按钮下载文件名
     */
    public ListHeaderButtonBlob(String baseUrl, String buttonText, String downloadFilename) {
    	this(baseUrl, buttonText, downloadFilename, "primary");
    }

    /**
     * ListHeaderButtonBlob constructor.
     * @param baseUrl 调用接口的url
     * @param buttonText 按钮文本
     * @param downloadFilename 按钮下载文件名
     * @param string buttonType 按钮类型
     */
    public ListHeaderButtonBlob(String baseUrl, String buttonText, String downloadFilename, String buttonType) {
        super(baseUrl, buttonText, buttonType);
        this.downloadFilename = downloadFilename;
    }

	@Override
	public JSONObject jsonSerialize() {
		JSONObject ret = super.jsonSerialize();
		ret.put("type", this.type);
		ret.put("downloadFilename", this.downloadFilename);
		return ret;
	}

	@Override
	public String toString() {
		return this.jsonSerialize().toString();
	}
}
