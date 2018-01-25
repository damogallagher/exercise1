/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.config;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import springfox.documentation.spring.web.plugins.Docket;


@RunWith(EasyMockRunner.class)
public class SwaggerConfigTest {

	@TestSubject
	SwaggerConfig swaggerConfig = new SwaggerConfig();


	@Test
	public void testAPIMethod() {
		Docket docket = swaggerConfig.api();
		assertNotNull(docket);
	}

}
