package com.dougfsilva.e_AGE.domain.responsible;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface ResponsibleRepository {

	Responsible save(Responsible responsible);

	void delete(Responsible responsible);

	Optional<Responsible> findByID(String ID);
	
	Optional<Responsible> findByRg(String rg);
	
	Page<Responsible> findAllByNameContaining(String name, PageRequest pageRequest);

	Page<Responsible> findAll(PageRequest pageRequest);
	
}
