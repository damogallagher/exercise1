/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.vo;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DAO bean for the attribute charges information
 * @author damien
 *
 */
@Entity(name = "T_CHARGES_INFORMATION")
public class AttributeChargesInformationDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "bearer_code", nullable = false, length = 50)
	private String bearerCode;

	@NotNull
	@Column(name = "receiver_charges_amount", nullable = false, length = 40)
	private BigDecimal receiverChargesAmount;

	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "receiver_charges_currency", nullable = false, length = 10)
	private String receiverChargesCurrency;
	
	@OneToMany(mappedBy = "attributeChargesInfo", cascade = CascadeType.ALL)
	private Set<AttributeChargesDBO> attributeCharges;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBearerCode() {
		return bearerCode;
	}

	public void setBearerCode(String bearerCode) {
		this.bearerCode = bearerCode;
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

	public Set<AttributeChargesDBO> getAttributeCharges() {
		return attributeCharges;
	}

	public void setAttributeCharges(Set<AttributeChargesDBO> attributeCharges) {
		this.attributeCharges = attributeCharges;
	}

	@Override
	public String toString() {
		return "AttributeChargesInformationDBO [id=" + id + ", bearerCode=" + bearerCode + ", receiverChargesAmount="
				+ receiverChargesAmount + ", receiverChargesCurrency=" + receiverChargesCurrency + "]";
	}

}
