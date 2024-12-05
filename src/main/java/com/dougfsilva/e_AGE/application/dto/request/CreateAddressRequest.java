package com.dougfsilva.e_AGE.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateAddressRequest {

	private String country;
	
	private String state;
	
	private String postalCode;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String number;

	public CreateAddressRequest(String country, String state, String postalCode, String city, String district,
			String street, String number) {
		this.country = country;
		this.state = state;
		this.postalCode = postalCode;
		this.city = city;
		this.district = district;
		this.street = street;
		this.number = number;
	}
	
}
