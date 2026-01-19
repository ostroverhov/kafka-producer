package com.ostroverhov.kafka.producer.controller;

import com.ostroverhov.kafka.producer.model.EventEntity;
import com.ostroverhov.kafka.producer.model.EventStatistic;
import com.ostroverhov.kafka.producer.service.EventDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/processed-events")
@AllArgsConstructor
public class ProcessedEventController {

    private final EventDataService eventDataService;

    @GetMapping
    public List<EventEntity> all() {
        return eventDataService.getAllEvents();
    }

    @GetMapping("/statistic")
    public EventStatistic statistic() {
        return eventDataService.getStatistic();
    }
}
