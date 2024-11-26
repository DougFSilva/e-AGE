package com.dougfsilva.e_AGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAgeApplication.class, args);
		AddressRepository repository = new TestAddressRepository();
		Logger logger= new TestLogger();
		AuthChecker authChecker = new AuthChecker(new TestUserContext(), new TestLogger());
		//deleteAddress.delete("A123");
		//updateAddress.update("A123", "Peru", "Cuzco", "12345-678", "chile_city", "chile_district", "chile_street");
	}

}
