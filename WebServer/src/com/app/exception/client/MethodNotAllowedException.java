package com.app.exception.client;

import com.app.utils.constants.ClientStatusCode;
import com.app.utils.messages.ClientErrorMessages;

public class MethodNotAllowedException extends ClientException {
	
	/**
	 * 
	 */

	
	private static final long serialVersionUID = 1L;

	public MethodNotAllowedException() {
		super(ClientErrorMessages.E_405, ClientStatusCode.METHOD_NOT_ALLOWED);
	}
	
}
