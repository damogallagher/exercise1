/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rest bean for the payment resource attributes
 * @author damien
 *
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class PaymentResourceAttributeVO {

	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("beneficiary_party")
	private AttributePartyVO beneficiaryParty;
	
	@JsonProperty("charges_information")
	private AttributeChargesInformationVO chargesInformation;
	
	@JsonProperty("currency")
	private String currency;
	
	@JsonProperty("debtor_party")
	private AttributePartyVO debtorParty;
	
	@JsonProperty("end_to_end_reference")
	private String endToEndReference;
	
	@JsonProperty("fx")
	private AttributeFxVO fx;
	
	@JsonProperty("numeric_reference")
	private Integer numericReference;	

	@JsonProperty("payment_id")
	private BigInteger paymentId;
	
	@JsonProperty("payment_purpose")
	private String paymentPurpose;
	
	@JsonProperty("payment_scheme")
	private String paymentScheme;
	
	@JsonProperty("payment_type")
	private String paymentType;
	
	@JsonProperty("processing_date")
	private Date processingDate;
	
	@JsonProperty("reference")
	private String reference;
	
	@JsonProperty("scheme_payment_sub_type")
	private String schemePaymentSubType;
	
	@JsonProperty("scheme_payment_type")
	private String schemePaymentType;
	
	@JsonProperty("sponsor_party")
	private AttributePartyVO sponsorParty;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AttributePartyVO getBeneficiaryParty() {
		return beneficiaryParty;
	}

	public void setBeneficiaryParty(AttributePartyVO beneficiaryParty) {
		this.beneficiaryParty = beneficiaryParty;
	}

	public AttributeChargesInformationVO getChargesInformation() {
		return chargesInformation;
	}

	public void setChargesInformation(AttributeChargesInformationVO chargesInformation) {
		this.chargesInformation = chargesInformation;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public AttributePartyVO getDebtorParty() {
		return debtorParty;
	}

	public void setDebtorParty(AttributePartyVO debtorParty) {
		this.debtorParty = debtorParty;
	}

	public String getEndToEndReference() {
		return endToEndReference;
	}

	public void setEndToEndReference(String endToEndReference) {
		this.endToEndReference = endToEndReference;
	}

	public AttributeFxVO getFx() {
		return fx;
	}

	public void setFx(AttributeFxVO fx) {
		this.fx = fx;
	}

	public Integer getNumericReference() {
		return numericReference;
	}

	public void setNumericReference(Integer numericReference) {
		this.numericReference = numericReference;
	}

	public BigInteger getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(BigInteger paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentPurpose() {
		return paymentPurpose;
	}

	public void setPaymentPurpose(String paymentPurpose) {
		this.paymentPurpose = paymentPurpose;
	}

	public String getPaymentScheme() {
		return paymentScheme;
	}

	public void setPaymentScheme(String paymentScheme) {
		this.paymentScheme = paymentScheme;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSchemePaymentSubType() {
		return schemePaymentSubType;
	}

	public void setSchemePaymentSubType(String schemePaymentSubType) {
		this.schemePaymentSubType = schemePaymentSubType;
	}

	public String getSchemePaymentType() {
		return schemePaymentType;
	}

	public void setSchemePaymentType(String schemePaymentType) {
		this.schemePaymentType = schemePaymentType;
	}

	public AttributePartyVO getSponsorParty() {
		return sponsorParty;
	}

	public void setSponsorParty(AttributePartyVO sponsorParty) {
		this.sponsorParty = sponsorParty;
	}

	@Override
	public String toString() {
		return "PaymentResourceAttributeVO [amount=" + amount + ", beneficiaryParty=" + beneficiaryParty
				+ ", chargesInformation=" + chargesInformation + ", currency=" + currency + ", debtorParty="
				+ debtorParty + ", endToEndReference=" + endToEndReference + ", fx=" + fx + ", numericReference="
				+ numericReference + ", paymentId=" + paymentId + ", paymentPurpose=" + paymentPurpose
				+ ", paymentScheme=" + paymentScheme + ", paymentType=" + paymentType + ", processingDate="
				+ processingDate + ", reference=" + reference + ", schemePaymentSubType=" + schemePaymentSubType
				+ ", schemePaymentType=" + schemePaymentType + ", sponsorParty=" + sponsorParty + "]";
	}

	
	
}
