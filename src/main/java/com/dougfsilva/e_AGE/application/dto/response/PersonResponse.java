package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.person.Sex;
import com.dougfsilva.e_AGE.domain.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = {"ID", "rg"})
@ToString
public abstract class PersonResponse {

	private String ID;

	private UserResponse user;

	private String name;
	
	private Sex sex;

	private String rg;

	private String phone;

	private String email;

	private LocalDate dateOfBirth;

	private Address address;

	public PersonResponse(String ID, User user, String name, Sex sex, String rg, String phone, String email,
			LocalDate dateOfBirth, Address address) {
		this.ID = ID;
		this.user = user != null ? UserResponse.fromUser(user) : null;
		this.name = name;
		this.sex = sex;
		this.rg = rg;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

}
