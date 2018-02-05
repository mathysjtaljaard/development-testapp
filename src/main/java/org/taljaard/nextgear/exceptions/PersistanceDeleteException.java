package org.taljaard.nextgear.exceptions;

public class PersistanceDeleteException extends RuntimeException {

	private static final long serialVersionUID = -2030298357735387908L;

	public PersistanceDeleteException(String message) {
		super(message);
	}

	public PersistanceDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistanceDeleteException(Throwable cause) {
		super(cause);
	}

}
