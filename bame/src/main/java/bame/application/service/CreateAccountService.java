package bame.application.service;

import java.util.Objects;

import bame.application.exception.UserNotFoundException;
import bame.domain.model.Account;
import bame.domain.model.User;
import bame.domain.repository.AccountRepository;
import bame.domain.repository.UserRepository;

public final class CreateAccountService {
	/** An user repository. */
	private final UserRepository userRepository;
	/** An account repository. */
    private final AccountRepository accountRepository;

    /**
     * Constructor.
     * @param userRepository the user repository
     * @param accountRepository the account repository
     * */
    public CreateAccountService(
    		final UserRepository userRepository,
    		final AccountRepository accountRepository
    ) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    /**
     * Create an account.
     * @param userId the user id.
     * @param accountNumber the account number
     * @return the created account.
     * */
    public Account createAccount(final String userId, final String accountNumber) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Account account = new Account(accountNumber);
        user.addAccount(account);

        accountRepository.save(account);
        userRepository.save(user);

        return account;
    }
}
