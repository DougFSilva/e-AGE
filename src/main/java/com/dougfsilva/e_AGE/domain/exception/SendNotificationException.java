package com.dougfsilva.e_AGE.domain.exception;

public class SendNotificationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SendNotificationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendNotificationException(String message) {
		super(message);
	}

}
