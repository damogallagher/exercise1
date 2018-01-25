/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.config;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

@RunWith(EasyMockRunner.class)
public class BeanConfigTest {

	@TestSubject
	BeanConfig beanConfig = new BeanConfig();

	@Test
	public void testRestTemplate() {
		RestTemplate restTemplate = beanConfig.restTemplate();
		assertNotNull(restTemplate);
	}	
}
