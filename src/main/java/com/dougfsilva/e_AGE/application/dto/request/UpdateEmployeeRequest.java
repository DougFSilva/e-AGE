package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.employee.StaffRole;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateEmployeeRequest extends UpdatePersonRequest {

	private String registration;
	private StaffRole staffRole;
	private Boolean active;

	public UpdateEmployeeRequest(String ID, String name, String sex, String rg, String phone, String email,
			LocalDate dateOfBirth, UpdateAddressRequest address, String registration, StaffRole staffRole, Boolean active) {
		super(ID, name, sex, rg, phone, email, dateOfBirth, address);
		this.registration = registration;
		this.staffRole = staffRole;
		this.active = active;
	}
	
}
