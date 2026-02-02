package bame.domain.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MoneyTest {

	@Test
	void should_create_money_with_valid_amount_and_currency() {
	    Money money = new Money(Currency.EUR, new BigDecimal("100.00"));

	    assertEquals(Currency.EUR, money.getCurrency());
	    assertEquals(new BigDecimal("100.00"), money.getAmount());
	}

	@Test
	void should_throw_exception_when_currency_is_null() {
		assertThrows(NullPointerException.class, () ->
	        new Money(null, new BigDecimal("10"))
	    );
	}

	@Test
	void should_throw_exception_when_amount_is_null() {
		assertThrows(NullPointerException.class, () ->
	        new Money(Currency.EUR, null)
	    );
	}

	@Test
	void should_throw_exception_when_amount_is_negative() {
		assertThrows(IllegalArgumentException.class, () ->
	        new Money(Currency.EUR, new BigDecimal("-1"))
	    );
	}

	@Test
	void should_allow_zero_amount() {
	    Money money = new Money(Currency.EUR, BigDecimal.ZERO);

	    assertEquals(BigDecimal.ZERO, money.getAmount());
	}

	@Test
	void should_add_money_with_same_currency() {
	    Money m1 = new Money(Currency.EUR, new BigDecimal("50"));
	    Money m2 = new Money(Currency.EUR, new BigDecimal("25"));

	    Money result = m1.add(m2);

	    assertEquals(new BigDecimal("75"), result.getAmount());
	    assertEquals(Currency.EUR, result.getCurrency());
	}

	@Test
	void should_throw_exception_when_adding_different_currencies() {
	    Money eur = new Money(Currency.EUR, new BigDecimal("50"));
	    Money usd = new Money(Currency.USD, new BigDecimal("10"));

	    assertThrows(IllegalArgumentException.class, () ->
	        eur.add(usd)
	    );
	}

	@Test
	void add_should_not_mutate_original_money() {
	    Money original = new Money(Currency.EUR, new BigDecimal("100"));
	    Money added = original.add(new Money(Currency.EUR, new BigDecimal("50")));

	    assertEquals(new BigDecimal("100"), original.getAmount());
	    assertEquals(new BigDecimal("150"), added.getAmount());
	}

}
