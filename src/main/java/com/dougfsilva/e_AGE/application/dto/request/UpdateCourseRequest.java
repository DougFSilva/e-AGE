package com.dougfsilva.e_AGE.application.dto.request;

import com.dougfsilva.e_AGE.domain.course.CourseModality;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateCourseRequest {

	private String ID;
	private CourseModality modality;
	private String title;
	private String technologicalAreaID;

	public UpdateCourseRequest(String ID, CourseModality modality, String title, String technologicalAreaID) {
		if(ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		this.modality = modality;
		this.title = title;
		this.technologicalAreaID = technologicalAreaID;
	}
}
