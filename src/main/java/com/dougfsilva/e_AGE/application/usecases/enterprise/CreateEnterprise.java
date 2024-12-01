package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.AddressDataRequest;
import com.dougfsilva.e_AGE.application.dto.request.EnterpriseDataRequest;
import com.dougfsilva.e_AGE.application.usecases.address.CreateAddress;
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
		Address createdAddress = createAddress.execute(new AddressDataRequest(request.country(), request.state(), request.postalCode(), request.city(),
				request.district(), request.street(), request.number()));
		Enterprise enterprise = new Enterprise(request.TIN(), request.name(), createdAddress);
		Enterprise createdEnterprise = repository.save(enterprise);
		logger.createdObjectLog(createdEnterprise);
		return createdEnterprise;
	}
	
}
