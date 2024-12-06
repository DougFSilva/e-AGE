package com.dougfsilva.e_AGE.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ProfileType {

	ADMIN(1, "ROLE_ADMIN"), 
	MANAGEMENT(2, "ROLE_MANAGEMENT"), 
	PROFESSOR(3, "ROLE_PROFESSOR"),
	STUDENT(4, "ROLE_STUDENT"), 
	RESPONSIBLE(5, "ROLE_RESPONSIBLE"), 
	VIEWER(6, "ROLE_VIEWER");

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
		throw new IllegalArgumentException("Invalid Profile!");
	}

	public static ProfileType fromCode(Long code) {
		if(code != null) {
			for (ProfileType x : ProfileType.values()) {
				if (x.getCode() == code) {
					return x;
				}
			}
		}
		throw new IllegalArgumentException("Invalid Profile Code: " + code);
	}

}
