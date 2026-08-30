package com.allydevs.persistence.metadata;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@SpringBootTest
class JdbcAppMetadataRepositoryTests {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private AppMetadataRepository repository;

  @BeforeEach
  void cleanDatabase() {
    jdbcTemplate.update("DELETE FROM app_metadata");
  }

  @Test
  void findsPersistedMetadata() {
    OffsetDateTime createdAt = OffsetDateTime.now();

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(
        connection -> {
          var statement =
              connection.prepareStatement(
                  """
                    INSERT INTO app_metadata (
                        application_name,
                        application_version,
                        created_at
                    )
                    VALUES (?, ?, ?)
                    """,
                  new String[] {"id"});

          statement.setString(1, "spring-boot-postgres-starter");
          statement.setString(2, "0.1.0");
          statement.setObject(3, createdAt);

          return statement;
        },
        keyHolder);

    Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

    AppMetadata metadata = repository.findById(id).orElseThrow();

    assertThat(metadata.applicationName()).isEqualTo("spring-boot-postgres-starter");

    assertThat(metadata.applicationVersion()).isEqualTo("0.1.0");

    assertThat(metadata.createdAt()).isNotNull();
  }

  @Test
  void returnsEmptyWhenMetadataDoesNotExist() {
    assertThat(repository.findById(999L)).isEmpty();
  }
}
