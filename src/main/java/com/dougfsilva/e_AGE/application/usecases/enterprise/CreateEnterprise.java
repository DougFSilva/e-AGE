package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.CreateEnterpriseRequest;
import com.dougfsilva.e_AGE.application.usecases.address.CreateAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateEnterprise {

	private final EnterpriseRepository repository;
	
	private final CreateAddress createAddress;
	
	private final StandardLogger logger;
	
	public Enterprise execute(CreateEnterpriseRequest request) {
		if(repository.existsByTIN(request.getTIN())) {
			throw new DataIntegrityViolationException(String.format("Enterprise with TIN %S already registered!", request.getTIN()));
		}
		Address address = createAddress.execute(request.getAddress()); 
		Enterprise enterprise = new Enterprise(request.getTIN(), request.getName(), address);
		Enterprise createdEnterprise = repository.save(enterprise);
		logger.info(String.format("Created Enterprise ID %S - %S", createdEnterprise.getID(), createdEnterprise.getName()));
		return createdEnterprise;
	}
	
}
