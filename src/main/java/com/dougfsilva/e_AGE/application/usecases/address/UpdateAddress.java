package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.dto.request.UpdateAddressRequest;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateAddress {

	private final AddressRepository repository;
	
	private final FindAddress findAddress;
	
	public Address execute(UpdateAddressRequest request) {
		Address address = findAddress.findByID(request.getID());
		if(request.getCountry() != null && request.getCountry().isBlank()) {
			address.setCountry(request.getCountry());
		}
		if(request.getState() != null && !request.getState().isBlank()) {
			address.setState(request.getState());
		}
		if(request.getPostalCode() != null && !request.getPostalCode().isBlank()) {
			address.setPostalCode(request.getPostalCode());
		}
		if(request.getCity() != null && !request.getCity().isBlank()) {
			address.setCity(request.getCity());
		}
		if(request.getDistrict() != null && !request.getDistrict().isBlank()) {
			address.setDistrict(request.getDistrict());
		}
		if(request.getStreet() != null && !request.getStreet().isBlank()) {
			address.setStreet(request.getStreet());
		}
		if(request.getNumber() != null && !request.getNumber().isBlank()) {
			address.setNumber(request.getNumber());
		}
		return repository.save(address);
	}
}
