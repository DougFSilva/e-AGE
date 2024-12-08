package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.exception.AddressOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteAddress {

	private final AddressRepository repository;

	private final FindAddress findAddress;
	
	private final StandardLogger logger;

	public void execute(String ID) {
		try {
			Address address = findAddress.findByID(ID);
			repository.delete(address);
		} catch (Exception e) {
			logger.error("Unexpected error when deleting address: " + e.getMessage());
			throw new AddressOperationException("Error while delete address", e);
		}
		
	}
}
