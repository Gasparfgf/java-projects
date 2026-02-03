package bame.application.port.in;

import bame.domain.model.Money;

public interface WithdrawMoneyUseCase {
	void withdraw(String accountNumber, Money money);
}
