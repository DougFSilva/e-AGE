package com.dougfsilva.e_AGE.domain.responsible;

import com.dougfsilva.e_AGE.domain.person.Email;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class Responsible {
	
	private String name;
	private Email email;
	private String phone;
	
}
