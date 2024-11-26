package com.dougfsilva.e_AGE.domain.technologicalArea;

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
	
	private String ID;

	private String title;
	
	private String Description;

	public TechnologicalArea(String title, String description) {
		this.title = title;
		Description = description;
	}
	
	
}
