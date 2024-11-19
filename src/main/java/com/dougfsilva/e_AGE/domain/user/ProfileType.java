package com.dougfsilva.e_AGE.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ProfileType {

	ADMIN(1, "ROLE_ADMIN"), PROFESSOR(2, "ROLE_PROFESSOR"), STUDENT(3, "ROLE_STUDENT"), VIEWER(4, "ROLE_VIEWER");
	
	private long code;
	private String description;
	
	public static ProfileType toEnum(String description) {
		if (description == null) {
			return null;
		}
		for (ProfileType x : ProfileType.values()) {
			if (description.equals(x.getDescription())) {
				return x;
			}

		}
		throw new IllegalArgumentException("Perfil inválido");
	}

}
