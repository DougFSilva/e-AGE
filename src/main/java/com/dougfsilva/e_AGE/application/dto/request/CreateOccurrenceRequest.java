package com.dougfsilva.e_AGE.application.dto.request;

import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceType;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateOccurrenceRequest {

	private String reporterID;
	private String studentID;
	private String clazzID;
	private String curricularUnit;
	private OccurrenceType occurrenceType;
	private Boolean restricted;
	private String description;

	public CreateOccurrenceRequest(String reporterID, String studentID, String clazzID,
			String curricularUnit, OccurrenceType occurrenceType, Boolean restricted,
			String description, Boolean sendEmailNotification, Boolean sendPhoneNotification) {
		if (reporterID == null || reporterID.isBlank()) {
			throw new IllegalArgumentException("Reporter ID cannot be null or empty!");
		}
		if (studentID == null || studentID.isBlank()) {
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
		if (description == null || description.isBlank() ||description.length() < 20) {
			throw new IllegalArgumentException("Description cannot be empty or null!");
		}
		
		this.reporterID = reporterID;
		this.studentID = studentID;
		this.clazzID = clazzID;
		this.curricularUnit = curricularUnit;
		this.occurrenceType = occurrenceType;
		this.restricted = restricted != null ? restricted : false ;
		this.description = description;
	}

}
