package bame.infrastructure.persistence.inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bame.domain.model.Account;
import bame.domain.repository.AccountRepository;

public class InMemoryAccountRepository implements AccountRepository {
	/** Account storage. */
	private final Map<String, Account> storage = new HashMap<>();
	
	@Override
	public Optional<Account> findByAccountNumber(final String accountNumber) {
		return Optional.ofNullable(storage.get(accountNumber));
	}
	
	@Override
	public void save(final Account account) {
		storage.put(account.getAccountNumber(), account);
	}
}
