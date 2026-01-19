package com.ostroverhov.kafka.producer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventStatistic {

    private Long total;
    private Long newEvents;
    private Long doneEvents;
}
