package com.dougfsilva.e_AGE.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateEnterpriseRequest {
	
	private String ID;
	private String TIN;
	private String name;
	private UpdateAddressRequest address;

	public UpdateEnterpriseRequest(String ID, String tIN, String name, UpdateAddressRequest address) {
		if(ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		TIN = tIN;
		this.name = name;
		this.address = address;
	}
	
	
}
