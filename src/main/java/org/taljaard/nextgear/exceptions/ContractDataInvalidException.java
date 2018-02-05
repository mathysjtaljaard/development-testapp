package org.taljaard.nextgear.exceptions;

public class ContractDataInvalidException extends RuntimeException {

	private static final long serialVersionUID = -3879375663093159157L;

	public ContractDataInvalidException(String message) {
		super(message);
	}

	public ContractDataInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContractDataInvalidException(Throwable cause) {
		super(cause);
	}

}
