package com.dougfsilva.e_AGE;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		// SpringApplication.run(EAgeApplication.class, args);
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();

		// Converte o Set para uma List e ordena
		List<String> sortedZoneIds = zoneIds.stream().sorted().collect(Collectors.toList());
		// Imprime os fusos horários ordenados
		//sortedZoneIds.forEach(System.out::println);
		System.out.println(LocalDateTime.now());
		System.out.println(ZonedDateTime.now());
		System.out.println(Instant.now());
	}

}
