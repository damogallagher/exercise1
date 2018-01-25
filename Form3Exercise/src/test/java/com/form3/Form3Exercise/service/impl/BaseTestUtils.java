/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;

import com.form3.Form3Exercise.rest.vo.AttributeChargesInformationVO;
import com.form3.Form3Exercise.rest.vo.AttributeChargesVO;
import com.form3.Form3Exercise.rest.vo.AttributeFxVO;
import com.form3.Form3Exercise.rest.vo.AttributePartyVO;
import com.form3.Form3Exercise.rest.vo.PaymentResourceAttributeVO;
import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.utils.PaymentResourceUtils;
import com.form3.Form3Exercise.vo.PaymentResourceDBO;

@Ignore
public class BaseTestUtils {

	/**
	 * Method to get a dummy payment resource vo
	 */
	protected PaymentResourceVO getDummyPaymentResourceVO() {
		PaymentResourceVO paymentResourceVO = new PaymentResourceVO();
		paymentResourceVO.setId("1");
		paymentResourceVO.setOrganisationId("2");
		paymentResourceVO.setType("type");
		paymentResourceVO.setVersion(0);
		
		paymentResourceVO.setAttributes(getDummyPaymentResourceAttributeVO());
		return paymentResourceVO;
	}

	/**
	 * Method to get a dummy payment resource dbo
	 */
	protected PaymentResourceDBO getDummyPaymentResourceDBO() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		PaymentResourceDBO paymentResourceDBO = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourceVO);

		return paymentResourceDBO;
	}
	
	
	/**
	 * Method to get a dummy PaymentResourceAttributeVO
	 * @return
	 */
	private PaymentResourceAttributeVO getDummyPaymentResourceAttributeVO() {
		PaymentResourceAttributeVO paymentResourceAttributeVO = new PaymentResourceAttributeVO();
		paymentResourceAttributeVO.setAmount(new BigDecimal("1.0"));
		paymentResourceAttributeVO.setBeneficiaryParty(getDummyParty());
		paymentResourceAttributeVO.setChargesInformation(getDummyChargesInformation());
		paymentResourceAttributeVO.setCurrency("EUR");
		paymentResourceAttributeVO.setDebtorParty(getDummyParty());
		paymentResourceAttributeVO.setEndToEndReference("reference");
		paymentResourceAttributeVO.setFx(getDummyFX());
		paymentResourceAttributeVO.setNumericReference(1);
		paymentResourceAttributeVO.setPaymentId(new BigInteger("2"));
		paymentResourceAttributeVO.setPaymentPurpose("purpose");
		paymentResourceAttributeVO.setPaymentScheme("scheme");
		paymentResourceAttributeVO.setPaymentType("cash");
		paymentResourceAttributeVO.setProcessingDate(new Date());
		paymentResourceAttributeVO.setReference("reference");
		paymentResourceAttributeVO.setSchemePaymentSubType("schemePaymentSubType");
		paymentResourceAttributeVO.setSchemePaymentType("schemePaymentType");
		paymentResourceAttributeVO.setSponsorParty(getDummyParty());
		
		return paymentResourceAttributeVO;
	}

	/**
	 * Method to get dummy FX
	 * @return
	 */
	private AttributeFxVO getDummyFX() {
		AttributeFxVO attributeFxVO = new AttributeFxVO();
		attributeFxVO.setContractReference("reference");
		attributeFxVO.setExchangeRate(new BigDecimal("1.2"));
		attributeFxVO.setOriginalAmount(new BigDecimal("33.33"));
		attributeFxVO.setOriginalCurrency("USD");
		return attributeFxVO;
	}

	/**
	 * Method to get dummy AttributeChargesInformationVO
	 * @return
	 */
	private AttributeChargesInformationVO getDummyChargesInformation() {
		AttributeChargesInformationVO attributeChargesInformationVO = new AttributeChargesInformationVO();
		Set<AttributeChargesVO> attributeCharges = new HashSet<>();
		attributeCharges.add(getDummyAttributeCharges());
		attributeCharges.add(getDummyAttributeCharges());		
		attributeChargesInformationVO.setAttributeCharges(attributeCharges);
		attributeChargesInformationVO.setBearerCode("bearer");
		attributeChargesInformationVO.setReceiverChargesAmount(new BigDecimal("22.55"));
		attributeChargesInformationVO.setReceiverChargesCurrency("EUR");
		return attributeChargesInformationVO;
	}

	/**
	 * Method to get dummy AttributeChargesVO
	 * @return
	 */
	private AttributeChargesVO getDummyAttributeCharges() {
		AttributeChargesVO attributeChargesVO = new AttributeChargesVO();
		attributeChargesVO.setAmount(new BigDecimal("1.3"));
		attributeChargesVO.setCurrency("EUR");
		return attributeChargesVO;
	}

	/**
	 * Method to get a dummy party
	 * @return
	 */
	private AttributePartyVO getDummyParty() {
		AttributePartyVO attributePartyVO = new AttributePartyVO();
		attributePartyVO.setAccountName("name");
		attributePartyVO.setAccountNumber("123");
		attributePartyVO.setAccountNumberCode("code");
		attributePartyVO.setAccountType(4);
		attributePartyVO.setAddress("address");
		attributePartyVO.setBankId(1);
		attributePartyVO.setBankIdCode("aib");
		attributePartyVO.setName("name");
		return attributePartyVO;
	}
}
