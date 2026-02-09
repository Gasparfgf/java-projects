package bame.application.service;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import bame.domain.model.User;
import bame.domain.repository.UserRepository;
import bame.infrastructure.persistence.inmemory.InMemoryUserRepository;

class CreateUserServiceTest {

    @Test
    void should_create_and_save_user() {
        UserRepository repository = new InMemoryUserRepository();
        CreateUserService service = new CreateUserService(repository);

        User user = service.createUser("Bob", LocalDate.of(1995, 5, 10));

        assertNotNull(user.getId());
        assertEquals("Bob", user.getFullName());
        assertTrue(repository.findById(user.getId()).isPresent());
    }
}
