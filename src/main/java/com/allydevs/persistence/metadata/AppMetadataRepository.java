package com.allydevs.persistence.metadata;

import java.util.Optional;

public interface AppMetadataRepository {

  Optional<AppMetadata> findById(Long id);
}
