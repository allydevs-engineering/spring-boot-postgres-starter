## Testing Strategy

The project separates unit/configuration tests from database integration tests.

```text
Unit / configuration tests
        |
        +-- no database
        |
        v
Integration tests
        |
        +-- Testcontainers PostgreSQL
        |
        v
Liquibase migrations
        |
        v
Application / repository
```

## Test Categories

### Unit Tests

Unit tests do not start Spring Boot or PostgreSQL.

Example:

```text
AppMetadataRepositoryTests
```

These tests verify simple Java/domain behavior.

### Configuration Tests

Configuration property tests verify property binding without creating a database connection.

Example:

```text
DatabasePropertiesTests
```

The test uses `ApplicationContextRunner` and supplies the properties required for the test.

### Integration Tests

Integration tests use a real PostgreSQL instance managed by Testcontainers.

Examples:

```text
SpringBootPostgresStarterApplicationTests
JdbcAppMetadataRepositoryTests
```

These tests use:

```java
@Import(TestcontainersConfiguration.class)
```

The imported configuration provides the PostgreSQL container as a Spring bean.

### Testcontainers

The PostgreSQL container is defined in:

```text
src/test/java/com/allydevs/persistence/TestcontainersConfiguration.java
```

The container is configured as a Spring bean:

```java
@Bean
@ServiceConnection
PostgreSQLContainer postgresContainer()
```

`@ServiceConnection` allows Spring Boot to obtain the connection details directly from the container.

This means the integration tests do not depend on the developer's local PostgreSQL port.

## Local Development PostgreSQL

The application may still use a locally running PostgreSQL instance during normal development.

For example:

```text
localhost:<mapped-port>
```

This is separate from Testcontainers.

The important distinction is:

```text
Application development
    -> local PostgreSQL

Integration tests
    -> Testcontainers PostgreSQL
```

## Running Tests

Start Docker Engine before running integration tests.

Run all tests:

```bash
./mvnw test
```

Run the complete Maven verification:

```bash
./mvnw verify
```

Run formatting verification:

```bash
./mvnw spotless:check
```

Apply formatting:

```bash
./mvnw spotless:apply
```

## CI

GitHub Actions runs:

```text
Spotless
   |
Tests
   |
Maven verify
   |
Testcontainers PostgreSQL
```

CI does not require a separately provisioned PostgreSQL service.

The integration tests create and manage their own PostgreSQL container through Testcontainers.

## Important

Docker must be available when running integration tests.

If Docker is stopped, tests that use Testcontainers will fail.

This is expected behavior.

The tests that do not use Testcontainers should remain independent of Docker.
