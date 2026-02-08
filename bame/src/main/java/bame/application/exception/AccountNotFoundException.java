package bame.application.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param accountNumber an account number.
     * */
	public AccountNotFoundException(final String accountNumber) {
		super("Account not found with number:" + accountNumber);
	}
}
