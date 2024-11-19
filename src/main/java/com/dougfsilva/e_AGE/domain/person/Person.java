package com.dougfsilva.e_AGE.domain.person;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"rg"})
@ToString
public class Person {
	
	private User user;

	private String name;
	
	private String rg;
	
	private String phone;
	
	private String email;
	
	private LocalDate dateOfBirth;

	public Person(String name, String rg, String phone, String email, LocalDate dateOfBirth) {
		this.name = name;
		this.rg = rg;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
