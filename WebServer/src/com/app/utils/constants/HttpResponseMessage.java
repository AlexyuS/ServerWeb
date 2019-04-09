package com.app.utils.constants;

public enum HttpResponseMessage {
	STATUS_LINE(""),DATE("Date"),SERVER("Server"),LAST_MODIFIED("Last-Modified"),CONTENT_LENGTH("Content-Length"),CONTENT_TYPE("Content-Type"),CONNECTION("Connection");
	
	private String description;
	
    HttpResponseMessage(String description) {
		this.description=description;
	}

	public String getDescription() {
		return description;
	}
}
