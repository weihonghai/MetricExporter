package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Encode {
    private Long duration_in_millis;
    private Long writes_in;
}
