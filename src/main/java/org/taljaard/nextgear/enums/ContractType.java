package org.taljaard.nextgear.enums;

public enum ContractType {
	Sales(1, "Sales Contract"), Express(2, "Express Contract");

	private int contractMappingValue;
	private String contractTypeDisplayValue;

	private ContractType(int mappingValue, String displayValue) {
		this.contractMappingValue = mappingValue;
		this.contractTypeDisplayValue = displayValue;
	}

	public String getContractTypeDisplayValue() {
		return this.contractTypeDisplayValue;
	}

	public int getContractMappingValue() {
		return this.contractMappingValue;
	}
}
