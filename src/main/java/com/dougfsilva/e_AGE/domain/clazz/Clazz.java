package com.dougfsilva.e_AGE.domain.clazz;

import java.util.List;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.student.Studant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Clazz {
	
	private String ID;

	private String code;
	
	private Course course;
	
	private String name;
	
	private List<Studant> studants;
	
	private String image;

	public Clazz(String code, Course course, String name) {
		this.code = code;
		this.course = course;
		this.name = name;
	}
	
}
