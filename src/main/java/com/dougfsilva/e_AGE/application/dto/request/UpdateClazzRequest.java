package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateClazzRequest {

	private String ID;
	private Integer number;
	private String code;
	private String courseID;
	private LocalDate creationDate;

	public UpdateClazzRequest(String ID, Integer number, String code, String courseID, LocalDate creationDate) {
		if (ID == null || ID.isBlank()) {
			throw new IllegalArgumentException("ID cannot be null!");
		}
		if (number <= 0) {
			throw new IllegalArgumentException("Number must be a positive integer!");
		}
		this.number = number;
		this.code = code;
		this.courseID = courseID;
		this.creationDate = creationDate;
	}

}
