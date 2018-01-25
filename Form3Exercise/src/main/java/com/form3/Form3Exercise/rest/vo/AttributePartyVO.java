/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rest bean for the party details
 * @author damien
 *
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class AttributePartyVO {

	@JsonProperty("account_name")
	private String accountName;
	
	@JsonProperty("account_number")
	private String accountNumber;
	
	@JsonProperty("account_number_code")
	private String accountNumberCode;
	
	@JsonProperty("account_type")
	private Integer accountType;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("bank_id")
	private Integer bankId;
	
	@JsonProperty("bank_id_code")
	private String bankIdCode;
	
	@JsonProperty("name")
	private String name;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumberCode() {
		return accountNumberCode;
	}

	public void setAccountNumberCode(String accountNumberCode) {
		this.accountNumberCode = accountNumberCode;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankIdCode() {
		return bankIdCode;
	}

	public void setBankIdCode(String bankIdCode) {
		this.bankIdCode = bankIdCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AttributePartyVO [accountName=" + accountName + ", accountNumber=" + accountNumber
				+ ", accountNumberCode=" + accountNumberCode + ", accountType=" + accountType + ", address=" + address
				+ ", bankId=" + bankId + ", bankIdCode=" + bankIdCode + ", name=" + name + "]";
	}
	
	
}
