package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InputsEvents {
    private Long out;
    private Long queue_push_duration_in_millis;
}
