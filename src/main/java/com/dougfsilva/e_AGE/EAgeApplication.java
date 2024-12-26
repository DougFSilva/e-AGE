package com.dougfsilva.e_AGE;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dougfsilva.e_AGE.dominio.endereco.Endereco;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EAgeApplication.class, args);
		
		 Endereco endereco = new Endereco(
		            "Brasil",
		            "São Paulo",
		            "12345-678",
		            "São Paulo",
		            "Centro",
		            "Rua Exemplo",
		            "123"
		        );

		        // Imprimindo o objeto
		        System.out.println(endereco.toString());
	}

}
