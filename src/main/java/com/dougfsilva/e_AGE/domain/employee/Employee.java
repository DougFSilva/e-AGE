package com.dougfsilva.e_AGE.domain.employee;

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
public class Employee extends Person{
	
	private String registration;

	private StaffRole staffRole;
	
	private Boolean active;

	public Employee(String name, Sex sex, String rg, String phone, Email email, LocalDate dateOfBirth,
			Address address, String registration, StaffRole staffRole, Boolean active) {
		super(name, sex, rg, phone, email, dateOfBirth, address);
		this.registration = registration;
		this.staffRole = staffRole;
		this.active = active;
	}

}
