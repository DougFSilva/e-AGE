package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.person.Sex;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdatePersonRequest {

	private String ID;

	private String name;
	
	private Sex sex;

	private String rg;

	private String phone;

	private Email email;

	private LocalDate dateOfBirth;

	private UpdateAddressRequest address;

	public UpdatePersonRequest(String ID, String name, String sex, String rg, String phone, String email, LocalDate dateOfBirth,
			UpdateAddressRequest address) {
		if (ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		this.name = name;
		this.sex = Sex.fromLetter(sex);
		this.rg = rg;
		this.phone = phone;
		this.email = (email != null && !email.isBlank()) ? new Email(email) : null;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

}
