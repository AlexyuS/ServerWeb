package com.app.exception.client;

import com.app.utils.constants.ClientStatusCode;
import com.app.utils.messages.ClientErrorMessages;

public class NotAcceptableException extends ClientException{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public NotAcceptableException(String message, Integer code) {
		super(ClientErrorMessages.E_406, ClientStatusCode.NOT_ACCEPTABLE);
	}

}
