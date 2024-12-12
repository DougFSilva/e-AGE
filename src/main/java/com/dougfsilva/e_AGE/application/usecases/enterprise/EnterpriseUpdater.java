package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.UpdateEnterpriseRequest;
import com.dougfsilva.e_AGE.application.usecases.address.AddressUpdater;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseUpdater {

	private final EnterpriseRepository repository;
	private final AddressUpdater addressUpdater;
	private final EnterpriseValidator validator;
	
	private final StandardLogger logger;
	
	public Enterprise update(UpdateEnterpriseRequest request) {
		try {
			Enterprise enterprise = repository.findByIdOrThrow(request.getID());
			updateEnterpriseData(enterprise, request);
			Enterprise updatedEnterprise = repository.save(enterprise);
			logger.info(String.format("Updated enterprise ID %s - %s", updatedEnterprise.getID(), updatedEnterprise.getName()));
			return updatedEnterprise;
		} catch (ObjectNotFoundException | EnterpriseValidationException e) {
			String message = String.format("Error while updating enterprise ID %s : %s", request.getID(), e.getMessage());
			logger.warn(message, e);
			throw new EnterpriseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating enterprise ID %s : %s", request.getID() , e.getMessage());
			logger.error(message, e);
			throw new EnterpriseOperationException(message, e);
		}
	}
	
	private void updateEnterpriseData(Enterprise enterprise, UpdateEnterpriseRequest request) {
		if(request.getName() != null && !request.getName().isBlank() && !request.getName().equalsIgnoreCase(enterprise.getName())) {
			validator.uniqueName(request.getName());
			enterprise.setName(request.getName());
		}
		if(request.getTIN() != null && !request.getTIN().isBlank() && !request.getTIN().equalsIgnoreCase(enterprise.getTIN())) {
			validator.uniqueTIN(request.getTIN());
			enterprise.setTIN(request.getTIN());
		}
		if(request.getAddress() != null) {
			Address address = addressUpdater.update(request.getAddress());
			enterprise.setAddress(address);
		}
	}
}
