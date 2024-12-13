package com.dougfsilva.e_AGE.domain.utilities.notification;

import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;

public interface NotificationMessageGenerator {

	String getOccurrenceNotificationMessage(Occurrence occurrence);
}
