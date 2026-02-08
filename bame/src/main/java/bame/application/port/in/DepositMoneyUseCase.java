package bame.application.port.in;

import bame.domain.model.Money;

public interface DepositMoneyUseCase {
	/**
	 * Deposit money into an account.
	 * @param accountNumber the number of the account where to deposit money.
	 * @param money the money to deposit into an account.
	 * */
	void deposit(String accountNumber, Money money);
}
