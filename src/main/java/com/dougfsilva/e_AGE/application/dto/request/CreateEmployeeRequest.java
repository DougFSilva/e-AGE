package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.employee.StaffRole;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateEmployeeRequest extends CreatePersonRequest {

	private String registration;

	private StaffRole staffRole;
	
	public CreateEmployeeRequest(String name, String sex, String rg, String phone, String email, LocalDate dateOfBirth,
			CreateAddressRequest address, Boolean createDefaultUser, String registration, StaffRole staffRole) {
		super(name, sex, rg, phone, email, dateOfBirth, address, createDefaultUser);

		if (registration == null || registration.isBlank()) {
			throw new IllegalArgumentException("Registration cannot be null or empty!");
		}
		if (staffRole == null) {
			throw new IllegalArgumentException("Staff role cannot be null!");
		}

		this.registration = registration;
		this.staffRole = staffRole;
	}

}
