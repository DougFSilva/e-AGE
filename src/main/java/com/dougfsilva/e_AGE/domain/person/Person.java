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
public class Person {

	private String ID;
	private User user;
	private String name;
	private Sex sex;
	private String rg;
	private String phone;
	private Email email;
	private LocalDate dateOfBirth;
	private Address address;
	private String image;

	public Person(User user, String name, Sex sex, String rg, String phone, Email email, LocalDate dateOfBirth,
			Address address, String image) {
		this.user = user;
		this.name = name;
		this.sex = sex;
		this.rg = rg;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.image = image;
	}


	public Person(String name, Sex sex, String rg, String phone, Email email, LocalDate dateOfBirth,
			Address address, String image) {
		this.name = name;
		this.sex = sex;
		this.rg = rg;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.image = image;
	}

}
