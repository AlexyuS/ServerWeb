package com.app.exception.server;

import com.app.utils.constants.ServerStatusCode;
import com.app.utils.messages.ServerErrorMessages;

public class HttpVersionNotSupportedExcepption extends ServerException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public HttpVersionNotSupportedExcepption(String message, Integer code) {
		super(ServerErrorMessages.E_505, ServerStatusCode.VERSION_NOT_SUPPORTED);
	}
	

}
