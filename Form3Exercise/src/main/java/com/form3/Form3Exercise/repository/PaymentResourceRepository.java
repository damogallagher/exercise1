/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.form3.Form3Exercise.vo.PaymentResourceDBO;

/**
 * Jpa interface for interacting with the database
 * @author damien
 *
 */
@RepositoryRestResource(collectionResourceRel = "paymentResource", path = "paymentResource")
public interface PaymentResourceRepository extends PagingAndSortingRepository<PaymentResourceDBO, Long> {

	/**
	 * Method to find a payment resource by the id
	 * @param id
	 * @return
	 */
	PaymentResourceDBO findById(@Param("id") String id);

	/**
	 * Method to delete a payment resource by the id
	 * @param id
	 * @return
	 */
	@Modifying
	int deleteById(@Param("id") String id);


}
