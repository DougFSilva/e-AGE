package com.dougfsilva.e_AGE;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public class TestAddressRepository implements AddressRepository{

	@Override
	public Address create(Address address) {
		return new Address("Brazil", "São Paulo", "18125-000", "Alumínio", "Itararé");
	}

	@Override
	public void delete(Address address) {
		
	}

	@Override
	public Address update(Address address) {
		// TODO Auto-generated method stub
		return new Address(address.getID(), address.getCountry(), address.getState(), address.getCity(), address.getDistrict(), address.getStreet());
	}

	@Override
	public Optional<Address> findById(String ID) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(new Address("A321","Brazil", "São Paulo", "18125-000", "Alumínio", "Itararé", "Rua Jasiel"));
		//return Optional.ofNullable(null);
	}

	@Override
	public List<Address> findAllByStateContains(String stateName, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findAllByCityContains(String cityName, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findAllByDistrictContains(String districtName, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
