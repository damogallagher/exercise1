/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.form3.Form3Exercise.rest.vo.PaymentResourceVO;
import com.form3.Form3Exercise.service.IPaymentResourceService;
import com.form3.Form3Exercise.vo.PaymentResourceDBO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Class to contain all api methods for PaymentResources
 * @author damien
 *
 */
@Api(value = "Payment Resource Rest Controller")
@RestController
@RequestMapping("/api/paymentResource")
public class PaymentResourceAPI {

	// private log variable
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentResourceAPI.class);

	@Autowired
	private IPaymentResourceService paymentResourceService;
	
	
	/**
	 * Method to seed the database from the rest endpoint (http://mockbin.org/bin/41ca3269-d8c4-4063-9fd5-f306814ff03f)
	 * TEMP Method - would never have this in a production environment
	 * @return
	 */
    @ApiOperation(notes = "Seed DB", value = "Seed DB", nickname = "seedDB")
    @ApiResponses({ @ApiResponse(code = 200, message = "DB Seeded!", response = PaymentResourceDBO.class) })
    @PostMapping(value = { "/seedDB" })
    public Boolean seedDB() {    	
    	LOGGER.debug("Entered seedDB");
    	Boolean seedDBResult = paymentResourceService.seedDB();
    	return seedDBResult;
    }
	
	
	/**
	 * Method to create a payment resource 
	 * @param paymentResourceVO
	 * @return
	 * @throws IOException 
	 */
    @ApiOperation(notes = "Create a Payment Resource", value = "Create a Payment Resource", nickname = "createPaymentResource")
    @ApiResponses({ @ApiResponse(code = 200, message = "Payment Resource Created!", response = PaymentResourceDBO.class) })
    @PostMapping(value = { "" })
    public PaymentResourceVO createPaymentResource(@RequestBody PaymentResourceVO paymentResourceVO, HttpServletResponse response) throws IOException {    	
    	LOGGER.debug("Entered createPaymentResource");
    	PaymentResourceVO newPaymentResourceVO = paymentResourceService.createPaymentResource(paymentResourceVO);
    	if (newPaymentResourceVO == null) {
    		LOGGER.error("Failed to create payment resource");
    		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		return newPaymentResourceVO;
    	}
    	return newPaymentResourceVO;
    }
    
	/**
	 * Method to update a payment resource 
	 * @param paymentResourceVO
	 * @return
	 * @throws IOException 
	 */
    @ApiOperation(notes = "Update a Payment Resource", value = "Update a Payment Resource", nickname = "updatePaymentResource")
    @ApiResponses({ @ApiResponse(code = 200, message = "Payment Resource Updated!", response = PaymentResourceDBO.class) })
    @PutMapping(value = { "" })
    public PaymentResourceVO updatePaymentResource(@RequestBody PaymentResourceVO paymentResourceVO, HttpServletResponse response) throws IOException {    	
    	LOGGER.debug("Entered updatePaymentResource");
    	PaymentResourceVO updatedPaymentResourceVO = paymentResourceService.updatePaymentResource(paymentResourceVO);
    	if (updatedPaymentResourceVO == null) {
    		LOGGER.error("Failed to update payment resource");
    		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		return updatedPaymentResourceVO;
    	}
    	return updatedPaymentResourceVO;
    }
    
	/**
	 * Method to delete a payment resource 
	 * @param paymentResourceVO
	 * @return
	 * @throws IOException 
	 */
    @ApiOperation(notes = "Delete a Payment Resource By Id", value = "Delete a Payment Resource By Id", nickname = "deletePaymentResourceById")
    @ApiResponses({ @ApiResponse(code = 200, message = "Payment Resource Deleted!", response = PaymentResourceDBO.class) })
    @DeleteMapping(value = { "/{id}" })
    public Boolean deletePaymentResource(@ApiParam(required = false, name = "id", example = "4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43") 
	@PathVariable(value = "id", required = true) String id, HttpServletResponse response) throws IOException {    	
    	LOGGER.debug("Entered deletePaymentResource");
    	Boolean result = paymentResourceService.deletePaymentResource(id);
    	if (BooleanUtils.isNotTrue(result)) {
    		LOGGER.error("Failed to delete payment resource");
    		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		return result;
    	}
    	
    	return result;
    }
    
	/**
	 * Method to fetch a payment resource based on the id
	 * @param id
	 * @return
	 * @throws IOException 
	 */
    @ApiOperation(notes = "Fetch a Payment Resource By Id", value = "Fetch a Payment Resource By Id", nickname = "fetchPaymentResourceById")
    @ApiResponses({ @ApiResponse(code = 200, message = "Payment Resource Retrieved!", response = PaymentResourceDBO.class) })
    @GetMapping(value = { "/{id}" })
    public PaymentResourceVO fetchPaymentResource(@ApiParam(required = false, name = "id", example = "4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43") 
    	@PathVariable(value = "id", required = true) String id, HttpServletResponse response) throws IOException {    	
    	LOGGER.debug("Entered fetchPaymentResource - id:{}", id);
    	PaymentResourceVO paymentResourceVO = paymentResourceService.fetchPaymentResource(id);
    	if (paymentResourceVO == null) {
    		LOGGER.error("Failed to find payment resource for the id {}", id);
    		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		return paymentResourceVO;
    	}
    	
    	return paymentResourceVO;
    }
	
    /**
	 * Method to fetch all payment resources
	 * @return
     * @throws IOException 
	 */
    @ApiOperation(notes = "Fetch all Payment Resources", value = "Fetch all Payment Resources", nickname = "fetchAllPaymentResources")
    @ApiResponses({ @ApiResponse(code = 200, message = "Payment Resources Retrieved!", response = List.class) })
    @GetMapping(value = { "/list" })
    public List<PaymentResourceVO> fetchAllPaymentResources(HttpServletResponse response) throws IOException {    	
    	LOGGER.debug("Entered fetchAllPaymentResources");
    	List<PaymentResourceVO> paymentResourceList = paymentResourceService.fetchAllPaymentResources();
    	if (CollectionUtils.isEmpty(paymentResourceList)) {
    		LOGGER.error("Failed to find payment resources");
    		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		return paymentResourceList;
    	}
    	return paymentResourceList;
    }
    
    
    /**
	 * Method to fetch a payment resource based on the id with order
	 * @param order - order in asc or desc order
	 * @return
     * @throws IOException 
	 */
    @ApiOperation(notes = "Fetch all Payment Resources with order", value = "Fetch all Payment Resources with order", nickname = "fetchAllPaymentResourcesWithOrder")
    @ApiResponses({ @ApiResponse(code = 200, message = "Payment Resources Retrieved!", response = List.class) })
    @GetMapping(value = { "/list/{order}" })
    public List<PaymentResourceVO> fetchAllPaymentResources(
	@ApiParam(required = false, name = "order", example = "desc / asc") 
	@PathVariable(value = "order", required = true) String order, HttpServletResponse response) throws IOException {    	
    	LOGGER.debug("Entered fetchAllPaymentResources");
    	List<PaymentResourceVO> paymentResourceList = paymentResourceService.fetchAllPaymentResources(order);
    	if (CollectionUtils.isEmpty(paymentResourceList)) {
    		LOGGER.error("Failed to find payment resources");
    		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		return paymentResourceList;
    	}
    	return paymentResourceList;
    }
    

}
