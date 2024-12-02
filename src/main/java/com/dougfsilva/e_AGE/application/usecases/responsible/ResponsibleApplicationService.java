package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.ResponsibleDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface ResponsibleApplicationService {

	ResponsibleResponse create(ResponsibleDataRequest request);
	
	void delete(String ID);
	
	ResponsibleResponse update(String ID, ResponsibleDataRequest request);
	
	ResponsibleResponse findByID(String ID);
	
	Page<ResponsibleResponse> findAll(PageRequest pageRequest);
}
