package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.CreateEnterpriseRequest;
import com.dougfsilva.e_AGE.application.usecases.address.AddressCreator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseCreator {

	private final EnterpriseRepository repository;
	private final AddressCreator addressCreator;
	private final EnterpriseValidator validator;
	private final StandardLogger logger;
	
	public Enterprise create(CreateEnterpriseRequest request) {
		try {
			validator.uniqueTIN(request.getTIN());
			validator.uniqueName(request.getName());
			Address address = addressCreator.create(request.getAddress()); 
			Enterprise enterprise = new Enterprise(request.getTIN(), request.getName(), address);
			Enterprise createdEnterprise = repository.save(enterprise);
			logger.info(String.format("Created Enterprise ID %s - %s", createdEnterprise.getID(), createdEnterprise.getName()));
			return createdEnterprise;
		} catch (Exception e) {
			logger.error("Unexpected error when creating enterprise: " + e.getMessage());
			throw new EnterpriseOperationException("Error while create enterprise", e);
		}
	}
	
}
