package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Queue {
    private String type;
    private Long events_count;
    private Long queue_size_in_bytes;
    private Long max_queue_size_in_bytes;
}
