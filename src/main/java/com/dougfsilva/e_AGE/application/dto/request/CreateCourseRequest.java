package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.domain.course.CourseModality;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateCourseRequest {

	private CourseModality modality;

	private String title;

	private String technologicalAreaID;

	private MultipartFile image;

	LocalDate creationDate;

	public CreateCourseRequest(CourseModality modality, String title, String technologicalAreaID, MultipartFile image,
			LocalDate creationDate) {
		if (modality == null) {
			throw new IllegalArgumentException("Modality cannot be null!");
		}
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be null or empty!");
		}
		if (technologicalAreaID == null || technologicalAreaID.isBlank()) {
			throw new IllegalArgumentException("Technological Area ID cannot be null or empty!");
		}
		if (image == null || image.isEmpty()) {
			throw new IllegalArgumentException("Image file cannot be null or empty!");
		}
		if (creationDate == null) {
			throw new IllegalArgumentException("Creation date cannot be null!");
		}
		this.modality = modality;
		this.title = title;
		this.technologicalAreaID = technologicalAreaID;
		this.image = image;
		this.creationDate = creationDate;
	}

}
