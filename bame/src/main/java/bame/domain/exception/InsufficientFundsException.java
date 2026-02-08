package bame.domain.exception;

import java.math.BigDecimal;

import bame.domain.model.Currency;

public class InsufficientFundsException extends DomainException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param currency the actual currency.
     * @param available the available balance.
     * @param requested the amount requested.
     * */
	public InsufficientFundsException(
            final Currency currency,
            final BigDecimal available,
            final BigDecimal requested
    ) {
        super(String.format(
            "Insufficient funds in %s: available=%s, requested=%s",
            currency, available, requested
        ));
    }

}
