package com.dougfsilva.e_AGE.domain.student;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.person.Person;
import com.dougfsilva.e_AGE.domain.person.Sex;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Student extends Person {
	
	private Responsible responsible;

	private Enterprise enterprise;

	public Student(String name, Sex sex, String rg, String phone, Email email, LocalDate dateOfBirth,
			Address address, String image, Responsible responsible, Enterprise enterprise) {
		super(name, sex, rg, phone, email, dateOfBirth, address, image);
		this.responsible = responsible;
		this.enterprise = enterprise;
	}
	
	public Student(String name, Sex sex, String rg, String phone, Email email, LocalDate dateOfBirth,
			Address address, String image, Responsible responsible) {
		super(name, sex, rg, phone, email, dateOfBirth, address, image);
		this.responsible = responsible;
	}
	
	
}
