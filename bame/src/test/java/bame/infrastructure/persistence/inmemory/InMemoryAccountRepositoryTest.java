package bame.infrastructure.persistence.inmemory;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import bame.domain.model.Account;
import bame.domain.repository.AccountRepository;

class InMemoryAccountRepositoryTest {

    @Test
    void should_save_and_find_account_by_number() {
        AccountRepository repository = new InMemoryAccountRepository();
        Account account = new Account("ACC-001");

        repository.save(account);

        Optional<Account> result = repository.findByAccountNumber("ACC-001");

        assertTrue(result.isPresent());
        assertEquals("ACC-001", result.get().getAccountNumber());
    }

    @Test
    void should_return_empty_when_account_not_found() {
        AccountRepository repository = new InMemoryAccountRepository();

        Optional<Account> result = repository.findByAccountNumber("UNKNOWN");

        assertTrue(result.isEmpty());
    }
}
