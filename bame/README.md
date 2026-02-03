# BAME

## Version 0.0.1

In this project, i design a **bank account management engine** in order test my capacity in designing a core business independent of any technology.

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

## Code coverage

To find the JaCoCo report :
```bash
target/site/jacoco/index.html
```

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

## Packages

application : the application package orchestrate the logic.
infrastructure: excellent to use Spring/JPA later.

