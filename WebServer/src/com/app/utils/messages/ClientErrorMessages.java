package com.app.utils.messages;

public interface ClientErrorMessages {
	public static final String E_400 = "The server cannot or will not process the request due to an apparent client error "
			+ "(e.g., malformed request syntax, size too large, invalid request message framing, or deceptive request routing)";
	public static final String E_404="The requested resource could not be found but may be available in the future."
			+ "Subsequent requests by the client are permissible.";
	public static final String E_405="A request method is not supported for the requested resource; "
			+ "for example, a GET request on a form that requires data to be presented via POST, or a PUT request on a read-only resource.";
	public static final String E_406 ="The requested resource is capable of generating only content not acceptable according to the "
			+ "Accept headers sent in the request.[40] See Content negotiation.";
	public static final String E_429 = "The user has sent too many requests in a given amount of time. Intended for use with rate-limiting schemes";
	
}
