package com.dougfsilva.e_AGE.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateTechnologicalAreaRequest {

	private String title;

	private String description;

	public CreateTechnologicalAreaRequest(String title, String description) {
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be null or empty!");
		}
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Description cannot be null or empty!");
		}
		this.title = title;
		this.description = description;
	}

}
