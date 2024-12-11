package com.dougfsilva.e_AGE.domain.dropout;

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
@EqualsAndHashCode
@ToString
public class Dropout {

	private String ID;
	private Student student;
	private Clazz clazz;
	private String reason;
	private LocalDate date;

	public Dropout(Student student, Clazz clazz, String reason, LocalDate date) {
		this.student = student;
		this.clazz = clazz;
		this.reason = reason;
		this.date = date;
	}
	
}
