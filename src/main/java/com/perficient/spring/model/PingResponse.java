package com.perficient.spring.model;

public class PingResponse {
	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public PingResponse(String response) {
		super();
		this.response = response;
	}

	public PingResponse() {
		super();
	}
	
}
