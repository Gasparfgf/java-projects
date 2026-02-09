package bame.application.service;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import bame.application.exception.UserNotFoundException;
import bame.domain.model.Account;
import bame.domain.model.User;
import bame.domain.repository.AccountRepository;
import bame.domain.repository.UserRepository;
import bame.infrastructure.persistence.inmemory.InMemoryAccountRepository;
import bame.infrastructure.persistence.inmemory.InMemoryUserRepository;

class CreateAccountServiceTest {

    @Test
    void should_create_account_for_existing_user() {
        UserRepository userRepository = new InMemoryUserRepository();
        AccountRepository accountRepository = new InMemoryAccountRepository();

        User user = new User("Charlie", LocalDate.of(1990, 3, 3));
        userRepository.save(user);

        CreateAccountService service =
                new CreateAccountService(userRepository, accountRepository);

        Account account = service.createAccount(user.getId(), "ACC-100");

        assertEquals("ACC-100", account.getAccountNumber());
        assertEquals(1, user.getAccounts().size());
        assertTrue(accountRepository.findByAccountNumber("ACC-100").isPresent());
    }

    @Test
    void should_throw_exception_when_user_not_found() {
        CreateAccountService service =
                new CreateAccountService(
                        new InMemoryUserRepository(),
                        new InMemoryAccountRepository()
                );

        assertThrows(UserNotFoundException.class, () ->
                service.createAccount("unknown-user", "ACC-999")
        );
    }
}
