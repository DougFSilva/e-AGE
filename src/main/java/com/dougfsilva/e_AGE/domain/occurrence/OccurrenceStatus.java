package com.dougfsilva.e_AGE.domain.occurrence;

import lombok.Getter;

@Getter
public enum OccurrenceStatus {

	OPEN("The occurrence has been opened"),
	CLOSED("The occurrence has been treated and closed"),
    SIGNED("The occurrence has been signed by the student"), 
    FINISHED("The occurrence has been finished");

	private String description;

	private OccurrenceStatus(String description) {
		this.description = description;
	}
}
