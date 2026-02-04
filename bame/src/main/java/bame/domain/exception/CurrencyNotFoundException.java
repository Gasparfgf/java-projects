package bame.domain.exception;

import bame.domain.model.Currency;

public class CurrencyNotFoundException  extends DomainException {

    private static final long serialVersionUID = -7056339919187061912L;

	public CurrencyNotFoundException(Currency currency) {
        super("Currency not found in account: " + currency);
    }
}
