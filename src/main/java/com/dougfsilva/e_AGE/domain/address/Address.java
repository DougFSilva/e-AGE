package com.dougfsilva.e_AGE.domain.address;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "country", "state", "postalCode", "city", "district" })
@ToString
public class Address {

	private String ID;

	private String country;

	private String state;

	private String postalCode;

	private String city;

	private String district;

	private String street;

	private String number;

	public Address(String country, String state, String postalCode, String city, String district, String street,
			String number) {
		this.country = country;
		this.state = state;
		this.postalCode = postalCode;
		this.city = city;
		this.district = district;
		this.street = street;
	}

}
