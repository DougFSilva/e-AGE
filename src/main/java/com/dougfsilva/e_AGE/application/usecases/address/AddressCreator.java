package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.dto.request.CreateAddressRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.exception.AddressOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressCreator {

	private final AddressRepository repository;
	private final StandardLogger logger;

	public Address create(CreateAddressRequest request) {
		try {
			Address address = buildAddress(request);
			return repository.save(address);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating address : %s", e.getMessage());
			logger.error(message, e);
			throw new AddressOperationException(message, e);
		}
	}
	
	private Address buildAddress(CreateAddressRequest request) {
		return new Address(
				request.getCountry(), 
				request.getState(), 
				request.getPostalCode(),
				request.getCity(), 
				request.getDistrict(), 
				request.getStreet(), 
				request.getNumber());
	}
}
