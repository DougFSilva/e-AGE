package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

public record ResponsibleDataRequest(
		String studentID,
		String name, 
		String rg, 
		String phone, 
		String email, 
		LocalDate dateOfBirth,
		Boolean sameResidence,
		AddressDataRequest addressDataRequest) {
}
