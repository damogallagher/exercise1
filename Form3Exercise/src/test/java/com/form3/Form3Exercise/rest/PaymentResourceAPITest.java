/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.service.IPaymentResourceService;
import com.form3.Form3Exercise.service.impl.BaseTestUtils;
import com.form3.Form3Exercise.utils.JsonUtils;

@RunWith(EasyMockRunner.class)
public class PaymentResourceAPITest extends BaseTestUtils{

	@TestSubject
	private PaymentResourceAPI paymentResourceAPI = new PaymentResourceAPI();

	@Mock
	private IPaymentResourceService mockPaymentResourceService;

	private MockMvc mockMvc;
	private PaymentResourceVO paymentResourceVO;
	@Before
	public void setUp() {
		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(paymentResourceAPI).build();	
		
		paymentResourceVO = getDummyPaymentResourceVO();
	}
	
	@Test
	public void testPostSeedDB_Failure() throws Exception {
		EasyMock.expect(mockPaymentResourceService.seedDB()).andReturn(false);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/paymentResource/seedDB"))
		.andExpect(status().isOk())
		.andExpect(content().string("false"));
		
		EasyMock.verify(mockPaymentResourceService);
	}
	
	@Test
	public void testPostSeedDB_Success() throws Exception {
		EasyMock.expect(mockPaymentResourceService.seedDB()).andReturn(true);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/paymentResource/seedDB"))
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
		
		EasyMock.verify(mockPaymentResourceService);
	}
	
	@Test
	public void testCreatePaymentResource_Failure() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = null;
		EasyMock.expect(mockPaymentResourceService.createPaymentResource(EasyMock.isA(PaymentResourceVO.class))).andReturn(returnPaymentResourceVO);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/paymentResource")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError())
		.andExpect(content().string(""));
		
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testCreatePaymentResource_FailureInvalidEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/paymentResource/1")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isMethodNotAllowed())
		.andExpect(content().string(""));		
	}
	@Test
	public void testCreatePaymentResource_Success() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		EasyMock.expect(mockPaymentResourceService.createPaymentResource(EasyMock.isA(PaymentResourceVO.class))).andReturn(returnPaymentResourceVO);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/paymentResource")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("type").value(returnPaymentResourceVO.getType()))
		.andExpect(jsonPath("id").value(returnPaymentResourceVO.getId()))
		.andExpect(jsonPath("organisation_id").value(returnPaymentResourceVO.getOrganisationId()))
		.andExpect(jsonPath("version").value(returnPaymentResourceVO.getVersion()))		
		.andExpect(jsonPath("attributes").exists())
		.andExpect(jsonPath("attributes.amount").value(returnPaymentResourceVO.getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}
	
	@Test
	public void testUpdatePaymentResource_Failure() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = null;
		EasyMock.expect(mockPaymentResourceService.updatePaymentResource(EasyMock.isA(PaymentResourceVO.class))).andReturn(returnPaymentResourceVO);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/paymentResource")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError())
		.andExpect(content().string(""));
		
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testUpdatePaymentResource_FailureInvalidEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/api/paymentResource/1")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isMethodNotAllowed())
		.andExpect(content().string(""));
	}
	@Test
	public void testUpdatePaymentResource_Success() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		EasyMock.expect(mockPaymentResourceService.updatePaymentResource(EasyMock.isA(PaymentResourceVO.class))).andReturn(returnPaymentResourceVO);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/paymentResource")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("type").value(returnPaymentResourceVO.getType()))
		.andExpect(jsonPath("id").value(returnPaymentResourceVO.getId()))
		.andExpect(jsonPath("organisation_id").value(returnPaymentResourceVO.getOrganisationId()))
		.andExpect(jsonPath("version").value(returnPaymentResourceVO.getVersion()))		
		.andExpect(jsonPath("attributes").exists())
		.andExpect(jsonPath("attributes.amount").value(returnPaymentResourceVO.getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testDeletePaymentResource_Failure() throws Exception {
		EasyMock.expect(mockPaymentResourceService.deletePaymentResource(EasyMock.anyString())).andReturn(false);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/paymentResource/1")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError())
		.andExpect(content().string("false"));
	}
	
	@Test
	public void testDeletePaymentResource_Success() throws Exception {
		EasyMock.expect(mockPaymentResourceService.deletePaymentResource(EasyMock.anyString())).andReturn(true);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/paymentResource/1")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
	}
	@Test
	public void testFetchPaymentResource_Failure() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = null;
		EasyMock.expect(mockPaymentResourceService.fetchPaymentResource(EasyMock.anyString())).andReturn(returnPaymentResourceVO);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/1")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError());
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResource_FailureInvalidEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/1/2")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isNotFound());
	}
	@Test
	public void testFetchPaymentResource_Success() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		EasyMock.expect(mockPaymentResourceService.fetchPaymentResource(EasyMock.anyString())).andReturn(returnPaymentResourceVO);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/1")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("type").value(returnPaymentResourceVO.getType()))
		.andExpect(jsonPath("id").value(returnPaymentResourceVO.getId()))
		.andExpect(jsonPath("organisation_id").value(returnPaymentResourceVO.getOrganisationId()))
		.andExpect(jsonPath("version").value(returnPaymentResourceVO.getVersion()))		
		.andExpect(jsonPath("attributes").exists())
		.andExpect(jsonPath("attributes.amount").value(returnPaymentResourceVO.getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResources_InvalidURL() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/1/2")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isNotFound());
	}
	@Test
	public void testFetchPaymentResources_NullPaymentResourceReturned() throws Exception {
		List<PaymentResourceVO> paymentResourceList = null;

		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources()).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError());
		EasyMock.verify(mockPaymentResourceService);
	}
	
	@Test
	public void testFetchPaymentResources_NoPaymentResourceReturned() throws Exception {
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();

		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources()).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError());
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResources_Success1PaymentResourceReturned() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(returnPaymentResourceVO);
		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources()).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("[0].type").value(paymentResourceList.get(0).getType()))
		.andExpect(jsonPath("[0].id").value(paymentResourceList.get(0).getId()))
		.andExpect(jsonPath("[0].organisation_id").value(paymentResourceList.get(0).getOrganisationId()))
		.andExpect(jsonPath("[0].version").value(paymentResourceList.get(0).getVersion()))		
		.andExpect(jsonPath("[0].attributes").exists())
		.andExpect(jsonPath("[0].attributes.amount").value(paymentResourceList.get(0).getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}
	
	@Test
	public void testFetchPaymentResources_Success2PaymentResourcesReturned() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(returnPaymentResourceVO);
		paymentResourceList.add(returnPaymentResourceVO);
		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources()).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("[0].type").value(paymentResourceList.get(0).getType()))
		.andExpect(jsonPath("[0].id").value(paymentResourceList.get(0).getId()))
		.andExpect(jsonPath("[0].organisation_id").value(paymentResourceList.get(0).getOrganisationId()))
		.andExpect(jsonPath("[0].version").value(paymentResourceList.get(0).getVersion()))		
		.andExpect(jsonPath("[0].attributes").exists())
		.andExpect(jsonPath("[0].attributes.amount").value(paymentResourceList.get(0).getAttributes().getAmount()))
		.andExpect(jsonPath("[1].type").value(paymentResourceList.get(1).getType()))
		.andExpect(jsonPath("[1].id").value(paymentResourceList.get(1).getId()))
		.andExpect(jsonPath("[1].organisation_id").value(paymentResourceList.get(1).getOrganisationId()))
		.andExpect(jsonPath("[1].version").value(paymentResourceList.get(1).getVersion()))		
		.andExpect(jsonPath("[1].attributes").exists())
		.andExpect(jsonPath("[1].attributes.amount").value(paymentResourceList.get(1).getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingAscOrder_InvalidUrl() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/asc/2")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isNotFound());
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingAscOrder_NullPaymentResourcsReturned() throws Exception {
		List<PaymentResourceVO> paymentResourceList = null;

		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/asc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError());
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingAscOrder_NoPaymentResourcsReturned() throws Exception {
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();

		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/asc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError());
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingAscOrder_Success1PaymentResourceReturned() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(returnPaymentResourceVO);
		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/asc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("[0].type").value(paymentResourceList.get(0).getType()))
		.andExpect(jsonPath("[0].id").value(paymentResourceList.get(0).getId()))
		.andExpect(jsonPath("[0].organisation_id").value(paymentResourceList.get(0).getOrganisationId()))
		.andExpect(jsonPath("[0].version").value(paymentResourceList.get(0).getVersion()))		
		.andExpect(jsonPath("[0].attributes").exists())
		.andExpect(jsonPath("[0].attributes.amount").value(paymentResourceList.get(0).getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}

	@Test
	public void testFetchPaymentResourcesSpecifyingAscOrder_Success2PaymentResourcesReturned() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(returnPaymentResourceVO);
		paymentResourceList.add(returnPaymentResourceVO);
		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/asc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("[0].type").value(paymentResourceList.get(0).getType()))
		.andExpect(jsonPath("[0].id").value(paymentResourceList.get(0).getId()))
		.andExpect(jsonPath("[0].organisation_id").value(paymentResourceList.get(0).getOrganisationId()))
		.andExpect(jsonPath("[0].version").value(paymentResourceList.get(0).getVersion()))		
		.andExpect(jsonPath("[0].attributes").exists())
		.andExpect(jsonPath("[0].attributes.amount").value(paymentResourceList.get(0).getAttributes().getAmount()))
		.andExpect(jsonPath("[1].type").value(paymentResourceList.get(1).getType()))
		.andExpect(jsonPath("[1].id").value(paymentResourceList.get(1).getId()))
		.andExpect(jsonPath("[1].organisation_id").value(paymentResourceList.get(1).getOrganisationId()))
		.andExpect(jsonPath("[1].version").value(paymentResourceList.get(1).getVersion()))		
		.andExpect(jsonPath("[1].attributes").exists())
		.andExpect(jsonPath("[1].attributes.amount").value(paymentResourceList.get(1).getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingDescOrder_InvalidUrl() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/desc/2")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isNotFound());
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingDescOrder_NullPaymentResourcsReturned() throws Exception {
		List<PaymentResourceVO> paymentResourceList = null;

		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/desc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError());
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingDescOrder_NoPaymentResourcsReturned() throws Exception {
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();

		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/desc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isInternalServerError());
		EasyMock.verify(mockPaymentResourceService);
	}
	@Test
	public void testFetchPaymentResourcesSpecifyingDescOrder_Success1PaymentResourceReturned() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(returnPaymentResourceVO);
		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/desc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("[0].type").value(paymentResourceList.get(0).getType()))
		.andExpect(jsonPath("[0].id").value(paymentResourceList.get(0).getId()))
		.andExpect(jsonPath("[0].organisation_id").value(paymentResourceList.get(0).getOrganisationId()))
		.andExpect(jsonPath("[0].version").value(paymentResourceList.get(0).getVersion()))		
		.andExpect(jsonPath("[0].attributes").exists())
		.andExpect(jsonPath("[0].attributes.amount").value(paymentResourceList.get(0).getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}

	@Test
	public void testFetchPaymentResourcesSpecifyingDescOrder_Success2PaymentResourcesReturned() throws Exception {
		PaymentResourceVO returnPaymentResourceVO = paymentResourceVO;
		List<PaymentResourceVO> paymentResourceList = new LinkedList<>();
		paymentResourceList.add(returnPaymentResourceVO);
		paymentResourceList.add(returnPaymentResourceVO);
		EasyMock.expect(mockPaymentResourceService.fetchAllPaymentResources(EasyMock.anyString())).andReturn(paymentResourceList);
		
		EasyMock.replay(mockPaymentResourceService);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/paymentResource/list/desc")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(JsonUtils.convertObjectToJson(paymentResourceVO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("[0].type").value(paymentResourceList.get(0).getType()))
		.andExpect(jsonPath("[0].id").value(paymentResourceList.get(0).getId()))
		.andExpect(jsonPath("[0].organisation_id").value(paymentResourceList.get(0).getOrganisationId()))
		.andExpect(jsonPath("[0].version").value(paymentResourceList.get(0).getVersion()))		
		.andExpect(jsonPath("[0].attributes").exists())
		.andExpect(jsonPath("[0].attributes.amount").value(paymentResourceList.get(0).getAttributes().getAmount()))
		.andExpect(jsonPath("[1].type").value(paymentResourceList.get(1).getType()))
		.andExpect(jsonPath("[1].id").value(paymentResourceList.get(1).getId()))
		.andExpect(jsonPath("[1].organisation_id").value(paymentResourceList.get(1).getOrganisationId()))
		.andExpect(jsonPath("[1].version").value(paymentResourceList.get(1).getVersion()))		
		.andExpect(jsonPath("[1].attributes").exists())
		.andExpect(jsonPath("[1].attributes.amount").value(paymentResourceList.get(1).getAttributes().getAmount()));
		EasyMock.verify(mockPaymentResourceService);
	}
}
