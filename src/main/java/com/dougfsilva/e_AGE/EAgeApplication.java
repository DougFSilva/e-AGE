package com.dougfsilva.e_AGE;

import java.util.Random;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EAgeApplication.class, args);
		Random random = new Random();
        // Garante que o número seja sempre de 6 dígitos
        System.out.println(String.format("%06d", random.nextInt(1000000)));
	}

}
