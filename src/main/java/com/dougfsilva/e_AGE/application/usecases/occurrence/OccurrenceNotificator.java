package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.SendNotificationException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.notification.EmailNotifiable;
import com.dougfsilva.e_AGE.domain.utilities.notification.NotificationMessageGenerator;
import com.dougfsilva.e_AGE.domain.utilities.notification.PhoneNotifiable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceNotificator {

	private final NotificationMessageGenerator notificationMessageGenerator;
	private final EmailNotifiable emailNotifiable;
	private final PhoneNotifiable phoneNotifiable;
	private final StandardLogger logger;

	public void notifyByEmail(Occurrence occurrence) {
		try {
			Student student = occurrence.getStudent();
			emailNotifiable.notify(student.getEmail(), messageBuilder(occurrence));
			if (student.calculateAge() < 18) {
				emailNotifiable.notify(student.getResponsible().getEmail(), messageBuilder(occurrence));
			}
		} catch (Exception e) {
			String message = String.format("Unexpected error when sending occurrence email to student %s : %s",
					occurrence.getStudent().getName(), e.getMessage());
			logger.error(message, e);
			throw new SendNotificationException(message, e);
		}

	}

	public void notifyByPhone(Occurrence occurrence) {
		try {
			Student student = occurrence.getStudent();
			phoneNotifiable.notify(student.getPhone(), messageBuilder(occurrence));
			if (student.calculateAge() < 18) {
				phoneNotifiable.notify(student.getResponsible().getPhone(), messageBuilder(occurrence));
			}
		} catch (Exception e) {
			String message = String.format(
					"Unexpected error when sending occurrence phone notification to student %s : %s",
					occurrence.getStudent().getName(), e.getMessage());
			logger.error(message, e);
			throw new SendNotificationException(message, e);
		}
	}

	private String messageBuilder(Occurrence occurrence) {
		return notificationMessageGenerator.getNotificationMessageByOcurrence(occurrence);
	}
	

}
