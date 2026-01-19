package com.ostroverhov.kafka.producer.model;

import com.ostroverhov.kafka.producer.enums.EventStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventEntity {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private String payload;

}
