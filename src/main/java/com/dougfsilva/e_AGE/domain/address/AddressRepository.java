package com.dougfsilva.e_AGE.domain.address;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface AddressRepository {

	Address create(Address address);
	
	void delete(Address address);
	
	Address update (Address address, Address updatedAddress);
	
	Optional<Address> findById(String ID);
	
	List<Address> findAllByStateContains(String stateName, PageRequest pageRequest);
	
	List<Address> findAllByCityContains(String cityName, PageRequest pageRequest);
	
	List<Address> findAllByDistrictContains(String districtName, PageRequest pageRequest);
	
	List<Address> findAll(PageRequest pageRequest);
}
