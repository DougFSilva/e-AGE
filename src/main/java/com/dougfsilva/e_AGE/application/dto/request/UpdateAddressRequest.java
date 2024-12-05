package com.dougfsilva.e_AGE.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateAddressRequest {
	
	private String ID;

	private String country;
	
	private String state;
	
	private String postalCode;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String number;

	public UpdateAddressRequest(String ID, String country, String state, String postalCode, String city,
			String district, String street, String number) {
		if(ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		this.country = country;
		this.state = state;
		this.postalCode = postalCode;
		this.city = city;
		this.district = district;
		this.street = street;
		this.number = number;
	}
	

}
