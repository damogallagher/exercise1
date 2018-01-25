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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DAO bean for the attribute charges
 * @author damien
 *
 */
@Entity(name = "T_CHARGES")
public class AttributeChargesDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "amount", nullable = false, length = 40)
	private BigDecimal amount;

	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "currency", nullable = false, length = 10)
	private String currency;

	@ManyToOne
    @JoinColumn(name = "charges_info_id")
	private AttributeChargesInformationDBO attributeChargesInfo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public AttributeChargesInformationDBO getAttributeChargesInfo() {
		return attributeChargesInfo;
	}

	public void setAttributeChargesInfo(AttributeChargesInformationDBO attributeChargesInfo) {
		this.attributeChargesInfo = attributeChargesInfo;
	}

	@Override
	public String toString() {
		return "AttributeChargesDBO [id=" + id + ", amount=" + amount + ", currency=" + currency + "]";
	}
}
