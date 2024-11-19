package com.dougfsilva.e_AGE.domain.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"profileType"})
@ToString
public class Profile {
	
	private ProfileType profileType;

}
