package com.dougfsilva.e_AGE.application.dto.request;

public record EnterpriseDataRequest(
		String TIN,
		String name, 
		AddressDataRequest addressDataRequest ) {
}
