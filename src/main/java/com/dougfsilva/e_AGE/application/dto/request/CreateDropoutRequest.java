package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateDropoutRequest {

	private String enrollmentID;
	private String reason;
	private LocalDate date;

	public CreateDropoutRequest(String enrollmentID, String reason, LocalDate date) {
		if (enrollmentID == null || enrollmentID.isBlank()) {
			throw new IllegalArgumentException("Enrollment ID cannot be null or empty!");
		}
		if (reason == null || reason.isBlank()) {
			throw new IllegalArgumentException("Reason cannot be null or empty!");
		}
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null!");
		}
		this.enrollmentID = enrollmentID;
		this.reason = reason;
		this.date = date;
	}

}
