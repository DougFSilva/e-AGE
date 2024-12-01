package com.dougfsilva.e_AGE.application.dto.request;

public record EnterpriseDataRequest(
		String TIN,
		String name, 
		String country,
		String state, 
		String postalCode, 
		String city, 
		String district, 
		String street,
		String number ) {
}
