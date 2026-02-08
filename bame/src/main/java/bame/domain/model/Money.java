package bame.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import bame.domain.exception.InvalidAmountException;

/**
 * Designed to guarantee important aspects.
 * Like:
 * <ul>
 * <li>immutability</li>
 * <li>financial security (BigDecimal)</li>
 * <li>no currency inconsistencies</li>
 * <li>ready for APIs / databases / microservices</li>
 * </ul>
 * @author Gaspar Francisco
 * */
public class Money {
	/** Money currency. */
	private Currency currency;
	/** The amount of money. */
	private BigDecimal amount;

    /**
     * Constructor.
     * @param currency the money currency.
     * @param amount the money amount (quantity).
     * */
	public Money(final Currency currency, final BigDecimal amount) {
		this.currency = Objects.requireNonNull(currency, "Currency must not be null");
        this.amount = Objects.requireNonNull(amount, "Amount must not be null");
        if (amount.signum() < 0) {
            throw new InvalidAmountException(amount);
        }
	}

	/** @return money amount. */
	public BigDecimal getAmount() {
		return amount;
	}

	/** @return money currency. */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Add money to an account.
	 * @param other the money to be added.
	 * @return the amount of money available in bank account after addition.
	 * */
    public Money add(final Money other) {
        requireSameCurrency(other);
        return new Money(currency, amount.add(other.amount));
    }

	/**
	 * Subtract money from an account.
	 * @param other the money to be removed.
	 * @return the amount of money available in bank account after subtraction.
	 * */
    public Money subtract(final Money other) {
        requireSameCurrency(other);

        BigDecimal result = amount.subtract(other.amount);
        if (result.signum() < 0) {
            throw new InvalidAmountException("Resulting amount cannot be negative");
        }

        return new Money(currency, result);
    }

    private void requireSameCurrency(final Money other) {
        Objects.requireNonNull(other, "Money must not be null");

        if (currency != other.currency) {
            throw new InvalidAmountException(
                "Currency mismatch: " + currency + " vs " + other.currency
            );
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
        	return true;
        }
        if (!(o instanceof Money)) {
        	return false;
        }
        Money money = (Money) o;
        return currency == money.currency &&
               amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount.stripTrailingZeros());
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
