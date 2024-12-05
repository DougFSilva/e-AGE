package com.dougfsilva.e_AGE.domain.responsible;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.person.Person;
import com.dougfsilva.e_AGE.domain.person.Sex;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Responsible extends Person {
	
	public Responsible(String name, Sex sex, String rg, String phone, Email email, LocalDate dateOfBirth, Address address) {
		super(name, sex, rg, phone, email, dateOfBirth, address);
	}
	
	public Responsible(String name, Sex sex, String rg, String phone, Email email, LocalDate dateOfBirth) {
		super(name, sex, rg, phone, email, dateOfBirth);
	}
	
}
