package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateStudentRequest extends CreatePersonRequest {

	private String enrollment;

	private String enterpriseID;

	public CreateStudentRequest(String name, String sex, String rg, String phone, String email, LocalDate dateOfBirth,
			CreateAddressRequest address, String enrollment, String enterpriseID) {
		super(name, sex, rg, phone, email, dateOfBirth, address);

		if (enrollment == null || enrollment.isBlank()) {
			throw new IllegalArgumentException("Enrollment cannot be null or empty!");
		}
		this.enrollment = enrollment;
		this.enterpriseID = enterpriseID;
	}

}
