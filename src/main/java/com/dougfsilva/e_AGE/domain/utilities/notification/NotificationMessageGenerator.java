package com.dougfsilva.e_AGE.domain.utilities.notification;

import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.person.Person;

public interface NotificationMessageGenerator {

	String getNotificationMessageByOcurrence(Occurrence occurrence);
	
	String getNotificationMessageByUserCreated(Person person);
}
