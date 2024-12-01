package com.dougfsilva.e_AGE.application.dto.response;

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

	public CourseResponse(Course course) {
		this.ID = course.getID();
		this.modality = course.getModality();
		this.title = course.getTitle();
		this.technologicalAreaTitle = course.getTechnologicalArea().getTitle();
		this.image = course.getImage();
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
