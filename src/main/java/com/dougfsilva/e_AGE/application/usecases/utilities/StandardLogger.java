package com.dougfsilva.e_AGE.application.usecases.utilities;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.dto.response.UserResponse;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.Logger;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

public class StandardLogger {
	
	private final UserResponse user;
	
	private final Logger logger;
	
	public StandardLogger(UserContext userContext, Logger logger) {
		this.user = UserResponse.fromUser(userContext.getCurrentUser());
		this.logger = logger;
	}

	public void createdObjectLog(Object object) {
		logger.info(String.format("%S created by %S", object, user));
	}

	public void deletedObjectLog(Object object) {
		logger.info(String.format("%S deleted by %S", object, user));
	}

	public void updatedObjectLog(Object object) {
		logger.info(String.format("%S updated by %S", object, user));
	}
	
	public void imageStoreErrorLog(MultipartFile image) {
		logger.warn(String.format("Failed to load image %S by user %S",image.getName(), user));
	}
	
	public void imageDeleteErrorLog(String imageUrl) {
		logger.warn(String.format("Failed to delete image %S by user %S", imageUrl, user));
	}
	
	public void closingLog(Object object) {
		logger.info(String.format("%S closed by %S", object, user));
	}
	
	public void reopenLog(Object object) {
		logger.info(String.format("%S reopen by %S", object, user));
	}
	
	public void enrollLog(Student student, Clazz clazz) {
		logger.info(String.format("Student %S enroll in class %S by %S", StudentResponse.fromStudent(student), ClazzResponse.fromClazz(clazz), user));
	}
	
	public void disenrollLog(Student student, Clazz clazz) {
		logger.info(String.format("Student %S disenroll in class %S by %S", StudentResponse.fromStudent(student), ClazzResponse.fromClazz(clazz), user));
	}

}
