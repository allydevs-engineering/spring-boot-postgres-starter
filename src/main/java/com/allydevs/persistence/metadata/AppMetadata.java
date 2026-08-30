package com.allydevs.persistence.metadata;

import java.time.OffsetDateTime;

public record AppMetadata(
    Long id, String applicationName, String applicationVersion, OffsetDateTime createdAt) {}
