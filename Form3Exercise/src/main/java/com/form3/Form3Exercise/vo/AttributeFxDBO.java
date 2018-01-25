/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.vo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DAO bean for the fx details 
 * @author damien
 *
 */
@Entity(name="T_ATTRIBUTE_FX")
public class AttributeFxDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
		
	@NotNull
	@Size(min=1, max = 50)
	@Column(name = "contract_reference", nullable = false, length = 50)
    private String contractReference;

	@NotNull
	@Column(name = "exchange_rate", nullable = false, length=40)
    private BigDecimal exchangeRate;
	
	@NotNull
	@Column(name = "original_amount", nullable = false, length=40)
    private BigDecimal originalAmount;
	
	@NotNull
	@Size(min=1, max = 10)
	@Column(name = "original_currency", nullable = false, length=10)
    private String originalCurrency;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContractReference() {
		return contractReference;
	}

	public void setContractReference(String contractReference) {
		this.contractReference = contractReference;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}

	public String getOriginalCurrency() {
		return originalCurrency;
	}

	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}

	@Override
	public String toString() {
		return "AttributeFxDBO [id=" + id + ", contractReference=" + contractReference + ", exchangeRate="
				+ exchangeRate + ", originalAmount=" + originalAmount + ", originalCurrency=" + originalCurrency + "]";
	}

	
}
