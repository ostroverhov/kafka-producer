package com.ostroverhov.kafka.producer.repository;

import com.ostroverhov.kafka.producer.enums.EventStatus;
import com.ostroverhov.kafka.producer.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    long countByStatus(EventStatus status);
}
