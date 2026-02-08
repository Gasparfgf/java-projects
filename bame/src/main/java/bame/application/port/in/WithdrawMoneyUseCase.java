package bame.application.port.in;

import bame.domain.model.Money;

public interface WithdrawMoneyUseCase {
    /**
     * Withdraw money from existing account.
     * @param accountNumber the account number where withdraw money.
     * @param money the money to be withdraw.
     * */
	void withdraw(String accountNumber, Money money);
}
