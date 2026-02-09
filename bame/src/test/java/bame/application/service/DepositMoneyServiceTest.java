package bame.application.service;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import bame.application.exception.AccountNotFoundException;
import bame.domain.model.Account;
import bame.domain.model.Currency;
import bame.domain.model.Money;
import bame.domain.repository.AccountRepository;
import bame.infrastructure.persistence.inmemory.InMemoryAccountRepository;

class DepositMoneyServiceTest {

    @Test
    void should_deposit_money_into_existing_account() {
        AccountRepository repository = new InMemoryAccountRepository();
        DepositMoneyService service = new DepositMoneyService(repository);

        Account account = new Account("ACC-200");
        repository.save(account);

        service.deposit("ACC-200", new Money(Currency.EUR, new BigDecimal("100")));

        Money balance = repository.findByAccountNumber("ACC-200")
                .orElseThrow()
                .getBalances()
                .get(0);

        assertEquals(new BigDecimal("100"), balance.getAmount());
    }

    @Test
    void should_throw_exception_when_account_not_found() {
        DepositMoneyService service =
                new DepositMoneyService(new InMemoryAccountRepository());

        assertThrows(AccountNotFoundException.class, () ->
                service.deposit("UNKNOWN", new Money(Currency.EUR, BigDecimal.TEN))
        );
    }
}
