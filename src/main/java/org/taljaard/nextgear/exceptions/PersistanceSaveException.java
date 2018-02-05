package org.taljaard.nextgear.exceptions;

public class PersistanceSaveException extends RuntimeException {

	private static final long serialVersionUID = 2243535453709124196L;

	public PersistanceSaveException(String message) {
		super(message);
	}

	public PersistanceSaveException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistanceSaveException(Throwable cause) {
		super(cause);
	}
}
