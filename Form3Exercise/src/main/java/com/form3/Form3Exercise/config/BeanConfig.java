/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * All beans to be configured are created in this class
 * @author damien
 *
 */
@Configuration
public class BeanConfig {

	/**
	 * Bean for the rest template - used to seed the DB from the url supplied with exercise
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
        //Add the Jackson Message converter
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		List<MediaType> supportedMediaTypes = new LinkedList<>();
		supportedMediaTypes.add(MediaType.ALL);
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes); 
		
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(mappingJackson2HttpMessageConverter);
		
		
		//messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}
}
