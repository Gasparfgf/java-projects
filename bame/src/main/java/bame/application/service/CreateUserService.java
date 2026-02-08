package bame.application.service;

import java.time.LocalDate;
import java.util.Objects;

import bame.application.port.in.CreateUserUseCase;
import bame.domain.model.User;
import bame.domain.repository.UserRepository;

public class CreateUserService implements CreateUserUseCase {
	/** An user repository. */
    private final UserRepository userRepository;

    /**
     * Constructor.
     * @param userRepository the user repository.
     * */
    public CreateUserService(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public User createUser(final String fullName, final LocalDate birthDate) {
        User user = new User(fullName, birthDate);
        userRepository.save(user);
        return user;
    }
}
