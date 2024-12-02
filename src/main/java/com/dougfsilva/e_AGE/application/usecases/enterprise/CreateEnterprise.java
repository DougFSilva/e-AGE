package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.EnterpriseDataRequest;
import com.dougfsilva.e_AGE.application.usecases.Address.CreateAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateEnterprise {

	private final EnterpriseRepository repository;
	
	private final CreateAddress createAddress;
	
	private final StandardLogger logger;
	
	public Enterprise execute(EnterpriseDataRequest request) {
		Address address = createAddress.execute(request.addressDataRequest()); 
		Enterprise enterprise = new Enterprise(request.TIN(), request.name(), address);
		Enterprise createdEnterprise = repository.save(enterprise);
		logger.createdObjectLog(createdEnterprise);
		return createdEnterprise;
	}
	
}
