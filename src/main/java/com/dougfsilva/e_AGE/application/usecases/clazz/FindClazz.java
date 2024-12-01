package com.dougfsilva.e_AGE.application.usecases.clazz;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.application.usecases.student.FindStudent;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindClazz {
	
	private final ClazzRepository repository;
	
	private final FindCourse findCourse;
	
	private final FindStudent findStudent;
	
	public Clazz findByID(String ID) {
		return repository.findByID(ID).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Clazz with ID %S not found!", ID)));
	}
	
	public ClazzResponse findByIDAsClazzResponse(String ID) {
		return ClazzResponse.fromClazz(findByID(ID));
	}
	
	public Page<ClazzResponse> findAllByNameContains(String name, PageRequest pageRequest){
		return ClazzResponse.fromPage(repository.findAllByNameContains(name, pageRequest));
	}
	
	public Page<ClazzResponse> findAllByCourse(String courseID, PageRequest pageRequest){
		Course course = findCourse.findByID(courseID);
		return ClazzResponse.fromPage(repository.findAllByCourse(course, pageRequest));
	}
	
	public List<ClazzResponse> findAllByStudent(String studentID){
		Student student = findStudent.findByID(studentID);
		return repository.findAllByStudant(student).stream().map(ClazzResponse::new).collect(Collectors.toList());
	}
	
	public Page<ClazzResponse> findAll(PageRequest pageRequest){
		return ClazzResponse.fromPage(repository.findAll(pageRequest));
	}

}
