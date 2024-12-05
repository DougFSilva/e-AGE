package com.dougfsilva.e_AGE.domain.address;

import java.util.Optional;

public interface AddressRepository {

	Address save(Address address);
	
	void delete(Address address);
	
	Optional<Address> findById(String ID);
	
}
