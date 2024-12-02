package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.EnterpriseDataRequest;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EnterpriseApplicationService {

	Enterprise create(EnterpriseDataRequest request);
	
	void delete(String ID);
	
	Enterprise update(String ID, EnterpriseDataRequest request);
	
	Enterprise findByID(String ID);
	
	Page<Enterprise> findAllByNameContains(String name, PageRequest pageRequest);
	
	Page<Enterprise> findAll(PageRequest pageRequest);
}
