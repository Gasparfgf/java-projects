package bame.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Designed to guarantee:
 * <ul>
 * <li>immutability</li>
 * <li>financial security (BigDecimal)</li>
 * <li>no currency inconsistencies</li>
 * <li>ready for APIs / databases / microservices</li>
 * </ul>
 * @author Gaspar Francisco
 * */
public class Money {
	private Currency currency;
	private BigDecimal amount;

	public Money(Currency currency, BigDecimal amount) {
		this.currency = Objects.requireNonNull(currency, "Currency must not be null");
        this.amount = Objects.requireNonNull(amount, "Amount must not be null");
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("Amount must be zero or positive");
        }
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		amount.add(amount);
	}

	public Currency getCurrency() {
		return currency;
	}

    public Money add(Money other) {
        requireSameCurrency(other);
        return new Money(currency, amount.add(other.amount));
    }

    public Money subtract(Money other) {
        requireSameCurrency(other);

        BigDecimal result = amount.subtract(other.amount);
        if (result.signum() < 0) {
            throw new IllegalArgumentException("Resulting amount cannot be negative");
        }

        return new Money(currency, result);
    }

    private void requireSameCurrency(Money other) {
        Objects.requireNonNull(other, "Money must not be null");

        if (currency != other.currency) {
            throw new IllegalArgumentException(
                "Currency mismatch: " + currency + " vs " + other.currency
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
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
