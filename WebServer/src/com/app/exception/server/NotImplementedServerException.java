package com.app.exception.server;

import com.app.utils.constants.ServerStatusCode;
import com.app.utils.messages.ServerErrorMessages;

public class NotImplementedServerException extends ServerException {


	/**
	 * 
	 */

	
	private static final long serialVersionUID = 1L;

	public NotImplementedServerException() {
		super(ServerErrorMessages.E_501, ServerStatusCode.NOT_IMPLEMENTED);
	}
}
