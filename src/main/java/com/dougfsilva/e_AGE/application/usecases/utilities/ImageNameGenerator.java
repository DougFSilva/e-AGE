package com.dougfsilva.e_AGE.application.usecases.utilities;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

public class ImageNameGenerator {

	public static String byTechnologicalArea(TechnologicalArea area) {
		return ImageType.TECHNOLOGICAL_AREA.name() + "_" + area.getID();
	}

	public static String byCourse(Course course) {
		return ImageType.COURSE.name() + "_" + course.getID();
	}
	
	public static String byEmployee(Employee employee) {
		return ImageType.EMPLOYEE.name() + "_" + employee.getID();
	}

	public static String byStudent(Student student) {
		return ImageType.STUDENT.name() + "_" + student.getID();
	}

}
