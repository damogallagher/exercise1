/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.form3.Form3Exercise.rest.vo.AttributeChargesInformationVO;
import com.form3.Form3Exercise.rest.vo.AttributeChargesVO;
import com.form3.Form3Exercise.rest.vo.AttributeFxVO;
import com.form3.Form3Exercise.rest.vo.AttributePartyVO;
import com.form3.Form3Exercise.rest.vo.PaymentResourceAttributeVO;
import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.vo.AttributeChargesDBO;
import com.form3.Form3Exercise.vo.AttributeChargesInformationDBO;
import com.form3.Form3Exercise.vo.AttributeFxDBO;
import com.form3.Form3Exercise.vo.AttributePartyDBO;
import com.form3.Form3Exercise.vo.PaymentResourceDBO;

/**
 * Utilities for the payment resources
 * @author damien
 *
 */
public class PaymentResourceUtils {

	// private log variable
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentResourceUtils.class);

	/**
	 * Class constructor
	 */
	protected PaymentResourceUtils() {
		
	}
	
	/**
	 * Method to convert a PaymentResourceDBO to a PaymentResourceVO
	 * TODO: use a library like dozer to perform this conversion in the future
	 * @param paymentResourceDBO
	 * @return
	 */
	public static PaymentResourceVO convertToPaymentResourceVO(PaymentResourceDBO paymentResourceDBO) {
		LOGGER.debug("Entered convertToPaymentResourceVO");
		PaymentResourceVO paymentResourceVO = null;
		
		if (paymentResourceDBO == null) {
			LOGGER.error("paymentResourceDBO supplied is null");
			return paymentResourceVO;
		}
		paymentResourceVO = new PaymentResourceVO();
		
		paymentResourceVO.setId(paymentResourceDBO.getId());
		paymentResourceVO.setType(paymentResourceDBO.getType());
		paymentResourceVO.setVersion(paymentResourceDBO.getVersion());
		paymentResourceVO.setOrganisationId(paymentResourceDBO.getOrganisationId());
		
		PaymentResourceAttributeVO paymentResourceAttributeVO = new PaymentResourceAttributeVO();
		paymentResourceAttributeVO.setAmount(paymentResourceDBO.getAmount());
		paymentResourceAttributeVO.setCurrency(paymentResourceDBO.getCurrency());
		paymentResourceAttributeVO.setEndToEndReference(paymentResourceDBO.getEndToEndReference());
		paymentResourceAttributeVO.setNumericReference(paymentResourceDBO.getNumericReference());
		paymentResourceAttributeVO.setPaymentId(paymentResourceDBO.getPaymentId());
		paymentResourceAttributeVO.setPaymentPurpose(paymentResourceDBO.getPaymentPurpose());
		paymentResourceAttributeVO.setPaymentScheme(paymentResourceDBO.getPaymentScheme());			
		paymentResourceAttributeVO.setPaymentType(paymentResourceDBO.getPaymentType());
		paymentResourceAttributeVO.setProcessingDate(paymentResourceDBO.getProcessingDate());
		paymentResourceAttributeVO.setReference(paymentResourceDBO.getReference());
		paymentResourceAttributeVO.setSchemePaymentSubType(paymentResourceDBO.getSchemePaymentSubType());
		paymentResourceAttributeVO.setSchemePaymentType(paymentResourceDBO.getSchemePaymentType());
				
		AttributePartyDBO beneficiaryParty = paymentResourceDBO.getBeneficiaryParty();
		AttributePartyDBO debtorParty = paymentResourceDBO.getDebtorParty();
		AttributePartyDBO sponsorParty = paymentResourceDBO.getSponsorParty();
		
		paymentResourceAttributeVO.setBeneficiaryParty(getParty(beneficiaryParty));
		paymentResourceAttributeVO.setDebtorParty(getParty(debtorParty));
		paymentResourceAttributeVO.setSponsorParty(getParty(sponsorParty));
		
		AttributeFxDBO attributeFxDBO = paymentResourceDBO.getAttributeFx();
		AttributeFxVO attributeFxVO = new AttributeFxVO();
		attributeFxVO.setContractReference(attributeFxDBO.getContractReference());
		attributeFxVO.setExchangeRate(attributeFxDBO.getExchangeRate());
		attributeFxVO.setOriginalAmount(attributeFxDBO.getOriginalAmount());
		attributeFxVO.setOriginalCurrency(attributeFxDBO.getOriginalCurrency());
		paymentResourceAttributeVO.setFx(attributeFxVO);
		
		AttributeChargesInformationDBO attributeChargesInformationDBO = paymentResourceDBO.getChargesInformation();
		AttributeChargesInformationVO attributeChargesInfoVO = new AttributeChargesInformationVO();
		attributeChargesInfoVO.setBearerCode(attributeChargesInformationDBO.getBearerCode());
		attributeChargesInfoVO.setReceiverChargesAmount(attributeChargesInformationDBO.getReceiverChargesAmount());
		attributeChargesInfoVO.setReceiverChargesCurrency(attributeChargesInformationDBO.getReceiverChargesCurrency());
		
		Set<AttributeChargesVO> attributeChargesSet = new HashSet<>();
		for (AttributeChargesDBO attributeCharge : attributeChargesInformationDBO.getAttributeCharges()) {
			AttributeChargesVO attributeChargesVO = new AttributeChargesVO();
			attributeChargesVO.setAmount(attributeCharge.getAmount());
			attributeChargesVO.setCurrency(attributeCharge.getCurrency());
			attributeChargesSet.add(attributeChargesVO);
		}		

		attributeChargesInfoVO.setAttributeCharges(attributeChargesSet);
		paymentResourceAttributeVO.setChargesInformation(attributeChargesInfoVO);
		
		paymentResourceVO.setAttributes(paymentResourceAttributeVO);
		
		LOGGER.debug("Exiting convertToPaymentResourceVO");
		return paymentResourceVO;
	}
	
	/**
	 * Method to get the party for an attribute partyDBO
	 * @param attributePartyDBO
	 * @return
	 */
	private static AttributePartyVO getParty(AttributePartyDBO attributePartyDBO) {
		AttributePartyVO attributePartyVO = new AttributePartyVO();
		if (attributePartyDBO == null) {
			LOGGER.error("attributePartyDBO passed in is null");
			return attributePartyVO;
		}
		attributePartyVO.setAccountName(attributePartyDBO.getAccountName());
		attributePartyVO.setAccountNumber(attributePartyDBO.getAccountNumber());
		attributePartyVO.setAccountNumberCode(attributePartyDBO.getAccountNumberCode());
		attributePartyVO.setAccountType(attributePartyDBO.getAccountType());
		attributePartyVO.setAddress(attributePartyDBO.getAddress());
		attributePartyVO.setBankId(attributePartyDBO.getBankId());
		attributePartyVO.setBankIdCode(attributePartyDBO.getBankIdCode());
		attributePartyVO.setName(attributePartyDBO.getName());
		return attributePartyVO;
	}


	/**
	 * Method to convert a PaymentResourceVO to a PaymentResourceDBO
	 * TODO: use a library like dozer to perform this conversion in the future
	 * @param paymentResourceVO
	 * @return
	 */
	public static PaymentResourceDBO convertToPaymentResourceDBO(PaymentResourceVO paymentResourceVO) {
		LOGGER.debug("Entered convertToPaymentResourceDBO");
		PaymentResourceDBO paymentResourceDBO = null;
		
		if (paymentResourceVO == null) {
			LOGGER.error("paymentResourceVO supplied is null");
			return paymentResourceDBO;
		}
		paymentResourceDBO = new PaymentResourceDBO();
		
		paymentResourceDBO.setId(paymentResourceVO.getId());
		paymentResourceDBO.setType(paymentResourceVO.getType());
		paymentResourceDBO.setVersion(paymentResourceVO.getVersion());
		paymentResourceDBO.setOrganisationId(paymentResourceVO.getOrganisationId());
		
		PaymentResourceAttributeVO paymentResourceAttributeVO = paymentResourceVO.getAttributes();
		paymentResourceDBO.setAmount(paymentResourceAttributeVO.getAmount());
		paymentResourceDBO.setCurrency(paymentResourceAttributeVO.getCurrency());
		paymentResourceDBO.setEndToEndReference(paymentResourceAttributeVO.getEndToEndReference());
		paymentResourceDBO.setNumericReference(paymentResourceAttributeVO.getNumericReference());
		paymentResourceDBO.setPaymentId(paymentResourceAttributeVO.getPaymentId());
		paymentResourceDBO.setPaymentPurpose(paymentResourceAttributeVO.getPaymentPurpose());
		paymentResourceDBO.setPaymentScheme(paymentResourceAttributeVO.getPaymentScheme());			
		paymentResourceDBO.setPaymentType(paymentResourceAttributeVO.getPaymentType());
		paymentResourceDBO.setProcessingDate(paymentResourceAttributeVO.getProcessingDate());
		paymentResourceDBO.setReference(paymentResourceAttributeVO.getReference());
		paymentResourceDBO.setSchemePaymentSubType(paymentResourceAttributeVO.getSchemePaymentSubType());
		paymentResourceDBO.setSchemePaymentType(paymentResourceAttributeVO.getSchemePaymentType());
		
		AttributePartyVO beneficiaryParty = paymentResourceAttributeVO.getBeneficiaryParty();
		AttributePartyVO debtorParty = paymentResourceAttributeVO.getDebtorParty();
		AttributePartyVO sponsorParty = paymentResourceAttributeVO.getSponsorParty();
		paymentResourceDBO.setBeneficiaryParty(getParty(beneficiaryParty));
		paymentResourceDBO.setDebtorParty(getParty(debtorParty));
		paymentResourceDBO.setSponsorParty(getParty(sponsorParty));
	
		AttributeFxVO attributeFxVO = paymentResourceAttributeVO.getFx();
		AttributeFxDBO attributeFxDBO = new AttributeFxDBO();
		attributeFxDBO.setContractReference(attributeFxVO.getContractReference());
		attributeFxDBO.setExchangeRate(attributeFxVO.getExchangeRate());
		attributeFxDBO.setOriginalAmount(attributeFxVO.getOriginalAmount());
		attributeFxDBO.setOriginalCurrency(attributeFxVO.getOriginalCurrency());
		paymentResourceDBO.setAttributeFx(attributeFxDBO);
		
		AttributeChargesInformationVO attributeChargesInformationVO = paymentResourceAttributeVO.getChargesInformation();
		AttributeChargesInformationDBO attributeChargesInfoDBO = new AttributeChargesInformationDBO();
		attributeChargesInfoDBO.setBearerCode(attributeChargesInformationVO.getBearerCode());
		attributeChargesInfoDBO.setReceiverChargesAmount(attributeChargesInformationVO.getReceiverChargesAmount());
		attributeChargesInfoDBO.setReceiverChargesCurrency(attributeChargesInformationVO.getReceiverChargesCurrency());

		Set<AttributeChargesDBO> attributeChargesSet = new HashSet<>();
		for (AttributeChargesVO senderCharge : attributeChargesInformationVO.getAttributeCharges()) {
			AttributeChargesDBO attributeChargesDBO = new AttributeChargesDBO();
			attributeChargesDBO.setAmount(senderCharge.getAmount());
			attributeChargesDBO.setCurrency(senderCharge.getCurrency());
			attributeChargesDBO.setAttributeChargesInfo(attributeChargesInfoDBO);
			attributeChargesSet.add(attributeChargesDBO);
		}
		attributeChargesInfoDBO.setAttributeCharges(attributeChargesSet);

		paymentResourceDBO.setChargesInformation(attributeChargesInfoDBO);
		
		LOGGER.debug("Exiting convertToPaymentResourceDBO");
		return paymentResourceDBO;
	}

	/**
	 * Method to get the party for an attribute partyVO
	 * @param party
	 * @return
	 */
	private static AttributePartyDBO getParty(AttributePartyVO attributePartyVO) {
		AttributePartyDBO attributePartyDBO = new AttributePartyDBO();
		if (attributePartyVO == null) {
			LOGGER.error("attributePartyVO passed in is null");
			return attributePartyDBO;
		}
		
		attributePartyDBO.setAccountName(attributePartyVO.getAccountName());
		attributePartyDBO.setAccountNumber(attributePartyVO.getAccountNumber());
		attributePartyDBO.setAccountNumberCode(attributePartyVO.getAccountNumberCode());
		attributePartyDBO.setAccountType(attributePartyVO.getAccountType());
		attributePartyDBO.setAddress(attributePartyVO.getAddress());
		attributePartyDBO.setBankId(attributePartyVO.getBankId());
		attributePartyDBO.setBankIdCode(attributePartyVO.getBankIdCode());
		attributePartyDBO.setName(attributePartyVO.getName());
		return attributePartyDBO;
	}

	/**
	 * Method to convert a list of paymentResourceDBO's to PaymentResourceVO's
	 * @param paymentResourcesDBOList
	 * @return
	 */
	public static List<PaymentResourceVO> convertToPaymentResourceVO(List<PaymentResourceDBO> paymentResourcesDBOList) {
		LOGGER.debug("Entered convertToPaymentResourceVO");
		List<PaymentResourceVO> paymentResourceVOList = null;
		if (CollectionUtils.isEmpty(paymentResourcesDBOList)) {
			LOGGER.error("paymentResourcesDBOList passed in is null or empty");
			return paymentResourceVOList;
		}
		paymentResourceVOList = new LinkedList<>();
		
		for (PaymentResourceDBO paymentResourceDBO : paymentResourcesDBOList) {
			PaymentResourceVO paymentResourceVO = convertToPaymentResourceVO(paymentResourceDBO);
			paymentResourceVOList.add(paymentResourceVO);
		}
		
		LOGGER.debug("Exiting convertToPaymentResourceVO");
		return paymentResourceVOList;
	}
	
	/**
	 * Method to convert a list of paymentResourceVO's to PaymentResourceDBO's
	 * @param paymentResourcesDBOList
	 * @return
	 */
	public static List<PaymentResourceDBO> convertToPaymentResourceDBO(List<PaymentResourceVO> paymentResourcesVOList) {
		LOGGER.debug("Entered convertToPaymentResourceDBO");
		List<PaymentResourceDBO> paymentResourceDBOList = null;
		if (CollectionUtils.isEmpty(paymentResourcesVOList)) {
			LOGGER.error("paymentResourcesVOList passed in is null or empty");
			return paymentResourceDBOList;
		}
		paymentResourceDBOList = new LinkedList<>();
		
		for (PaymentResourceVO paymentResourceVO : paymentResourcesVOList) {
			PaymentResourceDBO paymentResourceDBO = convertToPaymentResourceDBO(paymentResourceVO);
			paymentResourceDBOList.add(paymentResourceDBO);
		}
		
		LOGGER.debug("Exiting convertToPaymentResourceDBO");
		return paymentResourceDBOList;
	}
}
