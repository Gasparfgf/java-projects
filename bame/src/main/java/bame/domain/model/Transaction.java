package bame.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final TransactionType type;
    private final Money money;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, Money money) {
        this.id = UUID.randomUUID().toString();
        this.type = Objects.requireNonNull(type);
        this.money = Objects.requireNonNull(money);
        this.timestamp = LocalDateTime.now();
    }

	public enum TransactionType {
		 DEPOSIT, WITHDRAWAL;
	}


    public String getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Money getMoney() {
        return money;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
