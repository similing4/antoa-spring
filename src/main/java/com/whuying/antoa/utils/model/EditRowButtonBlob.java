package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.EditRowButtonBase;

public abstract class EditRowButtonBlob extends EditRowButtonBase {
	private static final long serialVersionUID = 5123272594005618325L;
	public String downloadFilename;
    public String type = "EditRowButtonBlob";

	
	/**
     * EditRowButtonBlob constructor.
     * @param String baseUrl
     * @param String buttonText
     * @param String downloadFilename
     */
    public EditRowButtonBlob(String baseUrl, String buttonText, String downloadFilename) {
    	this(baseUrl, buttonText, downloadFilename, "primary");
    }
    /**
     * EditRowButtonBlob constructor.
     * @param String baseUrl
     * @param String buttonText
     * @param String downloadFilename
     * @param String $buttonType
     */
    public EditRowButtonBlob(String baseUrl, String buttonText, String downloadFilename, String buttonType) {
        super(baseUrl, buttonText, buttonType);
        this.downloadFilename = downloadFilename;
    }

	@Override
	public JSONObject jsonSerialize() {
		JSONObject ret = super.jsonSerialize();
		ret.put("type", this.type);
		ret.put("downloadFilename", downloadFilename);
		return ret;
	}
    
    @Override
    public String toString() {
    	return this.jsonSerialize().toString();
    }
}
