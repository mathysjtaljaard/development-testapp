package org.taljaard.nextgear.services;

import java.util.List;

import org.taljaard.nextgear.exceptions.ContractDataInvalidException;
import org.taljaard.nextgear.exceptions.NoDataFoundException;
import org.taljaard.nextgear.exceptions.PersistanceDeleteException;
import org.taljaard.nextgear.exceptions.PersistanceSaveException;
import org.taljaard.nextgear.exceptions.PersistanceUpdateException;
import org.taljaard.nextgear.model.dto.Contract;
import org.taljaard.nextgear.mybatis.dao.ContractsExample;

public interface IContractService {
	
	public void isDataValid(Contract contract) throws ContractDataInvalidException;
	
	public Contract createNew(Contract contract);
	
	public Contract save(Contract contract) throws PersistanceSaveException;
	
	public List<Contract> findAll() throws NoDataFoundException;
	
	public List<Contract> findAllApproved() throws NoDataFoundException;
	
	public Contract findById(Integer id) throws NoDataFoundException;
	
	public List<Contract> findAllGivenCriteria(ContractsExample criteria) throws NoDataFoundException;
	
	public void deleteById(Integer id) throws PersistanceDeleteException;
	
	public Contract update(Contract contract) throws PersistanceUpdateException;
}
