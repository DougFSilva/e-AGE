package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCourse {

	private final CourseRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;

	private final StoreImage storeImage;

	private final StandardLogger logger;

	public CourseResponse create(CreateCourseRequest request) {
		repository.findByTitle(request.getTitle().toUpperCase()).ifPresent(c -> {
			throw new DataIntegrityViolationException(String.format("Course with title %S already exists!", request.getTitle()));
		});
		String imageUrl = storeImage.execute(request.getImage(), ImageType.COURSE, request.getTitle());
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.getTechnologicalAreaID());
		Course course = new Course(request.getModality(), request.getTitle(), technologicalArea, imageUrl, false, request.getCreationDate());
		Course createdCourse = repository.save(course);
		logger.info(String.format("Created Course ID %S - %S", createdCourse.getID(), createdCourse.getTitle()));
		return CourseResponse.fromCourse(createdCourse);
	}
}
