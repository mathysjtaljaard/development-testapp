package org.taljaard.nextgear.enums;

public enum ContractStatus {

	Approved("Approved"), Denied("Denied");

	private String _statusDisplayValue;

	ContractStatus(String statusDisplayValue) {
		_statusDisplayValue = statusDisplayValue;
	}

	public String getStatusDisplayValue() {
		return _statusDisplayValue;
	}
}
