package com.dougfsilva.e_AGE.domain.exception;

public class ImageOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImageOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImageOperationException(String message) {
		super(message);
	}

}
