package com.dougfsilva.e_AGE.domain.guardian;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface GuardianRepository {

	Guardian save(Guardian guardian);
	
	void delete(Guardian guardian);
	
	Optional<Guardian> findByID(String ID);
	
	Page<Guardian> findAll(PageRequest pageRequest);
}
