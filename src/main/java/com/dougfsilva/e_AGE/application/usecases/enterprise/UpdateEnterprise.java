package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.UpdateEnterpriseRequest;
import com.dougfsilva.e_AGE.application.usecases.address.UpdateAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateEnterprise {

	private final EnterpriseRepository repository;
	
	private final FindEnterprise findEnterprise;
	
	private final UpdateAddress updatedAddress;
	
	private final EnterpriseValidator validator;
	
	private final StandardLogger logger;
	
	public Enterprise execute(UpdateEnterpriseRequest request) {
		try {
			Enterprise enterprise = findEnterprise.findByID(request.getID());
			updateEnterpriseData(enterprise, request);
			Enterprise updatedEnterprise = repository.save(enterprise);
			logger.info(String.format("Updated Enterprise ID %s - %s", updatedEnterprise.getID(), updatedEnterprise.getName()));
			return updatedEnterprise;
		} catch (Exception e) {
			logger.error("Unexpected error when updating enterprise: " + e.getMessage());
			throw new EnterpriseOperationException("Error while update enterprise", e);
		}
	
	}
	
	private void updateEnterpriseData(Enterprise enterprise, UpdateEnterpriseRequest request) {
		if(request.getName() != null && !request.getName().isBlank() && !request.getName().equalsIgnoreCase(enterprise.getName())) {
			validator.validateUniqueName(request.getName());
			enterprise.setName(request.getName());
		}
		if(request.getTIN() != null && !request.getTIN().isBlank() && !request.getTIN().equalsIgnoreCase(enterprise.getTIN())) {
			validator.validateUniqueTIN(request.getTIN());
			enterprise.setTIN(request.getTIN());
		}
		if(request.getAddress() != null) {
			Address address = updatedAddress.execute(request.getAddress());
			enterprise.setAddress(address);
		}
	}
	
}
