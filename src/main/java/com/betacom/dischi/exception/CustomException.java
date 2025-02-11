package com.betacom.dischi.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorCode;

	public CustomException() {
		super();
	}

	public CustomException(String message) {
		super(message);
	}

	public String getErrorCode() {
		return errorCode;
	}

	
}
