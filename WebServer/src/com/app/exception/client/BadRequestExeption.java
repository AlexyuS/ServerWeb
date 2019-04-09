package com.app.exception.client;

import com.app.utils.constants.ClientStatusCode;
import com.app.utils.messages.ClientErrorMessages;

public class BadRequestExeption extends ClientException {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public BadRequestExeption() {
		super(ClientErrorMessages.E_400, ClientStatusCode.BAD_REQUEST);
	}
}
