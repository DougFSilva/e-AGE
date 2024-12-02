package com.dougfsilva.e_AGE.application.usecases.Address;

import com.dougfsilva.e_AGE.application.dto.request.AddressDataRequest;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatedAddress {

	private final AddressRepository repository;
	
	private final FindAddress findAddress;
	
	public Address execute(String ID, AddressDataRequest request) {
		Address address = findAddress.findByID(ID);
		if(request.country() != null && request.country().isBlank()) {
			address.setCountry(request.country());
		}
		if(request.state() != null && !request.state().isBlank()) {
			address.setState(request.state());
		}
		if(request.postalCode() != null && !request.postalCode().isBlank()) {
			address.setPostalCode(request.postalCode());
		}
		if(request.city() != null && !request.city().isBlank()) {
			address.setCity(request.city());
		}
		if(request.district() != null && !request.district().isBlank()) {
			address.setDistrict(request.district());
		}
		if(request.street() != null && !request.street().isBlank()) {
			address.setStreet(request.street());
		}
		if(request.number() != null && !request.number().isBlank()) {
			address.setNumber(request.number());
		}
		return repository.save(address);
	}
}
