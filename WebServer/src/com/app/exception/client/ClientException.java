package com.app.exception.client;

import com.app.utils.constants.ClientStatusCode;

public abstract class ClientException extends Exception{
	/**
	 * 
	 */
	private final String message;
		
	private static final long serialVersionUID = 1L;
	private ClientStatusCode errorCode;
	
	public ClientException(String message,ClientStatusCode code) {
		this.message=message;
		this.errorCode = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ClientStatusCode getError() {
		return errorCode;
	}
}
