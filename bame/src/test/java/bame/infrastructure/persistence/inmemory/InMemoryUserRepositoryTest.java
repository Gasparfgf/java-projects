package bame.infrastructure.persistence.inmemory;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import bame.domain.model.User;
import bame.domain.repository.UserRepository;

class InMemoryUserRepositoryTest {

    @Test
    void should_save_and_find_user_by_id() {
        UserRepository repository = new InMemoryUserRepository();
        User user = new User("Alice", LocalDate.of(2000, 1, 1));

        repository.save(user);

        Optional<User> result = repository.findById(user.getId());

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getFullName());
    }

    @Test
    void should_return_empty_when_user_not_found() {
        UserRepository repository = new InMemoryUserRepository();

        Optional<User> result = repository.findById("unknown-id");

        assertTrue(result.isEmpty());
    }
}
