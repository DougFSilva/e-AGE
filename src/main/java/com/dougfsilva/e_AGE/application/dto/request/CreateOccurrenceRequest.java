package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceType;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateOccurrenceRequest {

	private LocalDateTime openingDate;
	private String reporterID;
	private String studantID;
	private String clazzID;
	private String curricularUnit;
	private OccurrenceType occurrenceType;
	private Boolean restricted;
	private Boolean forwarding;
	private String description;

	public CreateOccurrenceRequest(LocalDateTime openingDate, String reporterID, String studantID, String clazzID,
			String curricularUnit, OccurrenceType occurrenceType, Boolean restricted, Boolean forwarding,
			String description) {
		if (openingDate == null) {
			throw new IllegalArgumentException("Opening Date cannot be null!");
		}
		if (reporterID == null || reporterID.isBlank()) {
			throw new IllegalArgumentException("Reporter ID cannot be null or empty!");
		}
		if (studantID == null || studantID.isBlank()) {
			throw new IllegalArgumentException("Student ID cannot be null or empty!");
		}
		if (clazzID == null || clazzID.isBlank()) {
			throw new IllegalArgumentException("Class ID cannot be null or empty!");
		}
		if (curricularUnit == null || curricularUnit.isBlank()) {
			throw new IllegalArgumentException("Curricular Unit cannot be null or empty!");
		}
		if (occurrenceType == null) {
			throw new IllegalArgumentException("Occurrence Type cannot be null!");
		}
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Description cannot be null or empty!");
		}
		this.openingDate = openingDate;
		this.reporterID = reporterID;
		this.studantID = studantID;
		this.clazzID = clazzID;
		this.curricularUnit = curricularUnit;
		this.occurrenceType = occurrenceType;
		this.restricted = restricted;
		this.forwarding = forwarding;
		this.description = description;
	}

}
