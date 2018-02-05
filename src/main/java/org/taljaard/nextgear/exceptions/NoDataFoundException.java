package org.taljaard.nextgear.exceptions;

public class NoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = -9202880378081509929L;
	
	public NoDataFoundException(String message) {
		super(message);
	}

	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDataFoundException(Throwable cause) {
		super(cause);
	}

}
