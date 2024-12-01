package com.dougfsilva.e_AGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dougfsilva.e_AGE.application.usecases.course.CreateCourse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

@SpringBootApplication
public class EAgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAgeApplication.class, args);
		CourseRepository courseRepository = new TestCourseRepository();
		Logger logger= new TestLogger();
		AuthChecker authChecker = new AuthChecker(new TestUserContext(), new TestLogger());
		
		Course savedCourse = courseRepository.save(new Course(CourseModality.ONLINE, "Título do curso", new TechnologicalArea("Elétrica", ""),""));
		//deleteAddress.delete("A123");
		//updateAddress.update("A123", "Peru", "Cuzco", "12345-678", "chile_city", "chile_district", "chile_street");
	}

}
