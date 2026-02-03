package bame.infrastructure.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bame.domain.model.Account;
import bame.domain.repository.AccountRepository;

public class InMemoryAccountRepository implements AccountRepository {
	private final Map<String, Account> storage = new HashMap<>();
	
	@Override
	public Optional<Account> findByAccountNumber(String accountNumber) {
		return Optional.ofNullable(storage.get(accountNumber));
	}
	
	@Override
	public void save(Account account) {
		storage.put(account.getAccountNumber(), account);
	}
}
