package com.dougfsilva.e_AGE.domain.configuration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"key"})
@ToString
public class Configuration {

	private String ID;
	private SystemSettingKey key;
	private String value;
	
	public Configuration(SystemSettingKey key, String value) {
		this.key = key;
		this.key.validateValue(value);
		this.value = value;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setKey(SystemSettingKey key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.key.validateValue(value);
		this.value = value;
	}
	
	
}
