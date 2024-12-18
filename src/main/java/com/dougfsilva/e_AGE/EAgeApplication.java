package com.dougfsilva.e_AGE;

import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dougfsilva.e_AGE.domain.user.User;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		// SpringApplication.run(EAgeApplication.class, args);
		Optional<User> user = Optional.of(new User("", null, null, null));
	}

}
