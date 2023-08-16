package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.CreateRowButtonBase;

public abstract class CreateRowButtonBlob extends CreateRowButtonBase {

	private static final long serialVersionUID = 8133566754528066610L;
	
	public String downloadFilename;
    public String type = "CreateRowButtonBlob";

	public CreateRowButtonBlob(String baseUrl, String buttonText, String downloadFilename) {
		this(baseUrl, buttonText, downloadFilename, "primary");
	}
	
    /**
     * CreateRowButtonBlob constructor.
     * @param String baseUrl
     * @param String buttonText
     * @param String downloadFilename
     * @param String buttonType
     */
    public CreateRowButtonBlob(String baseUrl, String buttonText, String downloadFilename, String buttonType) {
        super(baseUrl, buttonText, buttonType);
        this.downloadFilename = downloadFilename;
    }

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
