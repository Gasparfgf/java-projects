package bame.domain.exception;

import java.math.BigDecimal;

import bame.domain.model.Currency;

public class InsufficientFundsException extends DomainException {

    private static final long serialVersionUID = 3345705135552813685L;

	public InsufficientFundsException(
            Currency currency,
            BigDecimal available,
            BigDecimal requested
    ) {
        super(String.format(
            "Insufficient funds in %s: available=%s, requested=%s",
            currency, available, requested
        ));
    }

}
