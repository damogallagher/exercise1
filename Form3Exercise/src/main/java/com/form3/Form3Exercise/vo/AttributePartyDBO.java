/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DAO bean for the attribute party details 
 * @author damien
 *
 */
@Entity(name="T_PARTY")
public class AttributePartyDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
		
	@Size(max = 100)
	@Column(name = "account_name", nullable = true, length = 100)
    private String accountName;

	@Size(max = 40)
	@Column(name = "account_number", nullable = true, length=40)
    private String accountNumber;
	
	@Size(max = 40)
	@Column(name = "account_number_code", nullable = true, length=40)
    private String accountNumberCode;
	
	@Column(name = "account_type", nullable = true)
    private Integer accountType;
	
	@Size(max = 250)
	@Column(name = "address", nullable = true, length = 250)
    private String address;
	
	@NotNull
	@Column(name = "bank_id", nullable = false)
    private Integer bankId;
	
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "bank_id_code", nullable = false, length = 40)
    private String bankIdCode;
	
	
	@Size(min = 1, max = 250)
	@Column(name = "name", nullable = false, length = 250)
    private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
		return "AttributePartyDBO [id=" + id + ", accountName=" + accountName + ", accountNumber=" + accountNumber
				+ ", accountNumberCode=" + accountNumberCode + ", accountType=" + accountType + ", address=" + address
				+ ", bankId=" + bankId + ", bankIdCode=" + bankIdCode + ", name=" + name + "]";
	}

}
