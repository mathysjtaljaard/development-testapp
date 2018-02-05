package org.taljaard.nextgear.services.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.taljaard.nextgear.enums.ContractStatus;
import org.taljaard.nextgear.enums.ContractType;
import org.taljaard.nextgear.exceptions.BeanCopyException;
import org.taljaard.nextgear.model.dto.Contract;
import org.taljaard.nextgear.mybatis.dao.Contracts;

public class ContractServiceUtilities {

	private static BigDecimal SALES_CONTRACT_AMOUNT = new BigDecimal("50000");
	private static int VAL_COMPARED_TO_GIVEN_PARAMETER_IS_LESS = -1;
	private static int VAL_COMPARED_TO_GIVEN_PARAMETER_IS_GREATER = 1;
	private static int VAL_COMPARED_TO_GIVEN_PARAMETER_IS_EQUAL = 0;

	private static String AMOUNT_REQUESTED_INVALID_MESSAGE = "Contract amount requested invalid: Value should be greater than 0.";
	private static String CONTRACT_NAME_BLANK_MESSAGE = "Contract name invalid: Contract name cannot be empty.";
	private static String CONTRACT_BUSINESS_NUMBER_BLANK_MESSAGE = "Contract business number invalid: Contract business number cannot be blank.";

	public static List<String> validate(Contract contract) {

		List<String> validationMessages = new ArrayList<>();

		if (contract.getAmountRequested() == null
				|| (BigDecimal.ZERO
						.compareTo(contract.getAmountRequested()) == VAL_COMPARED_TO_GIVEN_PARAMETER_IS_GREATER)
				|| (BigDecimal.ZERO
						.compareTo(contract.getAmountRequested()) == VAL_COMPARED_TO_GIVEN_PARAMETER_IS_EQUAL)) {
			validationMessages.add(AMOUNT_REQUESTED_INVALID_MESSAGE);
		}

		if (StringUtils.isBlank(contract.getName())) {
			validationMessages.add(CONTRACT_NAME_BLANK_MESSAGE);
		}

		if (StringUtils.isBlank(contract.getBusinessNumber())) {
			validationMessages.add(CONTRACT_BUSINESS_NUMBER_BLANK_MESSAGE);
		}

		return validationMessages;
	}

	public static String determineType(Contract contract) {
		if ((SALES_CONTRACT_AMOUNT
				.compareTo(contract.getAmountRequested()) == VAL_COMPARED_TO_GIVEN_PARAMETER_IS_LESS)
				|| (SALES_CONTRACT_AMOUNT
						.compareTo(contract.getAmountRequested()) == VAL_COMPARED_TO_GIVEN_PARAMETER_IS_EQUAL)) {
			return ContractType.Sales.toString();
		}

		return ContractType.Express.toString();
	}

	public static String determineStatus(Contract contract) {

		if ((BigDecimal.ZERO.compareTo(contract.getAmountRequested()) == VAL_COMPARED_TO_GIVEN_PARAMETER_IS_LESS)
				&& (BigDecimal.ZERO
						.compareTo(contract.getAmountRequested()) != VAL_COMPARED_TO_GIVEN_PARAMETER_IS_EQUAL)) {
			return ContractStatus.Approved.toString();
		}

		return ContractStatus.Denied.toString();
	}

	public static List<Contract> convertToDTOList(List<Contracts> contractDAOList) throws BeanCopyException {
		List<Contract> contractDTOList = new ArrayList<>();

		for (Contracts contractDAO : contractDAOList) {
			contractDTOList.add(convertToDTO(contractDAO));
		}

		return contractDTOList;
	}

	public static List<Contracts> convertToDAOList(List<Contract> contractDTOList) throws BeanCopyException {
		List<Contracts> contractDAOList = new ArrayList<>();

		for (Contract contractDTO : contractDTOList) {
			contractDAOList.add(convertToDAO(contractDTO));
		}
		return contractDAOList;
	}

	public static Contract convertToDTO(Contracts contractDAO) throws BeanCopyException {
		Contract contractDTO = new Contract();
		try {
			BeanUtils.copyProperties(contractDTO, contractDAO);
		} catch (Exception ex) {
			throw new BeanCopyException("Unable to copy DAO to DTO", ex);
		}

		return contractDTO;
	}

	public static Contracts convertToDAO(Contract contractDTO) throws BeanCopyException {
		Contracts contractsDAO = new Contracts();
		try {
			BeanUtils.copyProperties(contractsDAO, contractDTO);
		} catch (Exception ex) {
			throw new BeanCopyException("Unable to copy DTO to DAO", ex);
		}

		return contractsDAO;
	}
}
