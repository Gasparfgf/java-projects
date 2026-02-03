package bame.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import bame.domain.exception.CurrencyNotFoundException;
import bame.domain.exception.InsufficientFundsException;

/**
 * <ul>
 * <li>Controls all business rules</li>
 * <li>Cannot have a negative balance</li>
 * <li>Automatic history</li>
 * <li>No scattered logic</li>
 * </ul>
 * */
public class Account {
    private final String accountNumber;
    private final Map<Currency, Money> balances = new HashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();


	public Account(String accountNumber) {
        this.accountNumber = Objects.requireNonNull(accountNumber, "Account number must not be null");
	}

    public String getAccountNumber() {
		return accountNumber;
	}

    public List<Money> getBalances() {
        return Collections.unmodifiableList(
            new ArrayList<>(balances.values())
        );
    }

    public List<Transaction> getTransactions() {
    	return transactions;
    }

    public void deposit(Money money) {
        Objects.requireNonNull(money, "Money object must not be null.");

        balances.merge(
            money.getCurrency(),
            money,
            Money::add
        );

        transactions.add(new Transaction(Transaction.TransactionType.DEPOSIT, money));
    }

    public void withdraw(Money money) {
        Objects.requireNonNull(money, "Money must not be null");

        Money current = balances.get(money.getCurrency());
        if (current == null) {
            throw new CurrencyNotFoundException(money.getCurrency());
        }


        if (current.getAmount().compareTo(money.getAmount()) < 0) {
            throw new InsufficientFundsException(
                money.getCurrency(),
                current.getAmount(),
                money.getAmount()
            );
        }

        Money updated = current.subtract(money);
        balances.put(money.getCurrency(), updated);

        transactions.add(new Transaction(Transaction.TransactionType.WITHDRAWAL, money));
    }
}
