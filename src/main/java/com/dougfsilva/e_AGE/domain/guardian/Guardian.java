package com.dougfsilva.e_AGE.domain.guardian;

import com.dougfsilva.e_AGE.domain.person.Person;
import com.dougfsilva.e_AGE.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Guardian {
	
	private User user;

	private Person name;
	
}
