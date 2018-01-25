/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.service;

import java.util.List;

import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.vo.PaymentResourceDBO;

/**
 * Interface for the payment resource service
 * @author damien
 *
 */
public interface IPaymentResourceService {

	/**
	 * Method to seed the database from the rest endpoint (http://mockbin.org/bin/41ca3269-d8c4-4063-9fd5-f306814ff03f)
	 * TEMP Method - would never have this in a production environment
	 * @return
	 */
	Boolean seedDB();
	
	
	/**
	 * Method to fetch a payment resource by id
	 * @param id
	 * @return
	 */
	PaymentResourceVO fetchPaymentResource(String id);


	/**
	 * Method to create a payment resource
	 * @param argPaymentResourceVO
	 * @return
	 */
	PaymentResourceVO createPaymentResource(PaymentResourceVO argPaymentResourceVO);
	
	/**
	 * Method to create a payment resource
	 * @param argPaymentResourceDBO
	 * @return
	 */
	PaymentResourceVO createPaymentResource(PaymentResourceDBO argPaymentResourceDBO);

	/**
	 * Method to update a payment resource
	 * @param argPaymentResourceVO
	 * @return
	 */
	PaymentResourceVO updatePaymentResource(PaymentResourceVO argPaymentResourceVO);
	
	/**
	 * Method to delete a payment resource
	 * @param argId
	 * @return
	 */
	Boolean deletePaymentResource(String argId);
	
	/**
	 * Method to fetch all payment resources
	 * @return
	 */
	List<PaymentResourceVO> fetchAllPaymentResources();

	/**
	 * Method to fetch all payment resources with order
	 * @param order - order in asc or desc order
	 * @return
	 */
	List<PaymentResourceVO> fetchAllPaymentResources(String order);






	
}
