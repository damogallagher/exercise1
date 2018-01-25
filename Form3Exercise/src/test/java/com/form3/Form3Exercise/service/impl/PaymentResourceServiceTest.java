/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import com.form3.Form3Exercise.repository.PaymentResourceRepository;
import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.rest.vo.SeedResponseVO;
import com.form3.Form3Exercise.utils.Constants;
import com.form3.Form3Exercise.utils.PaymentResourceUtils;
import com.form3.Form3Exercise.vo.PaymentResourceDBO;

@RunWith(EasyMockRunner.class)
public class PaymentResourceServiceTest extends BaseTestUtils{

	@TestSubject
	PaymentResourceServiceImpl paymentResourceService = new PaymentResourceServiceImpl();
	@Mock
	PaymentResourceRepository mockPaymentResourceRepository;
	@Mock
	RestTemplate mockRestTemplate;
	@Mock
	DataAccessException mockDataAccessException;
	
	@Test
	public void testSeedDB_ResponseIsNull() {
		SeedResponseVO seedResponseVO = null;
		EasyMock.expect(mockRestTemplate.getForObject(EasyMock.anyString(), EasyMock.anyObject())).andReturn(seedResponseVO);
		EasyMock.replay(mockRestTemplate);
		
		boolean result = paymentResourceService.seedDB();
		assertFalse(result);
		EasyMock.verify(mockRestTemplate);
	}
	
	@Test
	public void testSeedDB_PaymentResourceListIsNull() {
		List<PaymentResourceVO> paymentResourceVOList = null;
		SeedResponseVO seedResponseVO = new SeedResponseVO();		
		seedResponseVO.setPaymentResourceVOList(paymentResourceVOList);
		
		EasyMock.expect(mockRestTemplate.getForObject(EasyMock.anyString(), EasyMock.anyObject())).andReturn(seedResponseVO);
		EasyMock.replay(mockRestTemplate);
		
		boolean result = paymentResourceService.seedDB();
		assertFalse(result);
		EasyMock.verify(mockRestTemplate);
	}
	@Test
	public void testSeedDB_PaymentResourceListIsEmpty() {
		List<PaymentResourceVO> paymentResourceVOList = new LinkedList<>();
		SeedResponseVO seedResponseVO = new SeedResponseVO();		
		seedResponseVO.setPaymentResourceVOList(paymentResourceVOList);
		
		EasyMock.expect(mockRestTemplate.getForObject(EasyMock.anyString(), EasyMock.anyObject())).andReturn(seedResponseVO);
		EasyMock.replay(mockRestTemplate);
		
		boolean result = paymentResourceService.seedDB();
		assertFalse(result);
		EasyMock.verify(mockRestTemplate);
	}
	
	@Test
	public void testSeedDB_FailedToSavePaymentResource() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		List<PaymentResourceVO> paymentResourceVOList = new LinkedList<>();
		paymentResourceVOList.add(paymentResourceVO);
		SeedResponseVO seedResponseVO = new SeedResponseVO();		
		seedResponseVO.setPaymentResourceVOList(paymentResourceVOList);
		
		EasyMock.expect(mockRestTemplate.getForObject(EasyMock.anyString(), EasyMock.anyObject())).andReturn(seedResponseVO);
		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);
		EasyMock.replay(mockRestTemplate);
		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);
		
		boolean result = paymentResourceService.seedDB();
		assertFalse(result);
		
		EasyMock.verify(mockRestTemplate);
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);
	}
	
	@Test
	public void testSeedDB_Success() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		PaymentResourceDBO paymentResourceDBO = PaymentResourceUtils.convertToPaymentResourceDBO(paymentResourceVO);
		
		List<PaymentResourceVO> paymentResourceVOList = new LinkedList<>();
		paymentResourceVOList.add(paymentResourceVO);
		SeedResponseVO seedResponseVO = new SeedResponseVO();		
		seedResponseVO.setPaymentResourceVOList(paymentResourceVOList);
		
		EasyMock.expect(mockRestTemplate.getForObject(EasyMock.anyString(), EasyMock.anyObject())).andReturn(seedResponseVO);
		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andReturn(paymentResourceDBO);
		EasyMock.replay(mockRestTemplate);
		EasyMock.replay(mockPaymentResourceRepository);
		
		boolean result = paymentResourceService.seedDB();
		assertTrue(result);
		
		EasyMock.verify(mockRestTemplate);
		EasyMock.verify(mockPaymentResourceRepository);
	}
	
	@Test
	public void testFetchPaymentResource_IdIsEmpty() {
		String id = "";
		PaymentResourceVO paymentResourceVO = paymentResourceService.fetchPaymentResource(id);
		assertNull(paymentResourceVO);
	}
	
	@Test
	public void testFetchPaymentResource_IdIsNull() {
		String id = null;
		PaymentResourceVO paymentResourceVO = paymentResourceService.fetchPaymentResource(id);
		assertNull(paymentResourceVO);
	}
	
	@Test
	public void testFetchPaymentResource_ExceptionFindingPaymentResource() {
		String id = "2";
		
		EasyMock.expect(mockPaymentResourceRepository.findById(EasyMock.anyString())).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);
		
		PaymentResourceVO paymentResourceVO = paymentResourceService.fetchPaymentResource(id);
		assertNull(paymentResourceVO);
		
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);
	}
	
	@Test
	public void testFetchPaymentResource_Success() {
		String id = "2";
		
		EasyMock.expect(mockPaymentResourceRepository.findById(EasyMock.anyString())).andReturn(getDummyPaymentResourceDBO());

		EasyMock.replay(mockPaymentResourceRepository);

		PaymentResourceVO paymentResourceVO = paymentResourceService.fetchPaymentResource(id);
		assertNotNull(paymentResourceVO);
		
		EasyMock.verify(mockPaymentResourceRepository);
	}
	
	@Test
	public void testFetchAllPaymentResources_ExceptionFindingResources() {

		EasyMock.expect(mockPaymentResourceRepository.findAll()).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources();
		assertNull(paymentResourceVOList);
		
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);
	}
	
	@Test
	public void testFetchAllPaymentResources_NullResourcesReturned() {
		List<PaymentResourceDBO> paymentResourceList = null;

		EasyMock.expect(mockPaymentResourceRepository.findAll()).andReturn(paymentResourceList);

		EasyMock.replay(mockPaymentResourceRepository);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources();
		assertNull(paymentResourceVOList);
		
		EasyMock.verify(mockPaymentResourceRepository);
	}
	@Test
	public void testFetchAllPaymentResources_0ResourcesReturned() {
		List<PaymentResourceDBO> paymentResourceList = new LinkedList<>();

		EasyMock.expect(mockPaymentResourceRepository.findAll()).andReturn(paymentResourceList);

		EasyMock.replay(mockPaymentResourceRepository);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources();
		assertNull(paymentResourceVOList);

		EasyMock.verify(mockPaymentResourceRepository);
	}
	
	@Test
	public void testFetchAllPaymentResources_Success() {
		List<PaymentResourceDBO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(getDummyPaymentResourceDBO());
		
		EasyMock.expect(mockPaymentResourceRepository.findAll()).andReturn(paymentResourceList);

		EasyMock.replay(mockPaymentResourceRepository);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources();
		assertNotNull(paymentResourceVOList);
		assertTrue(paymentResourceVOList.size() == 1);
		
		EasyMock.verify(mockPaymentResourceRepository);
	}
	@Test
	public void testFetchAllPaymentResourcesWithOrder_OrderIsEmpty() {
		String order = "";
		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources(order);
		assertNull(paymentResourceVOList);		
	}
	@Test
	public void testFetchAllPaymentResourcesWithOrder_OrderIsNull() {
		String order = null;
		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources(order);
		assertNull(paymentResourceVOList);		
	}
	@Test
	public void testFetchAllPaymentResourcesWithOrder_ExceptionFindingResources() {
		String order = Constants.ORDER_ASC;
		EasyMock.expect(mockPaymentResourceRepository.findAll(EasyMock.isA(Sort.class))).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources(order);
		assertNull(paymentResourceVOList);
		
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);
	}

	@Test
	public void testFetchAllPaymentResourcesWithOrder_NullResourcesReturned() {
		String order = Constants.ORDER_ASC;

		EasyMock.expect(mockPaymentResourceRepository.findAll(EasyMock.isA(Sort.class))).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources(order);
		assertNull(paymentResourceVOList);
		
		EasyMock.verify(mockPaymentResourceRepository);
	}

	@Test
	public void testFetchAllPaymentResourcesWithOrder_0ResourcesReturned() {
		String order = Constants.ORDER_ASC;
		List<PaymentResourceDBO> paymentResourceList = new LinkedList<>();
		Page<PaymentResourceDBO> paymentPage = new PageImpl<PaymentResourceDBO>(paymentResourceList);

		EasyMock.expect(mockPaymentResourceRepository.findAll(EasyMock.isA(Sort.class))).andReturn(paymentPage.getContent());

		EasyMock.replay(mockPaymentResourceRepository);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources(order);
		assertNull(paymentResourceVOList);

		EasyMock.verify(mockPaymentResourceRepository);
	}
	
	@Test
	public void testFetchAllPaymentResourcesWithOrder_SuccessAsc() {
		String order = Constants.ORDER_ASC;
		List<PaymentResourceDBO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(getDummyPaymentResourceDBO());
		Page<PaymentResourceDBO> paymentPage = new PageImpl<PaymentResourceDBO>(paymentResourceList);

		EasyMock.expect(mockPaymentResourceRepository.findAll(EasyMock.isA(Sort.class))).andReturn(paymentPage.getContent());

		EasyMock.replay(mockPaymentResourceRepository);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources(order);
		assertNotNull(paymentResourceVOList);
		assertTrue(paymentResourceVOList.size() == 1);
		
		EasyMock.verify(mockPaymentResourceRepository);
	}
	@Test
	public void testFetchAllPaymentResourcesWithOrder_SuccessDesc() {
		String order = Constants.ORDER_DESC;
		List<PaymentResourceDBO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(getDummyPaymentResourceDBO());
		Page<PaymentResourceDBO> paymentPage = new PageImpl<PaymentResourceDBO>(paymentResourceList);

		EasyMock.expect(mockPaymentResourceRepository.findAll(EasyMock.isA(Sort.class))).andReturn(paymentPage.getContent());

		EasyMock.replay(mockPaymentResourceRepository);

		List<PaymentResourceVO> paymentResourceVOList = paymentResourceService.fetchAllPaymentResources(order);
		assertNotNull(paymentResourceVOList);
		assertTrue(paymentResourceVOList.size() == 1);
		
		EasyMock.verify(mockPaymentResourceRepository);
	}
	@Test
	public void testCreatePaymentResource_PaymentResourceVO_PaymentResourceVOIsNull() {
		PaymentResourceVO paymentResourceVO  = null;

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceVO);
		assertNull(returnedPaymentResourceVO);
	}
	@Test
	public void testCreatePaymentResource_PaymentResourceVO_NullReturnedFromDB() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		PaymentResourceDBO dbReturnedPaymentResourceDBO = null;

		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andReturn(dbReturnedPaymentResourceDBO);

		EasyMock.replay(mockPaymentResourceRepository);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceVO);
		assertNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
	}
	@Test
	public void testCreatePaymentResource_PaymentResourceVO_Exception() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();

		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceVO);
		assertNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);

	}
	@Test
	public void testCreatePaymentResource_PaymentResourceVO_Success() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		PaymentResourceDBO dbReturnedPaymentResourceDBO = getDummyPaymentResourceDBO();

		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andReturn(dbReturnedPaymentResourceDBO);

		EasyMock.replay(mockPaymentResourceRepository);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceVO);
		assertNotNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
	}
	
	
	@Test
	public void testCreatePaymentResource_PaymentResourceDBO_PaymentResourceDBOIsNull() {
		PaymentResourceDBO paymentResourceDBO  = null;

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceDBO);
		assertNull(returnedPaymentResourceVO);
	}
	@Test
	public void testCreatePaymentResource_PaymentResourceDBO_NullReturnedFromDB() {
		PaymentResourceDBO paymentResourceDBO = getDummyPaymentResourceDBO();
		PaymentResourceDBO dbReturnedPaymentResourceDBO = null;

		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andReturn(dbReturnedPaymentResourceDBO);

		EasyMock.replay(mockPaymentResourceRepository);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceDBO);
		assertNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
	}
	@Test
	public void testCreatePaymentResource_PaymentResourceDBO_Exception() {
		PaymentResourceDBO paymentResourceDBO = getDummyPaymentResourceDBO();

		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceDBO);
		assertNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);

	}
	@Test
	public void testCreatePaymentResource_PaymentResourceDBO_Success() {
		PaymentResourceDBO paymentResourceDBO = getDummyPaymentResourceDBO();
		PaymentResourceDBO dbReturnedPaymentResourceDBO = getDummyPaymentResourceDBO();

		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andReturn(dbReturnedPaymentResourceDBO);

		EasyMock.replay(mockPaymentResourceRepository);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceDBO);
		assertNotNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
	}
	
	@Test
	public void testUpdatePaymentResource_PaymentResourceVOIsNull() {
		PaymentResourceVO paymentResourceVO = null;

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.updatePaymentResource(paymentResourceVO);
		assertNull(returnedPaymentResourceVO);
	}
	
	@Test
	public void testUpdatePaymentResource_Exception() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
	
		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.updatePaymentResource(paymentResourceVO);
		assertNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);
	}
	
	@Test
	public void testUpdatePaymentResource_Success() {
		PaymentResourceVO paymentResourceVO = getDummyPaymentResourceVO();
		PaymentResourceDBO dbReturnedPaymentResourceDBO = getDummyPaymentResourceDBO();

		EasyMock.expect(mockPaymentResourceRepository.save(EasyMock.isA(PaymentResourceDBO.class))).andReturn(dbReturnedPaymentResourceDBO);

		EasyMock.replay(mockPaymentResourceRepository);

		PaymentResourceVO returnedPaymentResourceVO = paymentResourceService.updatePaymentResource(paymentResourceVO);
		assertNotNull(returnedPaymentResourceVO);
	
		EasyMock.verify(mockPaymentResourceRepository);
	}
	
	@Test
	public void testDeletePaymentResource_IdIsEmpty() {
		String id = "";

		Boolean result = paymentResourceService.deletePaymentResource(id);
		assertFalse(result);	
	}
	@Test
	public void testDeletePaymentResource_IdIsNull() {
		String id = null;

		Boolean result = paymentResourceService.deletePaymentResource(id);
		assertFalse(result);	
	}
	
	@Test
	public void testDeletePaymentResource_Exception() {
		String id = "2";

		EasyMock.expect(mockPaymentResourceRepository.deleteById(EasyMock.anyString())).andThrow(mockDataAccessException);
		EasyMock.expect(mockDataAccessException.getMessage()).andReturn("message");
		EasyMock.expect(mockDataAccessException.getStackTrace()).andReturn(null);
		EasyMock.expect(mockDataAccessException.getCause()).andReturn(null);

		EasyMock.replay(mockPaymentResourceRepository);
		EasyMock.replay(mockDataAccessException);

		Boolean result = paymentResourceService.deletePaymentResource(id);
		assertFalse(result);
	
		EasyMock.verify(mockPaymentResourceRepository);
		EasyMock.verify(mockDataAccessException);
	}
	@Test
	public void testDeletePaymentResource_0RowsUpdated() {
		String id = "2";

		EasyMock.expect(mockPaymentResourceRepository.deleteById(EasyMock.anyString())).andReturn(0);

		EasyMock.replay(mockPaymentResourceRepository);

		Boolean result = paymentResourceService.deletePaymentResource(id);
		assertFalse(result);
	
		EasyMock.verify(mockPaymentResourceRepository);
	}
	@Test
	public void testDeletePaymentResource_Success1RowUpdated() {
		String id = "2";

		EasyMock.expect(mockPaymentResourceRepository.deleteById(EasyMock.anyString())).andReturn(1);

		EasyMock.replay(mockPaymentResourceRepository);

		Boolean result = paymentResourceService.deletePaymentResource(id);
		assertTrue(result);
	
		EasyMock.verify(mockPaymentResourceRepository);
	}
}
