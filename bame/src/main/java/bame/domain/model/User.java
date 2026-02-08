package bame.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {
	/** User id. */
    private String id;
	/** User full name. */
    private String fullName;
	/** User birth date. */
    private LocalDate birthDate;
	/** List of user bank accounts. */
    private List<Account> accounts = new ArrayList<>();

    /**
     * Constructor.
     * @param fullName user full name.
     * @param birthDate user birth date.
     * */
    public User(final String fullName, final LocalDate birthDate) {
        this.id = UUID.randomUUID().toString();
        this.fullName = Objects.requireNonNull(fullName);
        this.birthDate = Objects.requireNonNull(birthDate);
    }

    /** @return user id. */
    public String getId() {
        return id;
    }

    /** @return user full name. */
    public String getFullName() {
        return fullName;
    }

    /** @return user birth date. */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /** @return list of user bank accounts. */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Add an user account.
     * @param account the bank account to be assigned to user.
     * */
    public void addAccount(final Account account) {
        Objects.requireNonNull(account);
        accounts.add(account);
    }
}
