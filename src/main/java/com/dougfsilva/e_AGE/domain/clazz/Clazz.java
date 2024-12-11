package com.dougfsilva.e_AGE.domain.clazz;

import java.time.LocalDate;

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
	private Integer number;
	private String code;
	private Course course;
	private Boolean isClosed;
	private LocalDate creationDate;
    private LocalDate closingDate;

	public Clazz(Integer number, String code, Course course, Boolean isClosed, LocalDate creationDate) {
		this.number = number;
		this.code = code;
		this.course = course;
		this.isClosed = isClosed;
		this.creationDate = creationDate;
	}

}
