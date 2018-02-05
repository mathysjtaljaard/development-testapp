package org.taljaard.nextgear.tests.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.taljaard.nextgear.NextgearCapitolTestApplication;
import org.taljaard.nextgear.enums.ContractStatus;
import org.taljaard.nextgear.enums.ContractType;
import org.taljaard.nextgear.model.dto.Contract;
import org.taljaard.nextgear.mybatis.dao.Contracts;
import org.taljaard.nextgear.mybatis.mapper.ContractsMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { NextgearCapitolTestApplication.class })
@TestPropertySource(locations = "classpath:application-local-testing.properties")
public abstract class ITBase {

	@Autowired
	private ContractsMapper contractMapper;
	
	protected Gson gsonConverter;

	public ITBase() {
		gsonConverter = new GsonBuilder().setDateFormat("YYYY-mm-dd hh:mm:ss").create();
	}
	
	protected Contracts createNewContractDOA() {
		Contracts contract = new Contracts();
		Timestamp insertDate = new Timestamp(new DateTime().getMillis());
		contract.setAmountRequested(BigDecimal.valueOf(6000));
		contract.setActivitationDate(insertDate);
		contract.setCreateDate(insertDate);
		contract.setUpdateDate(insertDate);
		contract.setBusinessNumber("c-123aaa");
		contract.setName("integration-contract-123aaa");
		contract.setStatus(ContractStatus.Approved.toString());
		contract.setType(ContractType.Express.toString());

		return contract;
	}

	protected Contract createNewExpressContractDTO() {
		Contract contract = new Contract();
		contract.setAmountRequested(BigDecimal.valueOf(6000));
		contract.setBusinessNumber("c-123aaa");
		contract.setName("integration-contract-123aaa");

		return contract;
	}
	
	protected Contract createNewSalesContractDTO() {
		Contract contract = new Contract();
		contract.setAmountRequested(BigDecimal.valueOf(60000));
		contract.setBusinessNumber("c-123aaa");
		contract.setName("integration-contract-123aaa");

		return contract;
	}
	
	protected void insertContract(Contracts contract) {
		contractMapper.insert(contract);
	}

	protected void deleteContract(Integer id) {
		contractMapper.deleteByPrimaryKey(id);
	}
}
