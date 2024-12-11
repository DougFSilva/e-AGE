package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.exception.AddressOperationException;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressDeleter {

	private final AddressRepository repository;
	private final AddressFinder addressFinder;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Address address = addressFinder.findByID(ID);
			repository.delete(address);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while deleting address ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting address ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new AddressOperationException(message, e);
		}

	}
}
