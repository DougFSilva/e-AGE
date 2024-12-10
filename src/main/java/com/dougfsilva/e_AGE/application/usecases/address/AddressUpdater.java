package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.dto.request.UpdateAddressRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.exception.AddressOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressUpdater {

	private final AddressRepository repository;
	private final AddressFinder addressFinder;
	private final StandardLogger logger;

	public Address update(UpdateAddressRequest request) {
		try {
			Address address = addressFinder.findByID(request.getID());
			updateClazzData(address, request);
			return repository.save(address);
		} catch (Exception e) {
			logger.error("Unexpected error when updating address: " + e.getMessage());
			throw new AddressOperationException("Error while update the address", e);
		}

	}

	private void updateClazzData(Address address, UpdateAddressRequest request) {
		if (request.getCountry() != null && request.getCountry().isBlank()) {
			address.setCountry(request.getCountry());
		}
		if (request.getState() != null && !request.getState().isBlank()) {
			address.setState(request.getState());
		}
		if (request.getPostalCode() != null && !request.getPostalCode().isBlank()) {
			address.setPostalCode(request.getPostalCode());
		}
		if (request.getCity() != null && !request.getCity().isBlank()) {
			address.setCity(request.getCity());
		}
		if (request.getDistrict() != null && !request.getDistrict().isBlank()) {
			address.setDistrict(request.getDistrict());
		}
		if (request.getStreet() != null && !request.getStreet().isBlank()) {
			address.setStreet(request.getStreet());
		}
		if (request.getNumber() != null && !request.getNumber().isBlank()) {
			address.setNumber(request.getNumber());
		}
	}
}
