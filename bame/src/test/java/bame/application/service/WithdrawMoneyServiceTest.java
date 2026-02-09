package bame.application.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import bame.domain.model.Account;
import bame.domain.model.Currency;
import bame.domain.model.Money;
import bame.domain.repository.AccountRepository;
import bame.infrastructure.persistence.inmemory.InMemoryAccountRepository;

class WithdrawMoneyServiceTest {

    @Test
    void should_withdraw_money_from_existing_account() {
        AccountRepository repository = new InMemoryAccountRepository();
        WithdrawMoneyService service = new WithdrawMoneyService(repository);

        Account account = new Account("ACC-300");
        account.deposit(new Money(Currency.EUR, new BigDecimal("200")));
        repository.save(account);

        service.withdraw("ACC-300", new Money(Currency.EUR, new BigDecimal("50")));

        Money balance = repository.findByAccountNumber("ACC-300")
                .orElseThrow()
                .getBalances()
                .get(0);

        assertEquals(new BigDecimal("150"), balance.getAmount());
    }

    @Test
    void should_throw_exception_when_account_not_found() {
        WithdrawMoneyService service =
                new WithdrawMoneyService(new InMemoryAccountRepository());

        assertThrows(IllegalArgumentException.class, () ->
                service.withdraw("UNKNOWN", new Money(Currency.EUR, BigDecimal.TEN))
        );
    }
}
