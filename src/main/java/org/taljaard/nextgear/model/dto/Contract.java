package org.taljaard.nextgear.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties
public class Contract implements Serializable {

	private static final long serialVersionUID = 5929564962091758538L;

	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern = "YYYY-mm-dd hh:mm:ss")
	private Timestamp activitationDate;
	private BigDecimal amountRequested;

	private String businessNumber;
	private Integer id;
	private String name;

	@JsonProperty(access = Access.READ_ONLY)
	private String status;
	private String type;

	@JsonIgnore
	private Timestamp updateDate;
	@JsonIgnore
	private Timestamp createDate;

	public Contract() {
	}

	public Contract(String contractName, String businessNumber, BigDecimal amountRequested) {
		this.name = contractName;
		this.businessNumber = businessNumber;
		this.amountRequested = amountRequested;
	}

	public Timestamp getActivitationDate() {
		return activitationDate;
	}

	public void setActivitationDate(Timestamp activitationDate) {
		this.activitationDate = activitationDate;
	}

	public BigDecimal getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(BigDecimal amountRequested) {
		this.amountRequested = amountRequested;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
