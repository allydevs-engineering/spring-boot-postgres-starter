package com.allydevs.persistence.metadata;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

class AppMetadataRepositoryTests {

  @Test
  void appMetadataCanBeCreated() {
    OffsetDateTime createdAt = OffsetDateTime.now();

    AppMetadata metadata = new AppMetadata(1L, "spring-boot-postgres-starter", "0.1.0", createdAt);

    assertThat(metadata.id()).isEqualTo(1L);
    assertThat(metadata.applicationName()).isEqualTo("spring-boot-postgres-starter");
    assertThat(metadata.applicationVersion()).isEqualTo("0.1.0");
    assertThat(metadata.createdAt()).isEqualTo(createdAt);
  }
}
