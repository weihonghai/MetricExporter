package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeadLetterQueue {
    private Long queue_size_in_bytes;
}
