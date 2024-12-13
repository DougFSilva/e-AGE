package com.dougfsilva.e_AGE.domain.utilities.notification;

import com.dougfsilva.e_AGE.domain.person.Email;

public interface EmailNotifiable {

	void notify(Email recipientEmail, String message);
}
