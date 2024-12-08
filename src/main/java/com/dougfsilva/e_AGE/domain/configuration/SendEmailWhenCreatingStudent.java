package com.dougfsilva.e_AGE.domain.configuration;

public class SendEmailWhenCreatingStudent implements Configuration {

	@Override
	public String getKey() {
		return "SendEmailWhenCreatingStudent";
	}

	@Override
	public String getValue() {
		return "yes";
	}

}
