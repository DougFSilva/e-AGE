package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

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

	private MultipartFile image;

	LocalDate creationDate;

	public UpdateCourseRequest(String ID, CourseModality modality, String title, String technologicalAreaID, MultipartFile image,
			LocalDate creationDate) {
		if(ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		this.ID = ID;
		this.modality = modality;
		this.title = title;
		this.technologicalAreaID = technologicalAreaID;
		this.image = image;
		this.creationDate = creationDate;
	}
}
