package com.shiji.exporter.logstash.logstash.node.stats.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Events {

    private Long in;
    private Long filtered;
    private Long out;
    private Long duration_in_millis;
    private Long queue_push_duration_in_millis;
}
