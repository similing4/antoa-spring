package com.whuying.antoa.utils;

public class HttpException extends Exception {
	private static final long serialVersionUID = 2072146557537784664L;
	public int httpResponseCode;
	public HttpException(String message, int httpResponseCode) {
		super(message);
		this.httpResponseCode = httpResponseCode;
	}
}
