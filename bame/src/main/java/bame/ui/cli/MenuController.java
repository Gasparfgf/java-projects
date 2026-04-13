package bame.ui.cli;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import bame.application.port.in.CreateUserUseCase;
import bame.application.port.in.DepositMoneyUseCase;
import bame.application.port.in.WithdrawMoneyUseCase;
import bame.application.service.CreateAccountService;
import bame.domain.model.Currency;
import bame.domain.model.Money;
import bame.domain.model.User;

/**
 * Manages user interaction, call use cases and show the results.
 * @author Gaspar Francisco
 * */
public final class MenuController {
	/**
	 * User manager operation.
	 * */
	private final CreateUserUseCase createUserUseCase;
	/**
	 * Account manager operation.
	 * */
    private final CreateAccountService createAccountService;
	/**
	 * Deposit manager operation.
	 * */
    private final DepositMoneyUseCase depositMoneyUseCase;
	/**
	 * Withdraw manager operation.
	 * */
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;
	/**
	 * CLI.
	 * */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor.
     * @param userUseCase user manager operation.
     * @param accountService account manager operation.
     * @param depositMoney deposit manager operation.
     * @param withdrawMoney withdraw manager operation.
     * */
    public MenuController(
            final CreateUserUseCase userUseCase,
            final CreateAccountService accountService,
            final DepositMoneyUseCase depositMoney,
            final WithdrawMoneyUseCase withdrawMoney
    ) {
        createUserUseCase = userUseCase;
        createAccountService = accountService;
        depositMoneyUseCase = depositMoney;
        withdrawMoneyUseCase = withdrawMoney;
    }

    /**
     * Starts menu.
     * */
    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> createUser();
                    case 2 -> createAccount();
                    case 3 -> deposit();
                    case 4 -> withdraw();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println("\n=== Banking System ===");
        System.out.println("1. Create User");
        System.out.println("2. Create Account");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private void createUser() {
        System.out.print("Full name: ");
        String name = scanner.nextLine();

        System.out.print("Birth date (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        User user = createUserUseCase.createUser(name, birthDate);

        System.out.println("User created with ID: " + user.getId());
    }

    private void createAccount() {
        System.out.print("User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();

        var account = createAccountService.createAccount(userId, accountNumber);

        System.out.println("Account created: " + account.getAccountNumber());
    }

    private void deposit() {
        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();

        Money money = readMoney();

        depositMoneyUseCase.deposit(accountNumber, money);

        System.out.println("Deposit successful");
    }

    private void withdraw() {
        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();

        Money money = readMoney();

        withdrawMoneyUseCase.withdraw(accountNumber, money);

        System.out.println("Withdrawal successful");
    }

    private Money readMoney() {
        System.out.print("Currency (EUR/USD/GBP): ");
        Currency currency = Currency.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Amount: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        return new Money(currency, amount);
    }
}
