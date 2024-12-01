package com.dougfsilva.e_AGE.domain.utilities.exception;

public class ImageStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImageStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImageStorageException(String message) {
		super(message);
	}

}
