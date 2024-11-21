package com.dougfsilva.e_AGE.domain.pagination;

import lombok.Getter;

@Getter
public enum Direction {

	ASC("Ascending direction"), DESC("Descending direction");
	
	private String description;
	
	private Direction(String description) {
		this.description = description;
	}
}
