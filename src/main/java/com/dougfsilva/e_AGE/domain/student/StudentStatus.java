package com.dougfsilva.e_AGE.domain.student;

import lombok.Getter;

@Getter
public enum StudentStatus {

	ENROLLED("Currently enrolled in the institution"), 
	EGRESS("Successfully completed the course and graduated"),
	DROP_OUT("Discontinued studies voluntarily"), 
	TRANSFERRED("Moved to another institution"),
	EXPELLED("Removed from the institution due to disciplinary reasons");

	private String description;

	private StudentStatus(String description) {
		this.description = description;
	}
}
