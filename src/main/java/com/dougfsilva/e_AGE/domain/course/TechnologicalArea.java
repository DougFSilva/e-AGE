package com.dougfsilva.e_AGE.domain.course;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"title"})
@ToString
public class TechnologicalArea {

	private String title;
	
	private String Description;
}
