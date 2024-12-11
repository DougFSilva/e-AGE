package com.dougfsilva.e_AGE.domain.course;

import java.time.LocalDate;

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
	private Boolean isClosed;
	private LocalDate creationDate;
    private LocalDate closingDate;
	private String image;

	public Course(CourseModality modality, String title, TechnologicalArea technologicalArea, Boolean isClosed, LocalDate creationDate, String image) {
		this.modality = modality;
		this.title = title;
		this.technologicalArea = technologicalArea;
		this.isClosed = isClosed;
		this.creationDate = creationDate;
		this.image = image;
	}
	
}
