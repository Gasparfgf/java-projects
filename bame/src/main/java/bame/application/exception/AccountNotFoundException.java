package bame.application.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 479446309722193623L;

	public AccountNotFoundException(String accountNumber) {
		super("Account not found with number:" + accountNumber);
	}
}
