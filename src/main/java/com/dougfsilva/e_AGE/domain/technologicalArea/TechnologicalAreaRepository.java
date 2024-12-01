package com.dougfsilva.e_AGE.domain.technologicalArea;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface TechnologicalAreaRepository {

	TechnologicalArea save(TechnologicalArea technologicalArea);

	void delete(TechnologicalArea technologicalArea);

	Optional<TechnologicalArea> findByID(String ID);

	Page<TechnologicalArea> findAllByTitleContains(String title, PageRequest pageRequest);
	
	Page<TechnologicalArea> findAll(PageRequest pageRequest);
	
}
