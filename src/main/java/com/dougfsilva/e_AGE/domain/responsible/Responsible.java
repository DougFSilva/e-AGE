package com.dougfsilva.e_AGE.domain.responsible;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.person.Person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Responsible extends Person {
	
	public Responsible(String name, String rg, String phone, Email email, LocalDate dateOfBirth, Address address) {
		super(name, rg, phone, email, dateOfBirth, address);
	}

}
