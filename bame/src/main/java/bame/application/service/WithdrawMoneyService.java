package bame.application.service;

import java.util.Objects;

import bame.application.port.in.WithdrawMoneyUseCase;
import bame.domain.model.Account;
import bame.domain.model.Money;
import bame.domain.repository.AccountRepository;

public class WithdrawMoneyService implements WithdrawMoneyUseCase {

    private final AccountRepository accountRepository;

    public WithdrawMoneyService(AccountRepository accountRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    @Override
    public void withdraw(String accountNumber, Money money) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.withdraw(money);
        accountRepository.save(account);
    }

}
