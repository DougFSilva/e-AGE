package com.dougfsilva.e_AGE.application.usecases.Address;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAddress {

	private final AddressRepository repository;
	
	public Address findByID(String ID) {
		return repository.findById(ID).orElseThrow(() -> new ObjectNotFoundException(String.format("Address with ID %S not found!", ID)));
	}
}
