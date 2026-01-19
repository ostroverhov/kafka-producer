package com.ostroverhov.kafka.producer.service;

import com.ostroverhov.kafka.producer.enums.EventStatus;
import com.ostroverhov.kafka.producer.model.EventEntity;
import com.ostroverhov.kafka.producer.model.EventMessage;
import com.ostroverhov.kafka.producer.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventGeneratorService {

    private final EventRepository repo;
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;

    @Value("${app.topics.request}")
    private String requestTopic;

    @Scheduled(fixedDelayString = "${app.scheduler.fixedDelayMs}")
    @Transactional
    public void generate() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        String payload = "payload-" + id;

        repo.save(new EventEntity(id, EventStatus.NEW, now, payload));

        EventMessage msg = new EventMessage(id, payload, now);
        kafkaTemplate.send(requestTopic, id.toString(), msg);

        log.info("Generated event {}, saved NEW and sent to topic={}", id, requestTopic);
    }
}
