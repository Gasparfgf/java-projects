package bame.application.port.in;

import java.time.LocalDate;

import bame.domain.model.User;

public interface CreateUserUseCase {
	User createUser(String fullName, LocalDate birthDate);
}
