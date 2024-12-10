package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

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
	LocalDate creationDate;

	public UpdateCourseRequest(String ID, CourseModality modality, String title, String technologicalAreaID, LocalDate creationDate) {
		if(ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		this.modality = modality;
		this.title = title;
		this.technologicalAreaID = technologicalAreaID;
		this.creationDate = creationDate;
	}
}
