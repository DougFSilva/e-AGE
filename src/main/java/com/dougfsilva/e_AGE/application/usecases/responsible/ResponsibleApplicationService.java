package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.CreateResponsibleRequest;
import com.dougfsilva.e_AGE.application.dto.request.UpdateResponsibleRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface ResponsibleApplicationService {

	ResponsibleResponse create(CreateResponsibleRequest request);
	
	void delete(String ID);
	
	ResponsibleResponse update(String ID, UpdateResponsibleRequest request);
	
	ResponsibleResponse findByID(String ID);
	
	Page<ResponsibleResponse> findAllByNameContaining(String name, PageRequest pageRequest);
	
	Page<ResponsibleResponse> findAll(PageRequest pageRequest);
}
