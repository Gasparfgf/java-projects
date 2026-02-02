package bame.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private static final String ACCOUNT_PREFIX = "BAME";
    private final Map<Currency, Money> balances = new HashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();


	public Account(String accountNumber) {
        this.accountNumber = Objects.requireNonNull(accountNumber, "Account number must not be null");
	}

    public String getAccountNumber() {
		return accountNumber;
	}

    public List<Money> getBalances() {
		return new ArrayList<>(balances.values());
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
        Objects.requireNonNull(money);

        Money current = balances.get(money.getCurrency());
        if (current == null) {
            throw new IllegalArgumentException("Currency not found on account");
        }

        Money updated = current.subtract(money);
        balances.put(money.getCurrency(), updated);

        transactions.add(new Transaction(Transaction.TransactionType.WITHDRAWAL, money));
    }
}
