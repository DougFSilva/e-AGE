package com.dougfsilva.e_AGE.domain.course;

import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Course {

	private String ID;

	private CourseModality modality;

	private String title;

	private TechnologicalArea technologicalArea;
	
	private String image;

	public Course(CourseModality modality, String title, TechnologicalArea technologicalArea, String image) {
		this.modality = modality;
		this.title = title;
		this.technologicalArea = technologicalArea;
		this.image = image;
	}
	
	public Course(CourseModality modality, String title, TechnologicalArea technologicalArea) {
		this.modality = modality;
		this.title = title;
		this.technologicalArea = technologicalArea;
	}

}
