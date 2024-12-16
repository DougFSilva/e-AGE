package com.dougfsilva.e_AGE.application.dto.request;

import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceType;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateOccurrenceRequest {

	private String ID;
	private String curricularUnit;
	private OccurrenceType occurrenceType;
	private Boolean restricted;
	private String description;
	private String resolutionDescription;

	public UpdateOccurrenceRequest(String ID, String curricularUnit, OccurrenceType occurrenceType, Boolean restricted, String description, String resolutionDescription) {
		if (ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		this.curricularUnit = curricularUnit;
		this.occurrenceType = occurrenceType;
		this.restricted = restricted;
		this.description = description;
		this.resolutionDescription = resolutionDescription;
	}

}
