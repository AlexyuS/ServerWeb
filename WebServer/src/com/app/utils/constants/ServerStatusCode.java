package com.app.utils.constants;

public enum ServerStatusCode implements StatusCode{
	INTERNAL_ERROR("500","Internal error"),
	NOT_IMPLEMENTED("501","Not implemented"),
	BAD_GATEWAY("503","Bad gatweay"),
	VERSION_NOT_SUPPORTED("505","Version not supported");
	
	private String desc;
	private String errorCode;
	
	private ServerStatusCode(String errorCode,String desc) {
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
