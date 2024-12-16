package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.UpdateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.CourseValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseUpdater {

	private final CourseRepository repository;
	private final TechnologicalAreaRepository technologicalAreaRepository;
	private final CourseValidator validator;
	private final StandardLogger logger;

	public CourseResponse update(UpdateCourseRequest request) {
		try {
			Course course = repository.findByIdOrThrow(request.getID());
			Course updatedCourse = updateCourseData(course, request);
			Course savedCourse = repository.save(updatedCourse);
			logger.info(String.format("Updated Course ID %s, %s", savedCourse.getID(), savedCourse.getTitle()));
			return CourseResponse.fromCourse(savedCourse);
		} catch (ObjectNotFoundException | CourseValidationException e) {
			String message = String.format("Error while updating course %s : %s", request.getTitle(), e.getMessage());
			logger.warn(message, e);
			throw new CourseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating course %s : %s", request.getTitle(), e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}

	}

	private Course updateCourseData(Course course, UpdateCourseRequest request) {
		if (request.getTitle() != null && !request.getTitle().isBlank() && !request.getTitle().equalsIgnoreCase(course.getTitle())) {
			validator.uniqueTitle(request.getTitle());
			course.setTitle(request.getTitle());
		}
		if (request.getTechnologicalAreaID() != null && !request.getTechnologicalAreaID().isBlank()
				&& !request.getTechnologicalAreaID().equalsIgnoreCase(course.getID())) {
			TechnologicalArea technologicalArea = technologicalAreaRepository.findByIdOrThrow(request.getTechnologicalAreaID());
			course.setTechnologicalArea(technologicalArea);
		}
		if (request.getModality() != null) {
			course.setModality(request.getModality());
		}
		return course;
	}
}
