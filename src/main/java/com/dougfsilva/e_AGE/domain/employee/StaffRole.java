package com.dougfsilva.e_AGE.domain.employee;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum StaffRole {

	DIRECTOR("Overall strategic leadership and management of the school or a specific department."),
	COORDINATOR("Responsible for coordinating projects, teams, or specific activities within the school."),
	PROFESSOR("Responsible for teaching, research, and mentoring students."),
	QLA("Quality Liaison – Responsible for ensuring quality and continuous improvement of school processes and services."),
	SECRETARY("Provides administrative support, organization, and clerical assistance."),
	LIBRARIAN("Manages the school library and its resources.");
	
	private String description;

	private StaffRole(String description) {
		this.description = description;
	}
}
