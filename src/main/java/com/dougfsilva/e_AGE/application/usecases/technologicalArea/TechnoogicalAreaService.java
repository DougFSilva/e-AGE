package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.TechnologicalAreaDataRequest;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface TechnoogicalAreaService {

	TechnologicalArea create(TechnologicalAreaDataRequest request);
	
	void delete(String ID);
	
	TechnologicalArea update(String ID, TechnologicalAreaDataRequest request);
	
	TechnologicalArea findByID(String ID);

	Page<TechnologicalArea> findAllByTitleContains(String title, PageRequest pageRequest);

	Page<TechnologicalArea> findAll(PageRequest pageRequest);
}
