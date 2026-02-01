package bame.domain.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import bame.domain.model.Currency;
import bame.domain.model.Money;

public class MoneyTest {

	@Test
	void should_create_money_with_valid_amount_and_currency() {
	    Money money = new Money(Currency.EUR, new BigDecimal("100.00"));

	    Assert.assertEquals(Currency.EUR, money.getCurrency());
	    Assert.assertEquals(new BigDecimal("100.00"), money.getAmount());
	}

	@Test
	void should_throw_exception_when_currency_is_null() {
		Assert.assertThrows(NullPointerException.class, () ->
	        new Money(null, new BigDecimal("10"))
	    );
	}

	@Test
	void should_throw_exception_when_amount_is_null() {
		Assert.assertThrows(NullPointerException.class, () ->
	        new Money(Currency.EUR, null)
	    );
	}

	@Test
	void should_throw_exception_when_amount_is_negative() {
		Assert.assertThrows(IllegalArgumentException.class, () ->
	        new Money(Currency.EUR, new BigDecimal("-1"))
	    );
	}

	@Test
	void should_allow_zero_amount() {
	    Money money = new Money(Currency.EUR, BigDecimal.ZERO);

	    Assert.assertEquals(BigDecimal.ZERO, money.getAmount());
	}

	@Test
	void should_add_money_with_same_currency() {
	    Money m1 = new Money(Currency.EUR, new BigDecimal("50"));
	    Money m2 = new Money(Currency.EUR, new BigDecimal("25"));

	    Money result = m1.add(m2);

	    Assert.assertEquals(new BigDecimal("75"), result.getAmount());
	    Assert.assertEquals(Currency.EUR, result.getCurrency());
	}

	@Test
	void should_throw_exception_when_adding_different_currencies() {
	    Money eur = new Money(Currency.EUR, new BigDecimal("50"));
	    Money usd = new Money(Currency.USD, new BigDecimal("10"));

	    Assert.assertThrows(IllegalArgumentException.class, () ->
	        eur.add(usd)
	    );
	}

	@Test
	void add_should_not_mutate_original_money() {
	    Money original = new Money(Currency.EUR, new BigDecimal("100"));
	    Money added = original.add(new Money(Currency.EUR, new BigDecimal("50")));

	    Assert.assertEquals(new BigDecimal("100"), original.getAmount());
	    Assert.assertEquals(new BigDecimal("150"), added.getAmount());
	}

}
