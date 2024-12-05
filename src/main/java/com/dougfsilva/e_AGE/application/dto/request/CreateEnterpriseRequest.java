package com.dougfsilva.e_AGE.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateEnterpriseRequest {

	private String TIN; 
	
	private String name;
	
	private CreateAddressRequest address;
	
	public CreateEnterpriseRequest(String TIN, String name, CreateAddressRequest address) {
		if (TIN == null || TIN.isBlank()) {
			throw new IllegalArgumentException("TIN cannot be null or empty!");
		}
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be null or empty!");
		}
		this.TIN = TIN;
		this.name = name;
		this.address = address;
	}
}
