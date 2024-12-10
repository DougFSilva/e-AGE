package com.dougfsilva.e_AGE.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateTechnologicalAreaRequest {
	
	private String ID;
	private String title;
	private String description;
	private MultipartFile image;

	public UpdateTechnologicalAreaRequest(String ID, String title, String description, MultipartFile image) {
		if(ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be null or empty!");
		}
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Description cannot be null or empty!");
		}
		this.ID =ID;
		this.title = title;
		this.description = description;
		this.image = image;
	}
}
