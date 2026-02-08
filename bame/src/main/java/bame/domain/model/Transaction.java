package bame.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
	/**
	 * Transaction id.
	 * */
    private final String id;
	/**
	 * Transaction type.
	 * */
    private final TransactionType type;
	/**
	 * Money involved in the transaction.
	 * */
    private final Money money;
	/**
	 * Date and hour of the transaction.
	 * */
    private final LocalDateTime timestamp;

    /**
     * Constructor.
     * @param type the transaction type.
     * @param money the money involved in the transaction.
     * */
    public Transaction(final TransactionType type, final Money money) {
        this.id = UUID.randomUUID().toString();
        this.type = Objects.requireNonNull(type);
        this.money = Objects.requireNonNull(money);
        this.timestamp = LocalDateTime.now();
    }

    /**
     * The available transactions type.
     * */
	public enum TransactionType {
		 DEPOSIT, WITHDRAWAL;
	}

	/**
	 * @return id.
	 * */
    public String getId() {
        return id;
    }

    /**
     * @return transaction type.
     * */
    public TransactionType getType() {
        return type;
    }

    /**
     * @return money.
     * */
    public Money getMoney() {
        return money;
    }

    /**
     * @return date and hour of a transaction.
     * */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
