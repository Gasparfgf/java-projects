package bame.application.service;

import java.time.LocalDate;
import java.util.Objects;

import bame.application.port.in.CreateUserUseCase;
import bame.domain.model.User;
import bame.domain.repository.UserRepository;

public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public User createUser(String fullName, LocalDate birthDate) {
        User user = new User(fullName, birthDate);
        userRepository.save(user);
        return user;
    }
}
