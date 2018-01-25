/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main class for the SpringBootApp
 * @author damien
 *
 */
@ComponentScan({ "com.form3.Form3Exercise" })
@EntityScan({ "com.form3.Form3Exercise" })
@EnableTransactionManagement
@SpringBootApplication
public class Form3ExerciseApplication {

	/**
	 * Main method to kick off the spring boot application
	 * See https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/ for the spring boot documentation for the version of spring boot we are using
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Form3ExerciseApplication.class);
	}
}
