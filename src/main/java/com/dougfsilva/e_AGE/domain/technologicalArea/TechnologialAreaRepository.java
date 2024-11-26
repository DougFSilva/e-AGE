package com.dougfsilva.e_AGE.domain.technologicalArea;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface TechnologialAreaRepository {

	TechnologicalArea create(TechnologicalArea technologicalArea);

	void delete(TechnologicalArea technologicalArea);

	TechnologicalArea update(TechnologicalArea technologicalArea);

	Optional<TechnologicalArea> findByID(String ID);

	List<TechnologicalArea> findAllByTitleContains(String title, PageRequest pageRequest);

	List<TechnologicalArea> findAll(PageRequest pageRequest);

}
