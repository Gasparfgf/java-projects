package bame.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {
    private String id;
    private String fullName;
    private LocalDate birthDate;
    private List<Account> accounts = new ArrayList<>();

    public User(String fullName, LocalDate birthDate) {
        this.id = UUID.randomUUID().toString();
        this.fullName = Objects.requireNonNull(fullName);
        this.birthDate = Objects.requireNonNull(birthDate);
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        Objects.requireNonNull(account);
        accounts.add(account);
    }
}
