package com.dougfsilva.e_AGE.domain.enrollment;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"ID","student", "clazz"})
@ToString
public class Enrollment {

	private String ID;
	private String registration;
	private Student student;
	private Clazz clazz;
	private LocalDate date;
	private EnrollmentStatus status;

	public Enrollment(String registration, Student student, Clazz clazz, LocalDate date, EnrollmentStatus status) {
		this.registration = registration;
		this.student = student;
		this.clazz = clazz;
		this.date = date;
		this.status = status;
	}

}
