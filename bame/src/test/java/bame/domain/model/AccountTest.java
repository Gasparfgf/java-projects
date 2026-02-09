package bame.domain.model;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import bame.domain.exception.CurrencyNotFoundException;
import bame.domain.exception.InsufficientFundsException;


public class AccountTest {

    @Test
    void should_create_account_with_account_number() {
        Account account = new Account("ACC-123");

        assertEquals("ACC-123", account.getAccountNumber());
        assertTrue(account.getBalances().isEmpty());
        assertTrue(account.getTransactions().isEmpty());
    }

    @Test
    void should_deposit_money_with_new_currency() {
        Account account = new Account("ACC-123");
        Money money = new Money(Currency.EUR, new BigDecimal("100"));

        account.deposit(money);

        List<Money> balances = account.getBalances();
        assertEquals(1, balances.size());
        assertEquals(new BigDecimal("100"), balances.get(0).getAmount());
        assertEquals(Currency.EUR, balances.get(0).getCurrency());
    }

    @Test
    void should_deposit_money_with_existing_currency() {
        Account account = new Account("ACC-123");

        account.deposit(new Money(Currency.EUR, new BigDecimal("100")));
        account.deposit(new Money(Currency.EUR, new BigDecimal("50")));

        Money balance = account.getBalances().get(0);
        assertEquals(new BigDecimal("150"), balance.getAmount());
    }

    @Test
    void should_withdraw_money_when_balance_is_sufficient() {
        Account account = new Account("ACC-123");
        account.deposit(new Money(Currency.EUR, new BigDecimal("200")));

        account.withdraw(new Money(Currency.EUR, new BigDecimal("80")));

        Money balance = account.getBalances().get(0);
        assertEquals(new BigDecimal("120"), balance.getAmount());
    }

    @Test
    void should_throw_exception_when_withdrawing_more_than_balance() {
        Account account = new Account("ACC-123");
        account.deposit(new Money(Currency.EUR, new BigDecimal("50")));

        assertThrows(InsufficientFundsException.class, () ->
            account.withdraw(new Money(Currency.EUR, new BigDecimal("100")))
        );
    }

    @Test
    void should_throw_exception_when_withdrawing_currency_not_present() {
        Account account = new Account("ACC-123");

        assertThrows(CurrencyNotFoundException.class, () ->
            account.withdraw(new Money(Currency.USD, new BigDecimal("10")))
        );
    }

    @Test
    void should_register_transactions_for_deposit_and_withdrawal() {
        Account account = new Account("ACC-123");

        account.deposit(new Money(Currency.EUR, new BigDecimal("100")));
        account.withdraw(new Money(Currency.EUR, new BigDecimal("40")));

        List<Transaction> transactions = account.getTransactions();

        assertEquals(2, transactions.size());
        assertEquals(Transaction.TransactionType.DEPOSIT, transactions.get(0).getType());
        assertEquals(Transaction.TransactionType.WITHDRAWAL, transactions.get(1).getType());
    }

    @Test
    void should_not_allow_external_modification_of_balances() {
        Account account = new Account("ACC-123");
        account.deposit(new Money(Currency.EUR, new BigDecimal("100")));

        List<Money> balances = account.getBalances();

        assertThrows(UnsupportedOperationException.class, () ->
            balances.add(new Money(Currency.EUR, new BigDecimal("50")))
        );
    }

    @Test
    void should_not_allow_external_modification_of_transactions() {
        Account account = new Account("ACC-123");
        account.deposit(new Money(Currency.EUR, new BigDecimal("100")));

        List<Transaction> transactions = account.getTransactions();

        assertThrows(UnsupportedOperationException.class, () ->
            transactions.clear()
        );
    }
}
