package com.whuying.antoa.utils.model;

import com.alibaba.fastjson.JSONObject;
import com.whuying.antoa.utils.AbstractModel.ListRowButtonBase;

/**
 * ClassName: ListRowButtonBlob 描述: 列表页每行直接根据API下载文件（BLOB流）实体类
 */
public abstract class ListRowButtonBlob extends ListRowButtonBase {

	private static final long serialVersionUID = 5828867468481679639L;

	public String downloadFilename;
	public String type = "ListRowButtonBlob";

	/**
	 * ListHeaderButtonBlob constructor.
	 * 
	 * @param String baseUrl
	 * @param String buttonText
	 * @param String downloadFilename
	 * @param string buttonType
	 */
	public ListRowButtonBlob(String baseUrl, String buttonText, String downloadFilename) {
		this(baseUrl, buttonText, downloadFilename, "primary");
	}

	/**
	 * ListHeaderButtonBlob constructor.
	 * 
	 * @param String baseUrl
	 * @param String buttonText
	 * @param String downloadFilename
	 * @param string buttonType
	 */
	public ListRowButtonBlob(String baseUrl, String buttonText, String downloadFilename, String buttonType) {
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
