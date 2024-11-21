package com.dougfsilva.e_AGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dougfsilva.e_AGE.application.usecases.address.CreateAddress;
import com.dougfsilva.e_AGE.domain.address.Address;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAgeApplication.class, args);
		
		CreateAddress createAddress = new CreateAddress(new TestAddressRepository(), new TestLogger(), new TestUserContext());
		Address address = createAddress.create("Brazil", "SP", "1823232", "Alumínio", "Itararé", "rua");
		System.out.println(address);
	}

}
