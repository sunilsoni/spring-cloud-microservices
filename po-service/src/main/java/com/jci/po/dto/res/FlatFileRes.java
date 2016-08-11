package com.jci.po.dto.res;

public class FlatFileRes {

	private String body;
	private String message;
	
	private String contentType;
	private String statusCode;
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ProcessPoRes [body=" + body + ", message=" + message + ", contentType=" + contentType + ", statusCode="
				+ statusCode + "]";
	}


	
}
