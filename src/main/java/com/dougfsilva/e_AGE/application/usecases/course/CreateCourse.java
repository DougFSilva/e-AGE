package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCourse {

	private final CourseRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;

	private final StoreImage storeImage;

	private final StandardLogger logger;

	public Course create(CreateCourseRequest request) {
		String imageUrl = storeImage.execute(request.image(), ImageType.COURSE, request.title());
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.technologicalAreaID());
		Course course = new Course(request.modality(), request.title(), technologicalArea, imageUrl);
		Course createdCourse = repository.save(course);
		logger.createdObjectLog(createdCourse);
		return createdCourse;
	}
}
