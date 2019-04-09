package com.app.exception.server;

import com.app.utils.constants.ServerStatusCode;

public abstract class ServerException extends Exception{

	/**
	 * 
	 */
	private final String message;
	
	
	private static final long serialVersionUID = 1L;
	private ServerStatusCode errorCode;
	
	public ServerException(String message,ServerStatusCode code) {
		this.message=message;
		this.errorCode = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ServerStatusCode getError() {
		return errorCode;
	}
	
}
