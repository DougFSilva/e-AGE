package com.dougfsilva.e_AGE.domain.person;

public enum Sex {

	MALE("Male"), FEMALE("Female"), INTERSEX("Intersex"), UNDISCLOSED("Undisclosed");

	private final String description;

	Sex(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static Sex fromLetter(String letter) {
		if (letter == null || letter.isBlank()) {
			throw new IllegalArgumentException("The value cannot be null or empty.");
		}
		switch (letter.trim().toUpperCase()) {
		case "M":
			return MALE;
		case "F":
			return FEMALE;
		case "I":
			return INTERSEX;
		case "U":
			return UNDISCLOSED;
		default:
			throw new IllegalArgumentException("Invalid letter: " + letter);
		}
	}
}
