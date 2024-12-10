package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.domain.responsible.Responsible;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateStudentRequest extends CreatePersonRequest {

	private Responsible responsible;
	private String enterpriseID;
	
	public CreateStudentRequest(String name, String sex, String rg, String phone, String email, LocalDate dateOfBirth,
			CreateAddressRequest address, Boolean createdDefaultUser, Responsible responsible, String enterpriseID) {
		super(name, sex, rg, phone, email, dateOfBirth, address, createdDefaultUser);
		this.responsible = responsible;
		this.enterpriseID = enterpriseID;
	}

}
