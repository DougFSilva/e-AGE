package com.dougfsilva.e_AGE.application.dto.response;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"ID"})
public class ClazzResponse {

	private String ID;

	private String code;
	
	private CourseResponse course;
	
	private String image;
	
	public ClazzResponse(Clazz clazz) {
		this.ID = clazz.getID();
		this.code = clazz.getCode();
		this.course = CourseResponse.fromCourse(clazz.getCourse());
		this.image = clazz.getImage();
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
