# Spring Boot PostgreSQL Starter

A production-oriented Spring Boot starter demonstrating a clean PostgreSQL persistence foundation.

## What This Repository Demonstrates

- Spring Boot application structure
- PostgreSQL integration
- Liquibase database migrations
- JDBC-based persistence
- Repository abstraction
- Integration testing with Testcontainers
- GitHub Actions CI
- Code formatting enforcement

## Technology

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- Liquibase
- Spring JDBC
- Testcontainers
- JUnit 5
- AssertJ
- GitHub Actions

## Project Structure

```text
./
├── README.md
├── LICENSE
├── CONTRIBUTING.md
├── SECURITY.md
├── .gitignore
├── .github/
│   └── workflows/
│       └── ci.yml
├── docs/
│   └── testing.md
└── src/
```

## Database

The application uses PostgreSQL.

Database schema changes are managed through Liquibase.

The database schema is not created manually by the application.

## Testing

The repository separates configuration/unit tests from database integration tests.

Integration tests use Testcontainers PostgreSQL.

See: [testing](./docs/testing.md) for the complete testing strategy.

## Local Development

Configure the database connection through the application's datasource configuration.

The local PostgreSQL instance is used for normal application development.

Integration tests use their own PostgreSQL container and do not depend on the local PostgreSQL port.

## Verify Locally

Apply formatting:

```bash
./mvnw spotless:apply
```

Check formatting:

```bash
./mvnw spotless:check
```

Run tests:

```bash
./mvnw test
```

Run the complete verification:

```bash
./mvnw verify
```

Docker must be running when executing integration tests.

## CI

GitHub Actions runs the formatting checks, tests, and Maven verification.

The integration tests start PostgreSQL through Testcontainers.

No separately provisioned PostgreSQL server is required in CI.

## Status

This repository is intentionally being built incrementally.

The current focus is the backend foundation and engineering practices rather than product functionality.

## Contributing

Contributions, improvements and discussions are welcome.
Before opening a pull request:

- Run formatting checks.
- Run linting.
- Run the test suite.
- Ensure the production build succeeds.
- Use a Conventional Commit message.

See [CONTRIBUTING.md](./CONTRIBUTING.md) for development and contribution guidelines.

## Security

Please review [SECURITY.md](./SECURITY.md) for information about reporting security vulnerabilities.

## Maintained by

AllyDevs Engineering

Engineering capability for digital agencies.

Website: [https://allydevs.com](https://allydevs.com)

## License

This project is licensed under the MIT License. See [LICENSE](./LICENSE).
