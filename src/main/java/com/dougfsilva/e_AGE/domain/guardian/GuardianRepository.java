package com.dougfsilva.e_AGE.domain.guardian;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface GuardianRepository {

	Guardian create(Guardian guardian);
	
	void delete(Guardian guardian);
	
	Guardian update(Guardian guardian, Guardian updatedGuardian);
	
	Optional<Guardian> findByID(String ID);
	
	List<Guardian> findAll(PageRequest pageRequest);
}
