package com.dougfsilva.e_AGE.domain.enterprise;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EnterpriseRepository {

	Enterprise save(Enterprise enterprise);
	
	void delete(Enterprise enterprise);
	
	Optional<Enterprise> findByID(String ID);
	
	Page<Enterprise> findAllByNameContains(String name, PageRequest pageRequest);
	
	Page<Enterprise> findAll(PageRequest pageRequest);
	
	Boolean existsByAddress(Address address);
	
}
