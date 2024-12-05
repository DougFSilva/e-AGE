package com.dougfsilva.e_AGE.domain.certificate;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"ID"})
public class Certificate {

	private String ID;
	
	private Student student;
	
	private Course course;
	
	private LocalDate certificationDate;

	public Certificate(Student student, Course course, LocalDate certificationDate) {
		this.student = student;
		this.course = course;
		this.certificationDate = certificationDate;
	}
	
}
