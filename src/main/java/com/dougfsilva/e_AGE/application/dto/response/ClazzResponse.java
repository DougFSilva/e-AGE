package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"ID"})
public class ClazzResponse {

	private String ID;
	
	private Integer number;

	private String code;
	
	private CourseResponse course;
	
	private Boolean isClosed;

	private LocalDate creationDate;

    private LocalDate closingDate;
	
	public ClazzResponse(Clazz clazz) {
		this.ID = clazz.getID();
		this.number = clazz.getNumber();
		this.code = clazz.getCode();
		this.course = CourseResponse.fromCourse(clazz.getCourse());
		this.isClosed = course.getIsClosed();
		this.creationDate = course.getCreationDate();
		this.closingDate = course.getClosingDate();
	}
	
	public static Page<ClazzResponse> fromPage(Page<Clazz> clazzes) {
		return new Page<ClazzResponse>(
				clazzes.getContent()
				.stream()
				.map(ClazzResponse::new)
				.collect(Collectors.toList()), 
				clazzes.getNumber(), 
				clazzes.getSize(),
				clazzes.getTotalElements(),
				clazzes.getTotalPages(), 
				clazzes.getHasContent(), 
				clazzes.getIsFirst(),
				clazzes.getIsLast());
	}
	
	public static ClazzResponse fromClazz(Clazz clazz) {
		return new ClazzResponse(clazz);
	}
}
