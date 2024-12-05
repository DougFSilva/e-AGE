package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnrollRequest {

	private String ID;
	
	private String registration;
	
	private String studentID;
	
	private String clazzID;
	
	private LocalDate date;
}
