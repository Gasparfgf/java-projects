package bame.domain.exception;

import bame.domain.model.Currency;

public class CurrencyNotFoundException  extends DomainException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param currency the actual currency.
     * */
	public CurrencyNotFoundException(final Currency currency) {
        super("Currency not found in account: " + currency);
    }
}
