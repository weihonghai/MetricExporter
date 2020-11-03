package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OutputsEvents {
    private Long duration_in_millis;
    private Long out;
    private Long in;
}
