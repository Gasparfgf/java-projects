package bame.application.service;

import java.util.Objects;

import bame.application.exception.AccountNotFoundException;
import bame.application.port.in.DepositMoneyUseCase;
import bame.domain.model.Account;
import bame.domain.model.Money;
import bame.domain.repository.AccountRepository;

public class DepositMoneyService implements DepositMoneyUseCase {
	/**
	 * An account repository.
	 * */
    private final AccountRepository accountRepository;

    /**
     * Constructor.
     * @param accountRepository the account repository
     * */
    public DepositMoneyService(final AccountRepository accountRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    @Override
    public void deposit(final String accountNumber, final Money money) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        account.deposit(money);
        accountRepository.save(account);
    }

}
