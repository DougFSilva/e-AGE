package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateResponsibleRequest extends CreatePersonRequest {

	private String studentID;

	public CreateResponsibleRequest(String name, String sex, String rg, String phone, String email, LocalDate dateOfBirth,
			CreateAddressRequest address, String studentID) {
		super(name, sex, rg, phone, email, dateOfBirth, address);
		if (studentID != null && studentID.isBlank()) {
			throw new IllegalArgumentException("StudentID cannot be null!");
		}
		this.studentID = studentID;
	}

}
