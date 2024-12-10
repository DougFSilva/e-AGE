package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.person.Sex;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class CreatePersonRequest {

	private String name;
	private Sex sex;
	private String rg;
	private String phone;
	private Email email;
	private LocalDate dateOfBirth;
	private CreateAddressRequest address;
	private Boolean createDefaultUser;

	public CreatePersonRequest(String name, String sex, String rg, String phone, String email, LocalDate dateOfBirth,
			CreateAddressRequest address, Boolean createDefaultUser) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be null or empty!");
		}
		if (rg == null || rg.isBlank()) {
			throw new IllegalArgumentException("RG cannot be null or empty!");
		}
		if (phone == null || phone.isBlank()) {
			throw new IllegalArgumentException("Phone cannot be null or empty!");
		}
		this.name = name;
		this.sex = Sex.fromLetter(sex);
		this.rg = rg;
		this.phone = phone;
		this.email = (email != null && !email.isBlank()) ? new Email(email) : null;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.createDefaultUser = createDefaultUser;
	}

}
