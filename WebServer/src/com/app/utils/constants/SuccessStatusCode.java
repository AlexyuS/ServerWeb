package com.app.utils.constants;

public enum SuccessStatusCode implements StatusCode {
	SUCCESS("200","Success");

	private String errorCode;
	private String msg;
	
	SuccessStatusCode(String code,String msg) {
		this.errorCode=code;
		this.msg=msg;
	}
	@Override
	public String getCode() {
		return errorCode;
	}
	
	@Override
	public String toString() {
		return msg;
	}

}
