package org.taljaard.nextgear.mybatis.dao;

import java.math.BigDecimal;
import java.sql.Date;

public class Contracts {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.id
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.amountRequested
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private BigDecimal amountRequested;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.businessNumber
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private String businessNumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.activitationDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private Date activitationDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.name
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.status
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private String status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.contractType
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private String type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.updateDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private Date updateDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column contracts.createDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	private Date createDate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.id
	 * @return  the value of contracts.id
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.id
	 * @param id  the value for contracts.id
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.amountRequested
	 * @return  the value of contracts.amountRequested
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public BigDecimal getAmountRequested() {
		return amountRequested;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.amountRequested
	 * @param amountRequested  the value for contracts.amountRequested
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setAmountRequested(BigDecimal amountRequested) {
		this.amountRequested = amountRequested;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.businessNumber
	 * @return  the value of contracts.businessNumber
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public String getBusinessNumber() {
		return businessNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.businessNumber
	 * @param businessNumber  the value for contracts.businessNumber
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber == null ? null : businessNumber.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.activitationDate
	 * @return  the value of contracts.activitationDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public Date getActivitationDate() {
		return activitationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.activitationDate
	 * @param activitationDate  the value for contracts.activitationDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setActivitationDate(Date activitationDate) {
		this.activitationDate = activitationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.name
	 * @return  the value of contracts.name
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.name
	 * @param name  the value for contracts.name
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.status
	 * @return  the value of contracts.status
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.status
	 * @param status  the value for contracts.status
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.contractType
	 * @return  the value of contracts.contractType
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.contractType
	 * @param type  the value for contracts.contractType
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.updateDate
	 * @return  the value of contracts.updateDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.updateDate
	 * @param updateDate  the value for contracts.updateDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column contracts.createDate
	 * @return  the value of contracts.createDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column contracts.createDate
	 * @param createDate  the value for contracts.createDate
	 * @mbg.generated  Sat Feb 03 23:02:26 EST 2018
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}