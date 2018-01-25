/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.form3.Form3Exercise.repository.PaymentResourceRepository;
import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.rest.vo.SeedResponseVO;
import com.form3.Form3Exercise.service.IPaymentResourceService;
import com.form3.Form3Exercise.utils.Constants;
import com.form3.Form3Exercise.utils.PaymentResourceUtils;
import com.form3.Form3Exercise.vo.PaymentResourceDBO;

/**
 * All service methods related to the payment resources
 * @author damien
 *
 */
@Service
public class PaymentResourceServiceImpl implements IPaymentResourceService {

	// private log variable
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentResourceServiceImpl.class);


	@Autowired
	private PaymentResourceRepository paymentResourceRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${payment.data.endpoint}")
	private String paymentDataEndpoint;
	
	/**
	 * Method to seed the database from the rest endpoint (http://mockbin.org/bin/41ca3269-d8c4-4063-9fd5-f306814ff03f)
	 * TEMP Method - would never have this in a production environment
	 * @return
	 */
	@Transactional
	public Boolean seedDB() {
		LOGGER.info("Entered seedDB - paymentDataEndpoint:{}", paymentDataEndpoint);
		Boolean seedDBResult = false;

		SeedResponseVO seedResponseVO = restTemplate.getForObject(paymentDataEndpoint, SeedResponseVO.class);
		if (seedResponseVO == null || CollectionUtils.isEmpty(seedResponseVO.getPaymentResourceVOList())) {
			LOGGER.error("Failed to get the payment resources for the paymentDataEndpoint:{}", paymentDataEndpoint);
			return seedDBResult;
		}
		List<PaymentResourceVO> paymentResourceVOList = seedResponseVO.getPaymentResourceVOList();
		
		for (PaymentResourceVO paymentResourceVO : paymentResourceVOList) {
			PaymentResourceVO tempPaymentResourceVO = createPaymentResource(paymentResourceVO);
			if (tempPaymentResourceVO == null) {
				LOGGER.error("Failed to create the paymentResource for the id:{}", paymentResourceVO.getId());
				return seedDBResult;
			} 
		}		
		
		seedDBResult = true;		
		LOGGER.info("Exiting seedDB - seedDBResult:{}", seedDBResult);
		return seedDBResult;
	}
	
	
	

	/**
	 * Method to fetch a payment resource by id
	 * @param id
	 * @return
	 */
	@Override
	public PaymentResourceVO fetchPaymentResource(String id) {
		LOGGER.debug("Entered fetchPaymentResource - id:{}", id);
		PaymentResourceVO paymentResourceVO = null;
		if (StringUtils.isEmpty(id)) {
			LOGGER.error("id passed in is null or empty");
			return paymentResourceVO;
		}
		
		PaymentResourceDBO paymentResourceDBO;
		try {
			paymentResourceDBO = paymentResourceRepository.findById(id);
		} catch (DataAccessException e) {
			LOGGER.error("An DataAccessException has occured finding the PaymentResource. Exception is:{}", e);
			paymentResourceDBO = null;
			return paymentResourceVO;
		}	
		
		paymentResourceVO = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourceDBO);
		
		LOGGER.debug("Exiting fetchPaymentResource");
		return paymentResourceVO;
	}

	/**
	 * Method to fetch all payment resources
	 * @return
	 */
	public List<PaymentResourceVO> fetchAllPaymentResources() {
		LOGGER.debug("Entered fetchAllPaymentResources");
		List<PaymentResourceVO> paymentResourcesList = null;
		List<PaymentResourceDBO> paymentResourcesDBOList;
		try {
			paymentResourcesDBOList = (List<PaymentResourceDBO>) paymentResourceRepository.findAll();
		} catch (DataAccessException e) {
			LOGGER.error("An DataAccessException has occured finding the PaymentResources. Exception is:{}", e);
			paymentResourcesDBOList = null;
		}	
		
		if (CollectionUtils.isEmpty(paymentResourcesDBOList)) {
			LOGGER.error("No paymentResources returned from the database");
			return paymentResourcesList;
		}
		
		paymentResourcesList = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourcesDBOList);		
		
		LOGGER.debug("Exiting fetchAllPaymentResources");
		return paymentResourcesList;
	}
	
	/**
	 * Method to fetch all payment resources with order
	 * @param order - order in asc or desc order
	 * @return
	 */
	public List<PaymentResourceVO> fetchAllPaymentResources(String order) {
		LOGGER.debug("Entered fetchAllPaymentResources - order:{}",  order);
		List<PaymentResourceVO> paymentResourcesList = null;
		if (StringUtils.isEmpty(order)) {
			LOGGER.info("order passed in is null or empty");
			return paymentResourcesList;
		}
		
		Direction direction = Direction.DESC;
		if (order.equalsIgnoreCase(Constants.ORDER_ASC)) {
			direction = Sort.Direction.ASC;
		}

		Order finalOrder = new Order(direction, Constants.ID);
		Sort pageable = new Sort(finalOrder);

		List<PaymentResourceDBO> paymentResourcesDBOList;
		try {
			paymentResourcesDBOList = (List<PaymentResourceDBO>) paymentResourceRepository.findAll(pageable);
		} catch (DataAccessException e) {
			LOGGER.error("An DataAccessException has occured finding the PaymentResources. Exception is:{}", e);
			paymentResourcesDBOList = null;
		}	
		
		if (CollectionUtils.isEmpty(paymentResourcesDBOList)) {
			LOGGER.error("No paymentResources returned from the database");
			return paymentResourcesList;
		}
		
		paymentResourcesList = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourcesDBOList);		
		
		LOGGER.debug("Exiting fetchAllPaymentResources");
		return paymentResourcesList;
	}
	
	/**
	 * Method to create a payment resource
	 * @param argPaymentResourceVO
	 * @return
	 */
	public PaymentResourceVO createPaymentResource(PaymentResourceVO argPaymentResourceVO) {
		LOGGER.debug("Entered createPaymentResource");
		PaymentResourceVO paymentResourceVO = null;
		if (argPaymentResourceVO == null) {
			LOGGER.error("argPaymentResourceVO passed in is null");
			return paymentResourceVO;
		}
		PaymentResourceDBO tempPaymentResourceDBO = PaymentResourceUtils.convertToPaymentResourceDBO(argPaymentResourceVO);	
		paymentResourceVO = createPaymentResource(tempPaymentResourceDBO);

		LOGGER.debug("Exiting createPaymentResource");
		return paymentResourceVO;
	}
	
	
	
	/**
	 * Method to create a payment resource
	 * @param argPaymentResourceDBO
	 * @return
	 */
	public PaymentResourceVO createPaymentResource(PaymentResourceDBO argPaymentResourceDBO) {
		LOGGER.debug("Entered createPaymentResource");
		PaymentResourceVO paymentResourceVO = null;
		if (argPaymentResourceDBO == null) {
			LOGGER.error("argPaymentResourceDBO passed in is null");
			return paymentResourceVO;
		}

		try {
			PaymentResourceDBO newPaymentResourceDBO = paymentResourceRepository.save(argPaymentResourceDBO);
			if (newPaymentResourceDBO != null) {
				paymentResourceVO = PaymentResourceUtils.convertToPaymentResourceVO(newPaymentResourceDBO);
			}
		} catch (DataAccessException e) {
			LOGGER.error("An DataAccessException has occured saving the PaymentResource. Exception is:{}", e);
			paymentResourceVO = null;
		}
		
		LOGGER.debug("Exiting createPaymentResource");
		return paymentResourceVO;
	}
	
	/**
	 * Method to update a payment resource
	 * @param argPaymentResourceVO
	 * @return
	 */
	public PaymentResourceVO updatePaymentResource(PaymentResourceVO argPaymentResourceVO) {
		LOGGER.debug("Entered updatePaymentResource");
		PaymentResourceVO paymentResourceVO = createPaymentResource(argPaymentResourceVO);

		LOGGER.debug("Exiting updatePaymentResource");
		return paymentResourceVO;
	}
	
	/**
	 * Method to delete a payment resource
	 * @param argId
	 * @return
	 */
	@Transactional
	public Boolean deletePaymentResource(String argId) {
		LOGGER.debug("Entered deletePaymentResource - argId:{}", argId);
		Boolean result = false;
		if (StringUtils.isEmpty(argId)) {
			LOGGER.error("argId passed in is null or empty");
			return result;
		}

		int rowsDeleted = 0;
		try {
			rowsDeleted = paymentResourceRepository.deleteById(argId);
		} catch (DataAccessException e) {
			LOGGER.error("An DataAccessException has occured deleting the PaymentResource. Exception is:{}", e);
		}
		
		if (rowsDeleted > Constants.ZERO) {
			result = true;
		}

		LOGGER.debug("Exiting deletePaymentResource - result:{}", result);
		return result;
	}
}
