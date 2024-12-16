package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.dto.request.UpdateAddressRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.exception.AddressOperationException;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressUpdater {

	private final AddressRepository repository;
	private final StandardLogger logger;

	public Address update(UpdateAddressRequest request) {
		try {
			Address address = repository.findByIDOrThrow(request.getID());
			Address updatedAddress = updateAddressData(address, request);
			return repository.save(updatedAddress);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while updating address ID %s : %s", request.getID(), e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating address ID %s : %s ", request.getID(), e.getMessage());
			logger.error(message, e);
			throw new AddressOperationException(message, e);
		}
	}

	private Address updateAddressData(Address address, UpdateAddressRequest request) {
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
		return address;
	}
}
