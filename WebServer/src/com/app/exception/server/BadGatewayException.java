package com.app.exception.server;

import com.app.utils.constants.ServerStatusCode;
import com.app.utils.messages.ServerErrorMessages;

public class BadGatewayException extends ServerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadGatewayException(String message, Integer code) {
		super(ServerErrorMessages.E_502, ServerStatusCode.BAD_GATEWAY);
	}
	

}
