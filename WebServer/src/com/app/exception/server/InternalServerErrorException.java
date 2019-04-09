package com.app.exception.server;

import com.app.utils.constants.ServerStatusCode;
import com.app.utils.messages.ServerErrorMessages;

public class InternalServerErrorException extends ServerException{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
		super(ServerErrorMessages.E_500, ServerStatusCode.INTERNAL_ERROR);
	}

}
