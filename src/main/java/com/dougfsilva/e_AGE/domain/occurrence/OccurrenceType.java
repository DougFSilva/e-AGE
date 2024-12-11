package com.dougfsilva.e_AGE.domain.occurrence;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OccurrenceType {

	CONDUCT("Misconduct or inappropriate behavior"),
    ABSENCE("Unjustified absence from activities or classes"),
    TARDINESS("Late arrival for classes or activities"),
    PERFORMANCE("Poor academic or activity performance"),
    POSITIVE_OCCURENCE("Recognition of good behavior or performance"),
    OTHER("Uncategorized occurrence");
	
	private String description;
	
	private OccurrenceType (String description) {
		this.description = description;
	}
}
