package bame.domain.repository;

import java.util.Optional;

import bame.domain.model.Account;

public interface AccountRepository {
    Optional<Account> findByAccountNumber(String accountNumber);
    void save(Account account);
}
