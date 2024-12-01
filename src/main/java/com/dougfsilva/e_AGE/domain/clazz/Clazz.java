package com.dougfsilva.e_AGE.domain.clazz;

import com.dougfsilva.e_AGE.domain.course.Course;

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
	
	private String image;

	public Clazz(String code, Course course, String image) {
		this.code = code;
		this.course = course;
		this.image = image;
	}
	
}
