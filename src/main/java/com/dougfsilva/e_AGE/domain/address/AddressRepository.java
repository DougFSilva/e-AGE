package com.dougfsilva.e_AGE.domain.address;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

public interface AddressRepository {

	Address save(Address address);
	void delete(Address address);
	Optional<Address> findByID(String ID);
	default Address findByIDOrThrow(String ID) {
		return findByID(ID).orElseThrow(() -> new ObjectNotFoundException("Address not found for ID: " + ID));
	}
	
}
