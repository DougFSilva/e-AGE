package com.dougfsilva.e_AGE.domain.student;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.guardian.Guardian;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.person.Person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Student extends Person {

	private String enrollment;

	private List<Clazz> clazzes;

	private Guardian guardian;

	private Enterprise enterprise;

	private StudentStatus status;

	public Student(String name, String rg, String phone, Email email, LocalDate dateOfBirth, Address address,
			String enrollment, List<Clazz> clazzes, Guardian guardian, Enterprise enterprise, StudentStatus status) {
		super(name, rg, phone, email, dateOfBirth, address);
		this.enrollment = enrollment;
		this.clazzes = clazzes;
		this.guardian = guardian;
		this.enterprise = enterprise;
		this.status = status;
	}
	
}
