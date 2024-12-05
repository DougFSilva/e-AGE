package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateResponsibleRequest extends UpdatePersonRequest {

	public UpdateResponsibleRequest(String ID, String name, String sex,  String rg, String phone, String email,
			LocalDate dateOfBirth, UpdateAddressRequest address) {
		super(ID, name, sex, rg, phone, email, dateOfBirth, address);
	}

}
