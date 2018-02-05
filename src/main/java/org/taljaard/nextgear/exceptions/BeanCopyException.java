package org.taljaard.nextgear.exceptions;

public class BeanCopyException extends RuntimeException {

	private static final long serialVersionUID = 1744856852056836631L;

	public BeanCopyException(String message) {
		super(message);
	}

	public BeanCopyException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanCopyException(Throwable cause) {
		super(cause);
	}
}
