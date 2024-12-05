package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.CreateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.application.dto.request.UpdateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface TechnoogicalAreaService {

	TechnologicalArea create(CreateTechnologicalAreaRequest request);
	
	void delete(String ID);
	
	TechnologicalArea update(UpdateTechnologicalAreaRequest request);
	
	TechnologicalArea findByID(String ID);

	Page<TechnologicalArea> findAllByTitleContains(String title, PageRequest pageRequest);

	Page<TechnologicalArea> findAll(PageRequest pageRequest);
}
