package com.dougfsilva.e_AGE.application.dto.request;

import com.dougfsilva.e_AGE.domain.course.CourseModality;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateCourseRequest {

	private CourseModality modality;
	private String title;
	private String technologicalAreaID;

	public CreateCourseRequest(CourseModality modality, String title, String technologicalAreaID) {
		if (modality == null) {
			throw new IllegalArgumentException("Modality cannot be null!");
		}
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be null or empty!");
		}
		if (technologicalAreaID == null || technologicalAreaID.isBlank()) {
			throw new IllegalArgumentException("Technological Area ID cannot be null or empty!");
		}
		this.modality = modality;
		this.title = title;
		this.technologicalAreaID = technologicalAreaID;
	}

}
