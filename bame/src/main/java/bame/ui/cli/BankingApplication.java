package bame.ui.cli;

import bame.application.port.in.CreateUserUseCase;
import bame.application.port.in.DepositMoneyUseCase;
import bame.application.port.in.WithdrawMoneyUseCase;
import bame.application.service.CreateAccountService;
import bame.application.service.CreateUserService;
import bame.application.service.DepositMoneyService;
import bame.application.service.WithdrawMoneyService;
import bame.infrastructure.persistence.inmemory.InMemoryAccountRepository;
import bame.infrastructure.persistence.inmemory.InMemoryUserRepository;

/**
 * Launchs the application.
 * @author Gaspar Francisco
 * */
final class BankingApplication {

	private BankingApplication() {
	}

	public static void main(final String[] args) {
		InMemoryAccountRepository accountRepo = new InMemoryAccountRepository();
		InMemoryUserRepository userRepo = new InMemoryUserRepository();

		CreateUserUseCase userService = new CreateUserService(userRepo);
		CreateAccountService accountService = new CreateAccountService(userRepo, accountRepo);
		DepositMoneyUseCase moneyService = new DepositMoneyService(accountRepo);
		WithdrawMoneyUseCase withdrawService = new WithdrawMoneyService(accountRepo);

		MenuController menu = new MenuController(
                userService,
                accountService,
                moneyService,
                withdrawService
        );

        menu.start();
	}

}
