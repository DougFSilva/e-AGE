package com.dougfsilva.e_AGE.domain.configuration;

import java.util.Arrays;
import java.util.List;

public enum SystemSettingKey {
	
	SYSTEM_EMAIL_USERNAME(null),
	SYSTEM_EMAIL_PASSWORD(null),
    SEND_EMAIL_TO_EMPLOYEE_WHEN_CREATING_USER(List.of("YES", "NO")),  
    SEND_EMAIL_TO_STUDENT_WHEN_CREATING_USER(List.of("YES", "NO")),
	SEND_EMAIL_WHEN_CREATING_OCCURRENCE(List.of("YES", "NO")),
	SEND_EMAIL_WHEN_DELETING_OCCURRENCE(List.of("YES", "NO")),
	SEND_EMAIL_WHEN_UPDATING_OCCURRENCE(List.of("YES", "NO")),
	SEND_EMAIL_WHEN_UPDATING_TREATMENT_OCCURRENCE(List.of("YES", "NO")),
	SEND_EMAIL_WHEN_CLOSING_OCCURRENCE(List.of("YES", "NO")),
	SEND_EMAIL_WHEN_SIGNING_OCCURRENCE(List.of("YES", "NO")),
	SEND_EMAIL_WHEN_FINISHING_OCCURRENCE(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_TO_EMPLOYEE_WHEN_CREATING_USER(List.of("YES", "NO")),  
    SEND_PHONE_MESSAGE_TO_STUDENT_WHEN_CREATING_USER(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_WHEN_CREATING_OCCURRENCE(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_WHEN_DELETING_OCCURRENCE(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_WHEN_UPDATING_OCCURRENCE(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_WHEN_UPDATING_TREATMENT_OCCURRENCE(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_WHEN_CLOSING_OCCURRENCE(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_WHEN_SIGNING_OCCURRENCE(List.of("YES", "NO")),
	SEND_PHONE_MESSAGE_WHEN_FINISHING_OCCURRENCE(List.of("YES", "NO"));
	

    private final List<String> allowedValues;

    SystemSettingKey(List<String> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }

    public static SystemSettingKey fromKeyName(String keyName) {
        return Arrays.stream(values())
                .filter(key -> key.name().equalsIgnoreCase(keyName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid configuration key: " + keyName));
    }
    
    public void validateValue(String value) {
        if (allowedValues != null && !allowedValues.contains(value)) {
        	throw new IllegalArgumentException("Invalid configuration value: " + value);
        }
    }
}