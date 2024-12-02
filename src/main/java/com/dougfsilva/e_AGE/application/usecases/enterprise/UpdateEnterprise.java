package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.EnterpriseDataRequest;
import com.dougfsilva.e_AGE.application.usecases.Address.UpdatedAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateEnterprise {

	private final EnterpriseRepository repository;
	
	private final FindEnterprise findEnterprise;
	
	private final UpdatedAddress updatedAddress;
	
	private final StandardLogger logger;
	
	public Enterprise execute(String ID, EnterpriseDataRequest request) {
		Enterprise enterprise = findEnterprise.findByID(ID);
		if(request.TIN() != null && !request.TIN().isBlank()) {
			enterprise.setTIN(request.TIN());
		}
		if(request.name() != null && !request.name().isBlank()) {
			enterprise.setName(request.name());
		}
		Address address = updatedAddress.execute(enterprise.getAddress().getID(), request.addressDataRequest());
		enterprise.setAddress(address);
		Enterprise updatedEnterprise = repository.save(enterprise);
		logger.updatedObjectLog(updatedEnterprise);
		return updatedEnterprise;
	}
}
