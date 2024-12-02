package com.dougfsilva.e_AGE.domain.person;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "ID", "rg" })
@ToString
public abstract class Person {

	private String ID;

	private User user;

	private String name;

	private String rg;

	private String phone;

	private Email email;

	private LocalDate dateOfBirth;

	private Address address;

	public Person(String name, String rg, String phone, Email email, LocalDate dateOfBirth, Address address) {
		this.name = name;
		this.rg = rg;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	
	public Person(String name, String rg, String phone, Email email, LocalDate dateOfBirth) {
		this.name = name;
		this.rg = rg;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

}
