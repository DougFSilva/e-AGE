package com.dougfsilva.e_AGE.domain.employee;

import com.dougfsilva.e_AGE.domain.person.Person;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "enrollment" })
@ToString
public class Employee {
	
	private String ID;

	private String enrollment;

	private Person person;

	private StaffRole staffRole;

	public Employee(String enrollment, Person person, StaffRole staffRole) {
		this.enrollment = enrollment;
		this.person = person;
		this.staffRole = staffRole;
	}

}
