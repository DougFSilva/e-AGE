package com.dougfsilva.e_AGE.domain.student;

import java.util.List;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.guardian.Guardian;
import com.dougfsilva.e_AGE.domain.person.Person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(of = { "enrollment" })
@ToString
public class Student {

	private String ID;

	private String enrollment;

	private Person person;

	private List<Clazz> classes;

	private Guardian guardian;

	private Enterprise enterprise;

	private StudentStatus status;
	

	public Student(String enrollment, Person person, List<Clazz> classes, Enterprise enterprise) {
		this.enrollment = enrollment;
		this.person = person;
		this.classes = classes;
		this.enterprise = enterprise;
		this.status = StudentStatus.ENROLLED;
	}

	public Student(String enrollment, Person person, List<Clazz> classes) {
		this.enrollment = enrollment;
		this.person = person;
		this.classes = classes;
		this.status = StudentStatus.ENROLLED;
	}

	public Student(String enrollment, Person person, List<Clazz> classes, Guardian guardian, Enterprise enterprise) {
		this.enrollment = enrollment;
		this.person = person;
		this.classes = classes;
		this.guardian = guardian;
		this.enterprise = enterprise;
		this.status = StudentStatus.ENROLLED;
	}

}
