package com.allydevs.persistence.metadata;

import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcAppMetadataRepository implements AppMetadataRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcAppMetadataRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<AppMetadata> findById(Long id) {
    String sql =
        """
                SELECT id,
                       application_name,
                       application_version,
                       created_at
                FROM app_metadata
                WHERE id = ?
                """;

    return jdbcTemplate
        .query(
            sql,
            (resultSet, rowNum) ->
                new AppMetadata(
                    resultSet.getLong("id"),
                    resultSet.getString("application_name"),
                    resultSet.getString("application_version"),
                    resultSet.getObject("created_at", java.time.OffsetDateTime.class)),
            id)
        .stream()
        .findFirst();
  }
}
