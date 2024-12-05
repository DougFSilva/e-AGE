package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.dto.request.CreateAddressRequest;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAddress {

	private final AddressRepository repository;

	public Address execute(CreateAddressRequest request) {
		Address address = new Address(request.getCountry(), request.getState(), request.getPostalCode(), request.getCity(),
				request.getDistrict(), request.getStreet(), request.getNumber());
		return repository.save(address);
	}
}
