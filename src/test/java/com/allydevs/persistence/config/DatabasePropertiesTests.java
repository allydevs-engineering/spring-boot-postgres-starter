package com.allydevs.persistence.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class DatabasePropertiesTests {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withUserConfiguration(TestConfiguration.class)
          .withPropertyValues(
              "spring.datasource.url=jdbc:postgresql://localhost:5432/ad_sb_pg_starter",
              "spring.datasource.username=postgres",
              "spring.datasource.password=postgres");

  @Test
  void loadsDatabaseConfiguration() {
    contextRunner.run(
        context -> {
          DatabaseProperties databaseProperties = context.getBean(DatabaseProperties.class);

          assertThat(databaseProperties.url())
              .isEqualTo("jdbc:postgresql://localhost:5432/ad_sb_pg_starter");

          assertThat(databaseProperties.username()).isEqualTo("postgres");
        });
  }

  @EnableConfigurationProperties(DatabaseProperties.class)
  static class TestConfiguration {}
}
