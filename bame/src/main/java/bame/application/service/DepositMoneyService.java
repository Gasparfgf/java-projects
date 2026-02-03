package bame.application.service;

import java.util.Objects;

import bame.application.port.in.DepositMoneyUseCase;
import bame.domain.model.Account;
import bame.domain.model.Money;
import bame.domain.repository.AccountRepository;

public class DepositMoneyService implements DepositMoneyUseCase {

    private final AccountRepository accountRepository;

    public DepositMoneyService(AccountRepository accountRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    @Override
    public void deposit(String accountNumber, Money money) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.deposit(money);
        accountRepository.save(account);
    }

}
