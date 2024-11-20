package com.dougfsilva.e_AGE.domain.course;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CourseModality {

	IN_PERSON("Face-to-face instruction in a physical classroom."),
    BLENDED_LEARNING("Combines online and in-person instruction."),
    ONLINE("Entirely online, virtual instruction.");

	private String description;

	private CourseModality(String description) {
		this.description = description;
	}

}
