package bame.domain.exception;

import java.math.BigDecimal;

public class InvalidAmountException extends DomainException {

    private static final long serialVersionUID = 7111553458874127277L;

	public InvalidAmountException(BigDecimal amount) {
        super("Invalid amount: " + amount + "\nAmount must be zero or positive!");
    }

    public InvalidAmountException(String message) {
        super(message);
    }

}
