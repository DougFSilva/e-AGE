package com.dougfsilva.e_AGE.application.address;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.AuthorizationChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

public class CreateAddress {

	private final AddressRepository repository;
	
	private final Logger logger;
	
	public CreateAddress(AddressRepository repository, Logger logger) {
		this.repository = repository;
		this.logger = logger;
	}


	public Address create(User user, String country, String state, String postalCode, String city, String district, String street) {
		AuthorizationChecker checker = new AuthorizationChecker(user, logger, this.getClass());
		checker.requireAdmin();
		Address address = new Address(country, state, postalCode, city, district, street);
		Address createdAddress = repository.create(address);
		logger.info(String.format("Address %S created by User %S", createdAddress, user));
		return createdAddress;
	}
	
	public Address create(User user, String country, String state, String postalCode, String city, String district) {
		AuthorizationChecker checker = new AuthorizationChecker(user, logger, this.getClass());
		checker.requireAdmin();
		Address address = new Address(country, state, postalCode, city, district);
		Address createdAddress = repository.create(address);
		logger.info(String.format("Address %S created by User %S", createdAddress, user));
		return createdAddress;
	}
	
}
