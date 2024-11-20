package com.dougfsilva.e_AGE.domain.occurrence;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.student.Studant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"code"})
@ToString
public class Occurrence {

	private String code;
	
	private LocalDateTime openingDate;
	
	private LocalDateTime closingDate;
	
	private Employee Reporter;
	
	private Studant studant;
	
	private Clazz clazz;
	
	private String curricularUnit;
	
	private OccurrenceType occurrenceType;
	
	private Boolean restricted;
	
	private Boolean forwarding;
	
	private String description;
	
	private String resolutionDescription;
	
	private Boolean open;
	
	private String studantSignature;
	
	private Employee closureResponsible;
	

}
