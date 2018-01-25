/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.utils;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class ConstantsTest {

	@TestSubject
	Constants constants = new Constants();
	
	@Test
	public void testConstructor() {
		constants = new Constants();
		assertNotNull(constants);
	}
}
