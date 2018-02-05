package org.taljaard.nextgear.services.utils;

import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.taljaard.nextgear.enums.ContractStatus;
import org.taljaard.nextgear.enums.ContractType;
import org.taljaard.nextgear.model.dto.Contract;

@RunWith(JUnit4.class)
public class ContractServiceUtilitiesTest {

	private Contract invalidName;
	private Contract invalidBusinessNumber;
	private Contract invalidRequestedAmount;
	private Contract expressContract;
	private Contract salesContract;

	@Before
	public void setup() throws Exception {
		invalidName = createGenericContract();
		invalidName.setName("");

		invalidBusinessNumber = createGenericContract();
		invalidBusinessNumber.setBusinessNumber("");

		invalidRequestedAmount = createGenericContract();
		invalidRequestedAmount.setAmountRequested(null);
		
		expressContract = createGenericContract();
		salesContract = createGenericContract();
		salesContract.setAmountRequested(BigDecimal.valueOf(55000));
	}

	@Test
	public void testValidateContractNameEmpty() throws Exception {
		List<String> messages = ContractServiceUtilities.validate(invalidName);

		assertEquals(1, messages.size());
		assertTrue(StringUtils.contains(messages.get(0), "Contract name invalid"));

	}

	@Test
	public void testValidateContractBusinessNumberEmpty() throws Exception {
		List<String> messages = ContractServiceUtilities.validate(invalidBusinessNumber);

		assertEquals(1, messages.size());
		assertTrue(StringUtils.contains(messages.get(0), "Contract business number invalid"));

	}
	
	@Test
	public void testValidateContractRequestedAmountNull() throws Exception {
		List<String> messages = ContractServiceUtilities.validate(invalidRequestedAmount);

		assertEquals(1, messages.size());
		assertTrue(StringUtils.contains(messages.get(0), "Contract amount requested invalid"));

	}
	
	@Test
	public void testValidateContractRequestedAmountZero() throws Exception {
		invalidRequestedAmount.setAmountRequested(BigDecimal.ZERO);
		List<String> messages = ContractServiceUtilities.validate(invalidRequestedAmount);

		assertEquals(1, messages.size());
		assertTrue(StringUtils.contains(messages.get(0), "Contract amount requested invalid"));

	}

	@Test
	public void testContractTypeRetunsExpressRequestedAmountLessThan50K() {
		String contractType = ContractServiceUtilities.determineType(expressContract);
		assertTrue(StringUtils.equals(contractType, ContractType.Express.toString()));
	}
	
	@Test
	public void testContractTypeRetunsSalesRequestedAmountGreaterThan50K() {
		salesContract.setAmountRequested(BigDecimal.valueOf(60000));
		String contractType = ContractServiceUtilities.determineType(salesContract);
		assertTrue(StringUtils.equals(contractType, ContractType.Sales.toString()));
	}
	
	@Test
	public void testContractTypeRetunsSalesRequestedAmountEqualTo50K() {
		salesContract.setAmountRequested(BigDecimal.valueOf(50000));
		String contractType = ContractServiceUtilities.determineType(salesContract);
		assertTrue(StringUtils.equals(contractType, ContractType.Sales.toString()));
	}
	
	@Test
	public void testContractStatusRetunsApprovedRequestedAmountGreaterZero() {
		String contractStatus = ContractServiceUtilities.determineStatus(createGenericContract());
		assertTrue(StringUtils.equals(contractStatus, ContractStatus.Approved.toString()));
	}
	
	@Test
	public void testContractStatusRetunsDeniedRequestedAmountEqualZero() {
		invalidRequestedAmount.setAmountRequested(BigDecimal.ZERO);
		String contractStatus = ContractServiceUtilities.determineStatus(invalidRequestedAmount);
		assertTrue(StringUtils.equals(contractStatus, ContractStatus.Denied.toString()));
	}
	
	private Contract createGenericContract() {
		Contract contract = new Contract();
		contract.setName("test-1");
		contract.setBusinessNumber("c-b-1234");
		contract.setAmountRequested(BigDecimal.valueOf(6000));
		return contract;
	}
}
