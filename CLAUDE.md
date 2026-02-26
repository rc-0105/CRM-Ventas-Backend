# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
./mvnw spring-boot:run          # Run the application
./mvnw clean install            # Build and package
./mvnw clean test               # Run all tests
./mvnw test -Dtest=ClassName    # Run a single test class
./mvnw compile                  # Compile only
```

Requires MySQL running locally on port 3306 with database `crmventas` and credentials `root`/`123456` (see `application.properties`).

## Architecture Overview

This is a Spring Boot 3.5.9 / Java 17 CRM (Customer Relationship Management) backend. The project is early-stage — security and auth entities are well-defined, but CRM domain entities are skeletal (only ID fields) and repositories/services don't yet exist.

### Package Structure

```
com.rcb004.crm.crm_ventas_backend/
├── config/           # Spring Security configuration
├── controller/       # REST controllers (only a test auth controller exists)
├── model/            # CRM domain entities (Customer, Lead, Opportunity, Order, Product, EmailLog)
└── persistence/
    ├── entity/       # Auth/identity entities (User, Role, Permission)
    └── repository/   # JPA repositories (currently empty — not yet implemented)
```

### Two Distinct Entity Packages

There are two separate places for entities with different purposes:

- **`model/`** — CRM business entities. Currently stub classes with only an `id` field each. These use `String` typed IDs (inconsistency to be aware of: `UserEntity` uses `Long`).
- **`persistence/entity/`** — Authentication entities (`UserEntity`, `RoleEntity`, `PermissionEntity`, `RoleEnum`). Fully implemented with many-to-many relationships and eager loading.

### Security (Current State)

`SecurityConfig.java` is configured with:
- Stateless sessions, CSRF disabled
- HTTP Basic Auth with `InMemoryUserDetailsManager` (hardcoded users, no DB integration yet)
- `NoOpPasswordEncoder` (plaintext passwords — dev only)
- Only two endpoints exposed: `GET /auth/hello` (public) and `GET /auth/hello-secured` (requires `READ` authority); all others are denied

### Database

- MySQL via Spring Data JPA
- `spring.jpa.hibernate.ddl-auto=create-drop` — schema is recreated on every restart (dev mode)
- `spring.jpa.show-sql=true` — all SQL logged to console

### Key Dependencies

- Spring Boot Web, Security, Data JPA, Mail
- MySQL Connector
- Lombok (used heavily for `@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`)
- Spring Boot DevTools (hot reload)
