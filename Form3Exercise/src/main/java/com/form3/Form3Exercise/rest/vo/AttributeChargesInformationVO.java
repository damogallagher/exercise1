/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest.vo;

/**
 * Rest bean for the attribute charges
 */
import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=false)
public class AttributeChargesInformationVO {

	@JsonProperty("bearer_code")
	private String bearerCode;
	
	@JsonProperty("sender_charges")
	private Set<AttributeChargesVO> attributeCharges;
	
	@JsonProperty("receiver_charges_amount")
	private BigDecimal receiverChargesAmount;
	
	@JsonProperty("receiver_charges_currency")
	private String receiverChargesCurrency;

	public String getBearerCode() {
		return bearerCode;
	}

	public void setBearerCode(String bearerCode) {
		this.bearerCode = bearerCode;
	}

	public Set<AttributeChargesVO> getAttributeCharges() {
		return attributeCharges;
	}

	public void setAttributeCharges(Set<AttributeChargesVO> attributeCharges) {
		this.attributeCharges = attributeCharges;
	}

	public BigDecimal getReceiverChargesAmount() {
		return receiverChargesAmount;
	}

	public void setReceiverChargesAmount(BigDecimal receiverChargesAmount) {
		this.receiverChargesAmount = receiverChargesAmount;
	}

	public String getReceiverChargesCurrency() {
		return receiverChargesCurrency;
	}

	public void setReceiverChargesCurrency(String receiverChargesCurrency) {
		this.receiverChargesCurrency = receiverChargesCurrency;
	}

	@Override
	public String toString() {
		return "AttributeChargesInformationVO [bearerCode=" + bearerCode + ", receiverChargesAmount="
				+ receiverChargesAmount + ", receiverChargesCurrency=" + receiverChargesCurrency + "]";
	}
	

	
}
