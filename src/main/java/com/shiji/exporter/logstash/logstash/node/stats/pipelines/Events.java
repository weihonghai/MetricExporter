package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Events {
    private Long duration_in_millis;
    private Long out;
    private Long queue_push_duration_in_millis;
    private Long filtered;
    private Long in;
}
