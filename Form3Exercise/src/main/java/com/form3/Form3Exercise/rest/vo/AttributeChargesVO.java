/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rest bean for all charges details
 * @author damien
 *
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class AttributeChargesVO {

	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("currency")
	private String currency;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "AttributeChargesVO [amount=" + amount + ", currency=" + currency + "]";
	}	
}
