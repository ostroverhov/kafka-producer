package com.ostroverhov.kafka.producer.service;

import com.ostroverhov.kafka.producer.enums.EventStatus;
import com.ostroverhov.kafka.producer.model.EventMessage;
import com.ostroverhov.kafka.producer.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class EventsResponseListener {

    private final EventRepository eventRepository;

    @KafkaListener(
            topics = "${app.topics.response}",
            groupId = "${spring.kafka.consumer.group-id:service-a}"
    )
    @Transactional
    public void onResponse(EventMessage message) {
        var eventId = message.eventId();

        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalStateException("Event not found in service-a db: " + eventId));

        if (event.getStatus() == EventStatus.DONE) {
            log.info("Response received for eventId={}, but it is already DONE - skipping", eventId);
            return;
        }

        event.setStatus(EventStatus.DONE);
        eventRepository.save(event);

        log.info("Response received for eventId={}, status updated to DONE", eventId);
    }
}
