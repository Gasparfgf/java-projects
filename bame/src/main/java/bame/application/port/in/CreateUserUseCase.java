package bame.application.port.in;

import java.time.LocalDate;

import bame.domain.model.User;

public interface CreateUserUseCase {
	/**
	 * Creates an user.
	 * @param fullName the user full name.
	 * @param birthDate the user birth date.
	 * */
	User createUser(String fullName, LocalDate birthDate);
}
