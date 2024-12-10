package com.dougfsilva.e_AGE.application.usecases.user;

import java.util.Arrays;
import java.util.List;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.person.Person;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.user.Password;
import com.dougfsilva.e_AGE.domain.user.PasswordEncoder;
import com.dougfsilva.e_AGE.domain.user.Profile;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreator {

	private final UserRepository repository;
	private final PasswordEncoder encoder;
	private final StandardLogger logger;

	public User create(Person person) {
		try {
			if (person.getUser() != null) {
				throw new UserOperationException(String.format("%s already has a user", person.getName()));
			}
			String username = person.getRg();
			Password password = new Password("Ps" + person.getRg() + "@", encoder);
			List<Profile> profiles = getProfile(person);
			User user = new User(username, password, profiles, false);
			logger.info(String.format("User created for Person: %s, Username: %s", person.getName(), user.getUsername()));
			return repository.save(user);
		} catch (Exception e) {
			logger.error("Unexpected error when creating user: " + e.getMessage());
			throw new UserOperationException("Error while create user", e);
		}
	}

	private List<Profile> getProfile(Person person) {
		return switch (person) {
		case Employee employee -> Arrays.asList(new Profile(ProfileType.EMPLOYEE));
		case Student student -> Arrays.asList(new Profile(ProfileType.STUDENT));
		default -> throw new IllegalArgumentException("Unexpected value: " + person);
		};
	}
}
