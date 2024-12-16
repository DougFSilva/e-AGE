package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateEnrollmentRequest {

	private String ID;
	private String registration;
	private LocalDate date;
	
	public UpdateEnrollmentRequest(String ID, String registration, LocalDate date) {
		if (ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		this.registration = registration;
		this.date = date;
	}
}
