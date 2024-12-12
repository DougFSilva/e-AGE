package com.dougfsilva.e_AGE.domain.technologicalArea;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface TechnologicalAreaRepository {

	TechnologicalArea save(TechnologicalArea technologicalArea);
	void delete(TechnologicalArea technologicalArea);
	Optional<TechnologicalArea> findByID(String ID);
	default TechnologicalArea findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("Technological area not found for ID: " + ID));
	}
	Optional<TechnologicalArea> findByTitle(String title);
	Page<TechnologicalArea> findAllByTitleContains(String title, PageRequest pageRequest);
	Page<TechnologicalArea> findAll(PageRequest pageRequest);
	Boolean existsByTitle(String title);
	
}
