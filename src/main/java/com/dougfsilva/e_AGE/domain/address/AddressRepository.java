package com.dougfsilva.e_AGE.domain.address;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface AddressRepository {

	Address save(Address address);
	
	void delete(Address address);
	
	Optional<Address> findById(String ID);
	
	Page<Address> findAllByStateContains(String stateName, PageRequest pageRequest);
	
	Page<Address> findAllByCityContains(String cityName, PageRequest pageRequest);
	
	Page<Address> findAllByDistrictContains(String districtName, PageRequest pageRequest);
	
	Page<Address> findAll(PageRequest pageRequest);
	
}
