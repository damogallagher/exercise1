/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

/**
 * DAO bean for the payment resource details 
 * @author damien
 *
 */
@Entity(name="T_PAYMENT_RESOURCE")
public class PaymentResourceDBO {

	//Will override the id from the seed method - in a production environment - we would seed data using scripts
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2" )
	@Column(name="id")
	private String id;
	
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "type", nullable = false, length = 40)
    private String type;
	
	@NotNull
	@Column(name = "version", nullable = false, length = 11)
    private Integer version;
	
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "organisation_id", nullable = false, length = 40)
    private String organisationId;

	@NotNull
	@Column(name = "amount", nullable = false)
    private BigDecimal amount;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "beneficiary_party_id", nullable = false)
	private AttributePartyDBO beneficiaryParty;
		
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "charges_info_id", nullable = false)
	private AttributeChargesInformationDBO chargesInformation;
	
	
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "currency", nullable = false, length = 10)
    private String currency;	
	
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "debtor_party_id", nullable = false)
	private AttributePartyDBO debtorParty;	
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "end_to_end_reference", nullable = false, length = 250)
    private String endToEndReference;	
	
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "fx_id", nullable = false)
	private AttributeFxDBO attributeFx;	
	
	@NotNull
	@Column(name = "numeric_reference", nullable = false, length = 11)
    private Integer numericReference;
	
	@NotNull
	@Column(name = "payment_id", nullable = false, length = 40)
    private BigInteger paymentId;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "payment_purpose", nullable = false, length = 250)
    private String paymentPurpose;		
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "payment_scheme", nullable = false, length = 50)
    private String paymentScheme;	
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "payment_type", nullable = false, length = 50)
    private String paymentType;	
	
	@NotNull
	@Column(name = "processing_date", nullable = false, length = 50)
    private Date processingDate;	
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "reference", nullable = false, length = 250)
    private String reference;	
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "scheme_payment_sub_type", nullable = false, length = 100)
    private String schemePaymentSubType;	
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "scheme_payment_type", nullable = false, length = 100)
    private String schemePaymentType;

	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "sponsor_party_id", nullable = false)
	private AttributePartyDBO sponsorParty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AttributePartyDBO getBeneficiaryParty() {
		return beneficiaryParty;
	}

	public void setBeneficiaryParty(AttributePartyDBO beneficiaryParty) {
		this.beneficiaryParty = beneficiaryParty;
	}

	public AttributeChargesInformationDBO getChargesInformation() {
		return chargesInformation;
	}

	public void setChargesInformation(AttributeChargesInformationDBO chargesInformation) {
		this.chargesInformation = chargesInformation;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public AttributePartyDBO getDebtorParty() {
		return debtorParty;
	}

	public void setDebtorParty(AttributePartyDBO debtorParty) {
		this.debtorParty = debtorParty;
	}

	public String getEndToEndReference() {
		return endToEndReference;
	}

	public void setEndToEndReference(String endToEndReference) {
		this.endToEndReference = endToEndReference;
	}

	public AttributeFxDBO getAttributeFx() {
		return attributeFx;
	}

	public void setAttributeFx(AttributeFxDBO attributeFx) {
		this.attributeFx = attributeFx;
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

	public AttributePartyDBO getSponsorParty() {
		return sponsorParty;
	}

	public void setSponsorParty(AttributePartyDBO sponsorParty) {
		this.sponsorParty = sponsorParty;
	}

	@Override
	public String toString() {
		return "PaymentResourceDBO [id=" + id + ", type=" + type + ", version=" + version + ", organisationId="
				+ organisationId + ", amount=" + amount + ", beneficiaryParty=" + beneficiaryParty + ", chargesInformation="
				+ chargesInformation + ", currency=" + currency + ", debtorParty=" + debtorParty + ", endToEndReference="
				+ endToEndReference + ", attributeFx=" + attributeFx + ", numericReference=" + numericReference
				+ ", paymentId=" + paymentId + ", paymentPurpose=" + paymentPurpose + ", paymentScheme=" + paymentScheme
				+ ", paymentType=" + paymentType + ", processingDate=" + processingDate + ", reference=" + reference
				+ ", schemePaymentSubType=" + schemePaymentSubType + ", schemePaymentType=" + schemePaymentType
				+ ", sponsorParty=" + sponsorParty + "]";
	}	
}
