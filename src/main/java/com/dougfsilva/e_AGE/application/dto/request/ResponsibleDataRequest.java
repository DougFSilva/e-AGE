package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

public record ResponsibleDataRequest(
		String name, 
		String rg, 
		String phone, 
		String email, 
		LocalDate dateOfBirth,
		String country,
		String state, 
		String postalCode, 
		String city, 
		String district, 
		String street,
		String number) {
}
