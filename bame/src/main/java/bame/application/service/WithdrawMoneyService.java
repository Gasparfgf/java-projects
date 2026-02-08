package bame.application.service;

import java.util.Objects;

import bame.application.port.in.WithdrawMoneyUseCase;
import bame.domain.model.Account;
import bame.domain.model.Money;
import bame.domain.repository.AccountRepository;

public class WithdrawMoneyService implements WithdrawMoneyUseCase {
	/** An account repository. */
    private final AccountRepository accountRepository;

    /**
     * Constructor.
     * @param accountRepository the account repository
     * */
    public WithdrawMoneyService(final AccountRepository accountRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    @Override
    public void withdraw(final String accountNumber, final Money money) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.withdraw(money);
        accountRepository.save(account);
    }

}
