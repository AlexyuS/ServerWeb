package com.app.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.app.utils.constants.HttpResponseMessage;
import com.app.utils.constants.StatusCode;

public class HttpResponse {
	public static String VERSION = "HTTP/1.1";

	private Map<HttpResponseMessage, String> headerLines = new HashMap<>();
	
	private String body="";
	private String statusLine="";

	private void setDate(String date) {
		headerLines.put(HttpResponseMessage.DATE, date);
	}

	private void setContentType(String contentType) {
		headerLines.put(HttpResponseMessage.CONTENT_TYPE, contentType);
	}

	private void setServer(String server) {
		headerLines.put(HttpResponseMessage.SERVER, server);
	}

	private void setStatusLine(StatusCode statusCode, String version) {
		statusLine = VERSION + " " + statusCode.getCode() + " " + statusCode;
	}

	private void setBody(String body) {
		this.body = body;
	};
	
	

	public String printHeader() {
		String header = "";
		header += statusLine + '\n';
		header += headerLines.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue())
				.collect(Collectors.joining("\n"));
		header += '\n';
		header += body;

		return header;
	};
	
	public HttpResponseBuilder getBuilder() {
		return new HttpResponseBuilder(this);
	}

	public class HttpResponseBuilder {
		HttpResponse header;

		public HttpResponseBuilder(HttpResponse headerMsg) {
			this.header=headerMsg;
		}

		public HttpResponse build(StatusCode statusCode) {
			header.setStatusLine(statusCode, VERSION);
			return header;
		}

		public HttpResponse build(StatusCode statusCode, String body) {
			header.setStatusLine(statusCode, VERSION);
			header.setBody(body);
			return header;
		}

		public HttpResponseBuilder setServer(String server) {
			header.setServer(server);
			return this;
		}

		public HttpResponseBuilder setContentType(String contentType) {
			header.setContentType(contentType);
			return this;
		}

		public HttpResponseBuilder setDate(String date) {
			header.setDate(date);
			return this;
		}

		public HttpResponseBuilder setVersion(String httpVersion) {
			header.setServer(httpVersion);
			return this;
		}

		public HttpResponseBuilder setBody(String body) {
			header.setBody(body);
			return this;
		}

	}
}
