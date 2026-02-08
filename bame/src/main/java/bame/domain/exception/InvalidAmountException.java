package bame.domain.exception;

import java.math.BigDecimal;

public class InvalidAmountException extends DomainException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param amount the given amount.
     * */
	public InvalidAmountException(final BigDecimal amount) {
        super("Invalid amount: " + amount + "\nAmount must be zero or positive!");
    }

    /**
     * Constructor.
     * @param message the error message.
     * */
    public InvalidAmountException(final String message) {
        super(message);
    }

}
