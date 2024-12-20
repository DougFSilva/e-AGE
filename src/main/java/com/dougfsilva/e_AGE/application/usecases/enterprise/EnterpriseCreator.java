package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.CreateEnterpriseRequest;
import com.dougfsilva.e_AGE.application.usecases.address.AddressCreator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseValidationException;

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
			Enterprise savedEnterprise = repository.save(enterprise);
			logger.info(String.format("Created enterprise ID %s, %s", savedEnterprise.getID(), savedEnterprise.getName()));
			return savedEnterprise;
		} catch (EnterpriseValidationException e) {
			String message = String.format("Error while creating enterprise %s : %s", request.getName(), e.getMessage());
			logger.warn(message, e);
			throw new EnterpriseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating enterprise %s", request.getName(), e.getMessage());
			logger.error(message, e);
			throw new EnterpriseOperationException(message, e);
		}
	}
	
}
