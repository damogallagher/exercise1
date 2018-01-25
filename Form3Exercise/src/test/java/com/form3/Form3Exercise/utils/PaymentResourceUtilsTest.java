/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.service.impl.BaseTestUtils;
import com.form3.Form3Exercise.vo.PaymentResourceDBO;

@RunWith(EasyMockRunner.class)
public class PaymentResourceUtilsTest extends BaseTestUtils{

	@TestSubject
	PaymentResourceUtils paymentResourceUtils = new PaymentResourceUtils();
	
	@Test
	public void testConstructor() {
		paymentResourceUtils = new PaymentResourceUtils();
		assertNotNull(paymentResourceUtils);
	}
	
	@Test
	public void testConvertToPaymentResourceVO_PaymentResourceDBOIsNull() {
		PaymentResourceDBO paymentResourceDBO = null;
		PaymentResourceVO paymentResourceVO = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourceDBO);
		assertNull(paymentResourceVO);
	}
	
	@Test
	public void testConvertToPaymentResourceVO_BeneficiaryPartyIsNull() {
		PaymentResourceDBO paymentResourceDBO = getDummyPaymentResourceDBO();
		paymentResourceDBO.setBeneficiaryParty(null);
		PaymentResourceVO paymentResourceVO = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourceDBO);
		assertNotNull(paymentResourceVO);
	}
	
	
	@Test
	public void testConvertToPaymentResourceVO_Success() {
		PaymentResourceDBO paymentResourceDBO = getDummyPaymentResourceDBO();
		PaymentResourceVO paymentResourceVO = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourceDBO);
		assertNotNull(paymentResourceVO);
	}
	
	@Test
	public void testConvertToPaymentResourceDBO_PaymentResourceVOIsNull() {
		PaymentResourceVO paymentResourceVO = null;
		PaymentResourceDBO paymentResourceDBO = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourceVO);
		assertNull(paymentResourceDBO);
	}
	
	@Test
	public void testConvertToPaymentResourceDBO_BenefiricaryPartyIsNull() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		paymentResourceVO.getAttributes().setBeneficiaryParty(null);
		PaymentResourceDBO paymentResourceDBO = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourceVO);
		assertNotNull(paymentResourceDBO);
	}
	@Test
	public void testConvertToPaymentResourceDBO_Success() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		PaymentResourceDBO paymentResourceDBO = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourceVO);
		assertNotNull(paymentResourceDBO);
	}
	@Test
	public void testConvertToPaymentResourceVO_List_EmptyList() {
		List<PaymentResourceDBO> paymentResourcesDBOList = new LinkedList<>();

		List<PaymentResourceVO> paymentResourceVOList = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourcesDBOList);
		assertNull(paymentResourceVOList);
	}
	@Test
	public void testConvertToPaymentResourceVO_List_NullList() {
		List<PaymentResourceDBO> paymentResourcesDBOList = null;

		List<PaymentResourceVO> paymentResourceVOList = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourcesDBOList);
		assertNull(paymentResourceVOList);
	}
	@Test
	public void testConvertToPaymentResourceVO_List_Success1Item() {
		PaymentResourceDBO paymentResourceDBO = getDummyPaymentResourceDBO();
		List<PaymentResourceDBO> paymentResourcesDBOList = new LinkedList<>();
		paymentResourcesDBOList.add(paymentResourceDBO);
		
		List<PaymentResourceVO> paymentResourceVOList = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourcesDBOList);
		assertNotNull(paymentResourceVOList);
		assertTrue(paymentResourceVOList.size() == 1);		
	}
	@Test
	public void testConvertToPaymentResourceVO_List_Success2Items() {
		PaymentResourceDBO paymentResourceDBO = getDummyPaymentResourceDBO();
		List<PaymentResourceDBO> paymentResourcesDBOList = new LinkedList<>();
		paymentResourcesDBOList.add(paymentResourceDBO);
		paymentResourcesDBOList.add(paymentResourceDBO);
		
		List<PaymentResourceVO> paymentResourceVOList = PaymentResourceUtils.convertToPaymentResourceVO(paymentResourcesDBOList);
		assertNotNull(paymentResourceVOList);
		assertTrue(paymentResourceVOList.size() == 2);		
	}
	@Test
	public void testConvertToPaymentResourceDBO_List_EmptyList() {
		List<PaymentResourceVO> paymentResourcesVOList = new LinkedList<>();

		List<PaymentResourceDBO> paymentResourceDBOList = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourcesVOList);
		assertNull(paymentResourceDBOList);
	}
	@Test
	public void testConvertToPaymentResourceDBO_List_NullList() {
		List<PaymentResourceVO> paymentResourcesVOList = null;

		List<PaymentResourceDBO> paymentResourceDBOList = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourcesVOList);
		assertNull(paymentResourceDBOList);
	}
	@Test
	public void testConvertToPaymentResourceDBO_List_Success1Item() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		List<PaymentResourceVO> paymentResourcesVOList = new LinkedList<>();
		paymentResourcesVOList.add(paymentResourceVO);

		List<PaymentResourceDBO> paymentResourceDBList = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourcesVOList);
		assertNotNull(paymentResourceDBList);
		assertTrue(paymentResourceDBList.size() == 1);
	}
	@Test
	public void testConvertToPaymentResourceDBO_List_Success2Items() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		List<PaymentResourceVO> paymentResourcesVOList = new LinkedList<>();
		paymentResourcesVOList.add(paymentResourceVO);
		paymentResourcesVOList.add(paymentResourceVO);
		
		List<PaymentResourceDBO> paymentResourceDBList = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourcesVOList);
		assertNotNull(paymentResourceDBList);
		assertTrue(paymentResourceDBList.size() == 2);		
	}
}
