/*
 * Copyright (c) 2018, Damien Gallagher. All rights reserved.
 */
package com.form3.Form3Exercise.rest.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rest bean for the payment resource
 * @author damien
 *
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class PaymentResourceVO {

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("version")
    private Integer version;

	@JsonProperty("organisation_id")
	private String organisationId;
	
	@JsonProperty("attributes")
	private PaymentResourceAttributeVO attributes;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	public PaymentResourceAttributeVO getAttributes() {
		return attributes;
	}

	public void setAttributes(PaymentResourceAttributeVO attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "PaymentResourceVO [type=" + type + ", id=" + id + ", version=" + version + ", organisationId="
				+ organisationId + "]";
	}
	
	
}
