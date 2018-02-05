package org.taljaard.nextgear.services.impl;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taljaard.nextgear.enums.ContractStatus;
import org.taljaard.nextgear.exceptions.ContractDataInvalidException;
import org.taljaard.nextgear.exceptions.NoDataFoundException;
import org.taljaard.nextgear.exceptions.PersistanceDeleteException;
import org.taljaard.nextgear.exceptions.PersistanceSaveException;
import org.taljaard.nextgear.exceptions.PersistanceUpdateException;
import org.taljaard.nextgear.model.dto.Contract;
import org.taljaard.nextgear.mybatis.dao.Contracts;
import org.taljaard.nextgear.mybatis.dao.ContractsExample;
import org.taljaard.nextgear.mybatis.dao.ContractsExample.Criteria;
import org.taljaard.nextgear.mybatis.mapper.ContractsMapper;
import org.taljaard.nextgear.services.IContractService;
import org.taljaard.nextgear.services.utils.ContractServiceUtilities;

@Service
public class ContractServiceImpl implements IContractService {

	@Autowired
	private ContractsMapper contractsMapper;

	@Override
	public void isDataValid(Contract contract) {

		List<String> validationMessages = ContractServiceUtilities.validate(contract);

		if (validationMessages.size() > 0) {
			throw new ContractDataInvalidException(Arrays.toString(validationMessages.toArray()));
		}
	}

	@Override
	public Contract createNew(Contract contract) {

		this.isDataValid(contract);
		contract.setType(ContractServiceUtilities.determineType(contract));
		contract.setStatus(ContractServiceUtilities.determineStatus(contract));

		if (StringUtils.equals(ContractStatus.Approved.toString(), contract.getStatus())) {
			Date creationDate = new Date(new DateTime().getMillis());
			contract.setActivitationDate(creationDate);
			contract.setCreateDate(creationDate);
			contract.setUpdateDate(creationDate);
		}
		
		return contract;
	}

	@Override
	public Contract save(Contract contract) {
		
		Contracts contracts = ContractServiceUtilities.convertToDAO(this.createNew(contract));
		
		try {
			contractsMapper.insert(contracts);
		}
		catch (PersistenceException ex) {
			throw new PersistanceSaveException("Exception saving new contract. Exception", ex);
		}
		
		return ContractServiceUtilities.convertToDTO(contracts);
	}

	@Override
	public List<Contract> findAll() {
		ContractsExample example = new ContractsExample();
		return findAllGivenCriteria(example);
	}

	@Override
	public List<Contract> findAllApproved() {
		ContractsExample example = new ContractsExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(ContractStatus.Approved.getStatusDisplayValue());

		return findAllGivenCriteria(example);
	}

	@Override
	public Contract findById(Integer id) {
		
		Contracts contractDAO = contractsMapper.selectByPrimaryKey(id);
		
		if (contractDAO == null) {
			throw new NoDataFoundException("Unable to retieve requested data");
		}
		
		return ContractServiceUtilities.convertToDTO(contractDAO);
	}
	
	@Override
	public List<Contract> findAllGivenCriteria(ContractsExample criteria) {

		List<Contracts> contracts = contractsMapper.selectByExample(criteria);
		
		if (CollectionUtils.isEmpty(contracts)) {
			throw new NoDataFoundException("Unable to retieve requested data");
		}
		return ContractServiceUtilities.convertToDTOList(contracts);
	}

	@Override
	public void deleteById(Integer id) {
		
		try {
			int rowsDeleted = contractsMapper.deleteByPrimaryKey(id);
			if (rowsDeleted != 1) {
				throw new PersistenceException("No data was deleted given id " + id);
			}
		}
		catch (PersistenceException ex) {
			throw new PersistanceDeleteException("Exception saving new contract. Exception", ex);
		}
	}

	@Override
	public Contract update(Contract contract) {
		this.isDataValid(contract);
		
		contract.setType(ContractServiceUtilities.determineType(contract));
		contract.setStatus(ContractServiceUtilities.determineStatus(contract));
		
		Date updateDate = new Date(new DateTime().getMillis());
		contract.setUpdateDate(updateDate);
		
		if (StringUtils.equals(ContractStatus.Denied.toString(), contract.getStatus())) {
			contract.setActivitationDate(null);
		}
		
		Contracts contracts = ContractServiceUtilities.convertToDAO(this.createNew(contract));
		try {
			contractsMapper.updateByPrimaryKey(contracts); 
		} 
		catch (PersistanceSaveException ex) {
			throw new PersistanceUpdateException(ex);
		}
		
		return ContractServiceUtilities.convertToDTO(contracts);
	}

}
