package com.dougfsilva.e_AGE.domain.person;

import org.springframework.dao.DataIntegrityViolationException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = {"address"})
@ToString
public class Email {

	private String address;
	
	public Email(String address) {
		if (address == null || !address.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {

			throw new DataIntegrityViolationException(String.format("Email %s não é valido!", address));
		}
		this.address = address;
	}
}
