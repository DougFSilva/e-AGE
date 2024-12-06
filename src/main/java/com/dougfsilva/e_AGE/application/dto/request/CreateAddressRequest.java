package com.dougfsilva.e_AGE.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
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

}
