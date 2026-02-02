package bame.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void should_create_user_with_name_and_birth_date() {
        LocalDate birthDate = LocalDate.of(1999, 5, 20);

        User user = new User("Yarda Francisco", birthDate);

        assertEquals("Yarda Francisco", user.getFullName());
        assertEquals(birthDate, user.getBirthDate());
        assertNotNull(user.getId());
        assertTrue(user.getAccounts().isEmpty());
    }

    @Test
    void should_generate_unique_user_id() {
        User user1 = new User("User One", LocalDate.of(1990, 1, 1));
        User user2 = new User("User Two", LocalDate.of(1995, 2, 2));

        assertNotEquals(user1.getId(), user2.getId());
    }

    @Test
    void should_add_account_to_user() {
        User user = new User("Yarda Francisco", LocalDate.of(1999, 5, 20));
        Account account = new Account("ACC-001");

        user.addAccount(account);

        List<Account> accounts = user.getAccounts();
        assertEquals(1, accounts.size());
        assertEquals("ACC-001", accounts.get(0).getAccountNumber());
    }

    @Test
    void should_add_multiple_accounts_to_user() {
        User user = new User("Yarda Francisco", LocalDate.of(1999, 5, 20));

        user.addAccount(new Account("ACC-001"));
        user.addAccount(new Account("ACC-002"));

        assertEquals(2, user.getAccounts().size());
    }

    @Test
    void should_not_allow_external_modification_of_accounts() {
        User user = new User("Yarda Francisco", LocalDate.of(1999, 5, 20));
        user.addAccount(new Account("ACC-001"));

        List<Account> accounts = user.getAccounts();

        assertThrows(UnsupportedOperationException.class, () ->
            accounts.clear()
        );
    }

    @Test
    void should_throw_exception_when_adding_null_account() {
        User user = new User("Yarda Francisco", LocalDate.of(1999, 5, 20));

        assertThrows(NullPointerException.class, () ->
            user.addAccount(null)
        );
    }

    @Test
    void should_throw_exception_when_creating_user_with_null_name() {
        assertThrows(NullPointerException.class, () ->
            new User(null, LocalDate.of(2000, 1, 1))
        );
    }

    @Test
    void should_throw_exception_when_creating_user_with_null_birth_date() {
        assertThrows(NullPointerException.class, () ->
            new User("Alice", null)
        );
    }
}
