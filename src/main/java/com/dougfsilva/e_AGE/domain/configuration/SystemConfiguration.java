package com.dougfsilva.e_AGE.domain.configuration;

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
public class SystemConfiguration {

	private String ID;
	private String key;
	private String value;
}
