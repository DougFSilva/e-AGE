package com.dougfsilva.e_AGE.application.usecases.Address;

import com.dougfsilva.e_AGE.application.dto.request.AddressDataRequest;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAddress {

	private final AddressRepository repository;

	public Address execute(AddressDataRequest request) {
		Address address = new Address(request.country(), request.state(), request.postalCode(), request.city(),
				request.district(), request.street(), request.number());
		return repository.save(address);
	}
}
