package bame.application.service;

import java.util.Objects;

import bame.domain.model.Account;
import bame.domain.model.User;
import bame.domain.repository.AccountRepository;
import bame.domain.repository.UserRepository;

public class CreateAccountService {
	private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public CreateAccountService(
            UserRepository userRepository,
            AccountRepository accountRepository
    ) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    public Account createAccount(String userId, String accountNumber) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Account account = new Account(accountNumber);
        user.addAccount(account);

        accountRepository.save(account);
        userRepository.save(user);

        return account;
    }
}
