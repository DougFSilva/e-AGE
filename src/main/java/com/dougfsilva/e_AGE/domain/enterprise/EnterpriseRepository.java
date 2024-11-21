package com.dougfsilva.e_AGE.domain.enterprise;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EnterpriseRepository {

	Enterprise create(Enterprise enterprise);
	
	void delete(Enterprise enterprise);
	
	Enterprise update(Enterprise enterprise, Enterprise updatedEnterprise);
	
	Optional<Enterprise> findByID(String ID);
	
	List<Enterprise> findAllByNameContains(String name, PageRequest pageRequest);
	
	List<Enterprise> findAll(PageRequest pageRequest);
}
