package com.dougfsilva.e_AGE.domain.utilities;

import com.dougfsilva.e_AGE.domain.person.Person;

public interface Notifiable {

	 void sendNotification(Person recipient, String message);
}
