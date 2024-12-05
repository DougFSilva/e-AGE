package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.Getter;

@Getter
public class CourseResponse {

	private String ID;

	private CourseModality modality;

	private String title;

	private String technologicalAreaTitle;

	private String image;
	
	private Boolean isClosed;
	
	private LocalDate creationDate;

    private LocalDate closingDate;

	public CourseResponse(Course course) {
		this.ID = course.getID();
		this.modality = course.getModality();
		this.title = course.getTitle();
		this.technologicalAreaTitle = course.getTechnologicalArea().getTitle();
		this.image = course.getImage();
		this.isClosed = course.getIsClosed();
		this.creationDate = course.getCreationDate();
		this.closingDate = course.getClosingDate();
	}

	public static Page<CourseResponse> fromPage(Page<Course> courses) {
		return new Page<CourseResponse>(
				courses.getContent()
				.stream()
				.map(CourseResponse::new)
				.collect(Collectors.toList()), 
				courses.getNumber(), 
				courses.getSize(),
				courses.getTotalElements(),
				courses.getTotalPages(), 
				courses.getHasContent(), 
				courses.getIsFirst(),
				courses.getIsLast());
	}
	
	 public static CourseResponse fromCourse(Course course) {
	        return new CourseResponse(course);
	    }

}
