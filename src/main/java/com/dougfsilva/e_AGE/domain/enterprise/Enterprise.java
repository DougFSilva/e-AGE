package com.dougfsilva.e_AGE.domain.enterprise;

import com.dougfsilva.e_AGE.domain.address.Address;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"TIN"})
public class Enterprise {
	
	private String ID;

	private String TIN;
	
	private String name;
	
	private Address address;

	public Enterprise(String tIN, String name, Address address) {
		TIN = tIN;
		this.name = name;
		this.address = address;
	}
	
	
}
