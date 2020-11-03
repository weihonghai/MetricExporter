package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Reloads {
    private String last_error;
    private Long successes;
    private String last_success_timestamp;
    private Long failures;
    private String last_failure_timestamp;
}
