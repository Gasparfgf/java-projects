# BAME

In this project, i design a **bank account management engine** in order test my capacity in designing a core business independent of any technology.

---

## Table of contents

* [Context](#Context)
* [Project structure](#Project structure)
* [Code coverage](#Code coverage)
* [Tech stack](#Tech stack)
* [Useful commands](#Useful commands)


## Context

In this first version of the project i worked on a:

* Robust business modeling
* Fintech rules (money, invariants)
* Strong unit tests
* Hexagonal architecture

Why was this approach helpful for me ?

I found it helpful to avoid the common pitfalls of:

* choosing technologies directly without understanding the business
* having a scattered approach
* writing difficult tests

Scalability will be the validator of my project. This project has to be able to evolve towards:

* Backend framework
* Database
* API
* Frontend

But without breaking what is already done.

## Project structure

```text
banking-engine/
│
├── domain/
│   ├── model/
│   │   ├── Account.java
│   │   ├── Money.java
│   │   ├── User.java
│   │   ├── Transaction.java
│   │   ├── Currency.java
│   │   └── TransactionType.java
│   │
│   ├── exception/
│   │   ├── InsufficientFundsException.java
│   │   ├── CurrencyNotFoundException.java
│   │   └── InvalidAmountException.java
│   │
│   └── repository/
│       ├── AccountRepository.java
│       └── UserRepository.java
│
├── application/
│   ├── service/
│   │   ├── CreateUserService.java
│   │   ├── CreateAccountService.java
│   │   ├── DepositMoneyService.java
│   │   └── WithdrawMoneyService.java
│   │
│   └── port/
│       └── in/
│           ├── CreateUserUseCase.java
│           ├── DepositMoneyUseCase.java
│           └── WithdrawMoneyUseCase.java
│
├── infrastructure/
│   └── persistence/
│       └── inmemory/
│           ├── InMemoryAccountRepository.java
│           └── InMemoryUserRepository.java
│
├── ui/
│   └── cli/
│       ├── BankingApplication.java
│       └── MenuController.java
│
└── test/
    ├── domain/
    └── application/
```

## Code coverage

To find the JaCoCo report :

```bash
target/site/jacoco/index.html
```


## Tech stack

* Java 17+
* Maven

## Useful commands

In one command you can launch the :

* compilation
* tests
* coverage
* checkstyle
* PMD
* CPD

by using :

```bash
mvn clean verify
```

```bash
# Launch the application
mvn exec:java
```
