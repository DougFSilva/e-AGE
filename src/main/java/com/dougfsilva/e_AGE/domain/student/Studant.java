package com.dougfsilva.e_AGE.domain.student;

import java.util.List;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.guardian.Guardian;
import com.dougfsilva.e_AGE.domain.person.Person;
import com.dougfsilva.e_AGE.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"enrollment"})
@ToString
public class Studant {
	
	private String ID;
	
	private String enrollment;

	private User user;
	
	private Person person;
	
	private List<Clazz> classes;
	
	private Guardian guardian;
	
	private Enterprise enterprise;

	public Studant(String enrollment, Person person, List<Clazz> classes) {
		this.enrollment = enrollment;
		this.person = person;
		this.classes = classes;
	}
	
}
