package bame.application.port.in;

import bame.domain.model.Money;

public interface DepositMoneyUseCase {
	void deposit(String accountNumber, Money money);
}
