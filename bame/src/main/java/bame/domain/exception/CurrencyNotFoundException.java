package bame.domain.exception;

import bame.domain.model.Currency;

public class CurrencyNotFoundException  extends DomainException {

    public CurrencyNotFoundException(Currency currency) {
        super("Currency not found in account: " + currency);
    }
}
