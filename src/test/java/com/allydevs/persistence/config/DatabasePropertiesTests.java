package com.allydevs.persistence.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabasePropertiesTests {

  @Autowired private DatabaseProperties databaseProperties;

  @Test
  void loadsDatabaseConfiguration() {
    assertThat(databaseProperties.url())
        .isEqualTo("jdbc:postgresql://localhost:5432/ad_sb_pg_starter");

    assertThat(databaseProperties.username()).isEqualTo("postgres");
  }
}
