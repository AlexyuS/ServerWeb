package com.app.exception.client;

import com.app.utils.constants.ClientStatusCode;

public class NotFoundException extends ClientException{

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(String body) {
		super(body, ClientStatusCode.NOT_FOUND);
	}
	
}
