package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnrollRequest {

	private String registration;
	private String studentID;
	private String clazzID;
	private LocalDate date;

	public EnrollRequest(String registration, String studentID, String clazzID, LocalDate date) {
		if (registration == null || registration.isBlank()) {
			throw new IllegalArgumentException("Registration cannot be null or empty!");
		}
		if (studentID == null || studentID.isBlank()) {
			throw new IllegalArgumentException("Student ID cannot be null or empty!");
		}
		if (clazzID == null || clazzID.isBlank()) {
			throw new IllegalArgumentException("Class ID cannot be null or empty!");
		}
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null!");
		}

		this.registration = registration;
		this.studentID = studentID;
		this.clazzID = clazzID;
		this.date = date;
	}
}
