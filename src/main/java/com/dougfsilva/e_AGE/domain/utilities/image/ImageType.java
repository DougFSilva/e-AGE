package com.dougfsilva.e_AGE.domain.utilities.image;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ImageType {

	 TECHNOLOGICAL_AREA("Technological Area"),
	 COURSE("Course"),
	 STUDENT("Student"),
	 EMPLOYEE("Employee"),
	 GUARDIAN("Guardian");
	
	private String description;
	
	private ImageType(String description) {
		this.description = description;
	}
}
