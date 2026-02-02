package bame.domain.exception;

import java.math.BigDecimal;

import bame.domain.model.Currency;

public class InsufficientFundsException extends DomainException {

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
