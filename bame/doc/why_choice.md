# Why choice

In this file, i describe the reason for some choices or decisions.

## Description

**Version 1**:

The version one had as main goal to design the foundation of the application without using a technology / framework other than pure Java.

It equally aimed to avoid the classic trap of using technologies without understanding the profession, scattered logic, difficult tests:

Exemple:

```nginx

```

## Architectural

### Hexagonal architecture

The choice of this architecture is due to my desire of creating an independent system. Everything revolves around the core business (accounts, money, banking rules).

> The job itself is independent.
Everything else depends on the job.

This architecture allows me to have the technical details (CLI, DB, API, JSON, files…) external thanks to its organization:

* Domain → the core, independent of anything.
* Application → use cases (orchestrator)
* Adapters / Infrastructure → technical details
* Inputs / Outputs → ports

**Repositories**: are interfaces (outgoing ports). The domain doesn't know if it's a database, an API, or a map.

**application**:

The services:

* call the domain
* apply the use cases
* handle transactions (later)

No heavy business logic here.

**Infrastructure** (technical details): Can be replaced tomorrow by JPA, Mongo and REST API, without touching the domain.

**UI**: will be able to evolve from CLI to REST and frontend.

## Code

### Exceptions

In exceptions classes, probably you'll find :

```java
private static final long serialVersionUID = 1L;
```
 
All exceptions are serializable, even if i don't request it. This is because all Java exceptions inherit from:

```java
Throwable implements Serializable
```
Observation: it's more a problem of IDE (Eclipse in my case) than Java itself.
