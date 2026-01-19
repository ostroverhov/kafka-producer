package com.ostroverhov.kafka.producer.service;

import com.ostroverhov.kafka.producer.enums.EventStatus;
import com.ostroverhov.kafka.producer.model.EventEntity;
import com.ostroverhov.kafka.producer.model.EventStatistic;
import com.ostroverhov.kafka.producer.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventDataService {

    private final EventRepository eventRepository;

    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public EventStatistic getStatistic() {
        Long amountEventsDone = eventRepository.countByStatus(EventStatus.DONE);
        Long amountEventsNew = eventRepository.countByStatus(EventStatus.NEW);
        return EventStatistic.builder()
                .total(amountEventsDone + amountEventsNew)
                .doneEvents(amountEventsDone)
                .newEvents(amountEventsNew)
                .build();
    }
}
