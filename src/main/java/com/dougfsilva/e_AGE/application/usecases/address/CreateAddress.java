package com.dougfsilva.e_AGE.application.usecases.address;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAddress {

	private final AddressRepository repository;
	
	private final Logger logger;
	
	private final UserContext userContext;
	
	public Address create(String country, String state, String postalCode, String city, String district, String street) {
		AuthChecker authChecker = new AuthChecker(userContext, logger);
		authChecker.requireAdmin(getClass());
		Address address = new Address(country, state, postalCode, city, district, street);
		Address createdAddress = repository.create(address);
		logger.info(String.format("%S created by %S", createdAddress, new UserDto(userContext.getAuthenticatedUser())));
		return createdAddress;
	}
	
	public Address create(User user, String country, String state, String postalCode, String city, String district) {
		AuthChecker authChecker = new AuthChecker(userContext, logger);
		authChecker.requireAdmin(getClass());
		Address address = new Address(country, state, postalCode, city, district);
		Address createdAddress = repository.create(address);
		logger.info(String.format("%S created by %S", createdAddress, new UserDto(userContext.getAuthenticatedUser())));
		return createdAddress;
	}
	
}
