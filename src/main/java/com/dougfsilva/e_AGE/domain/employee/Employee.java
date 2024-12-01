package com.dougfsilva.e_AGE.domain.employee;

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
public class Employee extends Person{
	
	private String enrollment;

	private StaffRole staffRole;

	public Employee(String name, String rg, String phone, Email email, LocalDate dateOfBirth, Address address,
			String enrollment, StaffRole staffRole) {
		super(name, rg, phone, email, dateOfBirth, address);
		this.enrollment = enrollment;
		this.staffRole = staffRole;
	}

	
}
