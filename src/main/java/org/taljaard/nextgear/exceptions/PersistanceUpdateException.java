package org.taljaard.nextgear.exceptions;

public class PersistanceUpdateException extends RuntimeException {

	private static final long serialVersionUID = -2098633051061099344L;
	
	public PersistanceUpdateException(String message) {
		super(message);
	}

	public PersistanceUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistanceUpdateException(Throwable cause) {
		super(cause);
	}

}
