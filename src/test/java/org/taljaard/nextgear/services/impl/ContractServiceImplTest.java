package org.taljaard.nextgear.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.taljaard.nextgear.enums.ContractStatus;
import org.taljaard.nextgear.enums.ContractType;
import org.taljaard.nextgear.exceptions.ContractDataInvalidException;
import org.taljaard.nextgear.exceptions.NoDataFoundException;
import org.taljaard.nextgear.exceptions.PersistanceDeleteException;
import org.taljaard.nextgear.model.dto.Contract;
import org.taljaard.nextgear.services.IContractService;
import org.taljaard.nextgear.tests.base.ITBase;

public class ContractServiceImplTest extends ITBase {

	@Autowired
	private IContractService contractService;

	@Test(expected = ContractDataInvalidException.class)
	public void testContractServiceIsDataValidThrowsContractDataInvalidException() throws Exception {
		Contract contract = new Contract();
		contractService.isDataValid(contract);
	}

	@Test
	public void testContractServiceIsDataValidSuccessNoExeption() throws Exception {
		Contract contract = createNewSalesContractDTO();
		contractService.isDataValid(contract);
	}

	@Test
	public void testContractServiceCreateNewReturnApprovedSalesContract() throws Exception {
		Contract contract = contractService.createNew(createNewSalesContractDTO());

		assertTrue(StringUtils.equals(ContractStatus.Approved.toString(), contract.getStatus()));
		assertTrue(StringUtils.equals(ContractType.Sales.toString(), contract.getType()));
		assertNotNull(contract.getActivitationDate());
		assertNotNull(contract.getCreateDate());
		assertNotNull(contract.getUpdateDate());
	}

	@Test
	public void testContractServiceCreateNewReturnApprovedExpressContract() throws Exception {
		Contract contract = contractService.createNew(createNewExpressContractDTO());

		assertTrue(StringUtils.equals(ContractStatus.Approved.toString(), contract.getStatus()));
		assertTrue(StringUtils.equals(ContractType.Express.toString(), contract.getType()));
		assertNotNull(contract.getActivitationDate());
		assertNotNull(contract.getCreateDate());
		assertNotNull(contract.getUpdateDate());
	}

	@Test(expected = ContractDataInvalidException.class)
	public void testContractServiceCreateNewThrowsContractDataInvalidException() throws Exception {
		Contract denied = createNewExpressContractDTO();
		denied.setAmountRequested(BigDecimal.ZERO);
		contractService.createNew(denied);
	}

	@Test
	public void testContractServiceSaveNewContractSuccess() throws Exception {
		Contract contract = createNewSalesContractDTO();
		Contract savedContract = contractService.save(contract);

		assertNotNull(savedContract.getId());
		assertNotNull(savedContract.getActivitationDate());
		assertNotNull(savedContract.getCreateDate());
		assertNotNull(savedContract.getUpdateDate());

		assertTrue(StringUtils.equals(ContractType.Sales.toString(), savedContract.getType()));
		assertTrue(StringUtils.equals(ContractStatus.Approved.toString(), savedContract.getStatus()));
	}

	@Test(expected = ContractDataInvalidException.class)
	public void testContractServiceSaveNewContractThrowsContractDataInvalidException() throws Exception {
		Contract invalidContract = createNewExpressContractDTO();
		invalidContract.setName(null);
		invalidContract.setAmountRequested(null);
		
		contractService.save(invalidContract);
	}

	@Test
	public void testContractServiceUpdateContractSuccess() throws Exception {
		Contract retrievedContract = contractService.findById(Integer.valueOf(2));
		retrievedContract.setName("Updated-Contract-name-test");
		
		Contract updatedContract = contractService.update(retrievedContract);
		assertTrue(StringUtils.equals(updatedContract.getName(), retrievedContract.getName()));
		assertTrue(retrievedContract.getId().compareTo(updatedContract.getId()) == 0);
	}

	@Test(expected = ContractDataInvalidException.class)
	public void testContractServiceUpdateContractThrowsContractDataInvalidException() throws Exception {
		Contract retrievedContract = contractService.findById(Integer.valueOf(1));
		retrievedContract.setName("");
		
		contractService.update(retrievedContract);
	}

	@Test(expected = NoDataFoundException.class)
	public void testContractServiceDeleteContractSuccess() throws Exception {
		contractService.deleteById(Integer.valueOf(1));
		
		//Note: this is to validate that the data entry doesn't exist
		contractService.findById(Integer.valueOf(1));
	}

	@Test(expected = PersistanceDeleteException.class)
	public void testContractServiceDeleteContractThrowsPersistanceDeleteException() throws Exception {
		contractService.deleteById(Integer.valueOf(0));
	}

	@Test
	public void testContractServiceFindByContractIdSuccess() throws Exception {
		Contract savedContract = contractService.save(createNewSalesContractDTO());
		
		Contract requestedContract = contractService.findById(savedContract.getId());
		
		assertNotNull(requestedContract.getId());
		assertNotNull(requestedContract.getActivitationDate());
		assertNotNull(requestedContract.getCreateDate());
		assertNotNull(requestedContract.getUpdateDate());
		
		assertTrue(StringUtils.equals(ContractType.Sales.toString(), savedContract.getType()));
		assertTrue(StringUtils.equals(ContractStatus.Approved.toString(), savedContract.getStatus()));
		assertTrue(savedContract.getId().compareTo(requestedContract.getId()) == 0);
		
		contractService.deleteById(savedContract.getId());
	}

	@Test(expected = NoDataFoundException.class)
	public void testContractServiceFindByContractIdThrowsNoDataFoundException() throws Exception {
		contractService.findById(Integer.valueOf(0));
	}

	@Test
	public void testContractServiceFindAllContractsSuccess() throws Exception {
		List<Contract> allContracts = contractService.findAll();
		assertEquals(5, allContracts.size());
	}

	@Test
	public void testContractServiceFindAllApprovedContractsSuccess() throws Exception {
		List<Contract> allContracts = contractService.findAllApproved();
		assertEquals(3, allContracts.size());
	}
}
