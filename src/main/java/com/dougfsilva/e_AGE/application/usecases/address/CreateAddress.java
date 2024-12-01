package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.dto.request.AddressDataRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAddress {

	private final AddressRepository repository;
	
	private final StandardLogger logger;
	
	public Address execute(AddressDataRequest request) {
		Address address = new Address(request.country(), request.state(), request.postalCode(), request.city(),
				request.district(), request.street(), request.number());
		Address createdAddress = repository.save(address);
		logger.createdObjectLog(createdAddress);
		return createdAddress;
	}
	
}
