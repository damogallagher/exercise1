/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rest bean for the fx details
 * @author damien
 *
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class AttributeFxVO {

	@JsonProperty("contract_reference")
	private String contractReference;
	
	@JsonProperty("exchange_rate")
	private BigDecimal exchangeRate;
	
	@JsonProperty("original_amount")
	private BigDecimal originalAmount;
	
	@JsonProperty("original_currency")
	private String originalCurrency;

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
		return "AttributeFxVO [contractReference=" + contractReference + ", exchangeRate=" + exchangeRate
				+ ", originalAmount=" + originalAmount + ", originalCurrency=" + originalCurrency + "]";
	}
	
	
}
