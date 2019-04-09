package com.app.utils.constants;

public enum ClientStatusCode implements StatusCode {
	BAD_REQUEST("400","Bad reqeuest"),
	NOT_FOUND("404","Not found"),
	METHOD_NOT_ALLOWED("405","Method not allowed"),
	NOT_ACCEPTABLE("406","Not aceptable"),
	TOO_MANY_REQ("429","Too many requests");
	
	private String desc;
	private String errorCode;
	
	private ClientStatusCode(String errorCode,String desc) {
		this.errorCode=errorCode;
		this.desc=desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
	
	public String getCode() {
		return errorCode;
	}
}
