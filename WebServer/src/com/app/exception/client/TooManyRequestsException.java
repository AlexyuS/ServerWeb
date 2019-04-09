package com.app.exception.client;

import com.app.utils.constants.ClientStatusCode;
import com.app.utils.messages.ClientErrorMessages;

public class TooManyRequestsException extends ClientException{

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public TooManyRequestsException() {
		super(ClientErrorMessages.E_429, ClientStatusCode.TOO_MANY_REQ);
	}
	
}
