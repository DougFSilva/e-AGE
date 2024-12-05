package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteAddress {

	private final AddressRepository repository;

	private final FindAddress findAddress;

	public void execute(String ID) {
		Address address = findAddress.findByID(ID);
		repository.delete(address);
	}
}
