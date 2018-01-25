/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rest bean for the seed response from the endpoint provided by NearForm
 * @author damien
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedResponseVO {

	@JsonProperty("data")
	private List<PaymentResourceVO> paymentResourceVOList;

	public List<PaymentResourceVO> getPaymentResourceVOList() {
		return paymentResourceVOList;
	}

	public void setPaymentResourceVOList(List<PaymentResourceVO> paymentResourceVOList) {
		this.paymentResourceVOList = paymentResourceVOList;
	}

	@Override
	public String toString() {
		return "SeedResponseVO [paymentResourceVOList=" + paymentResourceVOList + "]";
	}


}
