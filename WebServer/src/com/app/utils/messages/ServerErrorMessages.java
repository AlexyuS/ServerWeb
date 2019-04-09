package com.app.utils.messages;

public interface ServerErrorMessages {
	
	public static final String E_500="A generic error message, given when an unexpected condition "
			+ "was encountered and no more specific message is suitable";
	public static final String E_501="The server either does not recognize the request method, or it lacks the ability to fulfil the request."
			+ " Usually this implies future availability (e.g., a new feature of a web-service API)";
	public static final String E_502="The server cannot handle the request (because it is overloaded or down for maintenance)."
			+ " Generally, this is a temporary state";
	public static final String E_505="The server does not support the HTTP protocol version used in the request";
}
