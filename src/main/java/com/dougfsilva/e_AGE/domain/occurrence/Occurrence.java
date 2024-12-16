package com.dougfsilva.e_AGE.domain.occurrence;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"ID"})
@ToString
public class Occurrence {

	private String ID;
	private LocalDateTime openingDate;
	private LocalDateTime closingDate;
	private Employee reporter;
	private Student student;
	private Clazz clazz;
	private String curricularUnit;
	private OccurrenceType occurrenceType;
	private Boolean restricted;
	private Boolean forwarding;
	private String description;
	private String resolutionDescription;
	private Boolean open;
	private String studentSignature;
	private Employee closureResponsible;

	public Occurrence(LocalDateTime openingDate, Employee reporter, Student student, Clazz clazz, String curricularUnit,
			OccurrenceType occurrenceType, Boolean restricted, Boolean forwarding, String description) {
		this.openingDate = openingDate;
		this.reporter = reporter;
		this.student = student;
		this.clazz = clazz;
		this.curricularUnit = curricularUnit;
		this.occurrenceType = occurrenceType;
		this.restricted = restricted;
		this.forwarding = forwarding;
		this.description = description;

	}

}
