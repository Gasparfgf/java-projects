package bame.domain.repository;

import java.util.Optional;

import bame.domain.model.Account;

public interface AccountRepository {
	/**
	 * Find an account by account number.
	 * @param accountNumber the number of the account to be found.
	 * @return The account if it exists, else null.
	 * */
    Optional<Account> findByAccountNumber(String accountNumber);

    /**
     * Save an account.
     * @param account The account to be saved.
     * */
    void save(Account account);
}
