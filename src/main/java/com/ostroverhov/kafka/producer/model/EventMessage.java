package com.ostroverhov.kafka.producer.model;

import java.time.Instant;
import java.util.UUID;

public record EventMessage(
        UUID eventId,
        String payload,
        Instant createdAt
) {
}
