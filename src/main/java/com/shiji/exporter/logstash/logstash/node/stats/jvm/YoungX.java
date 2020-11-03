package com.shiji.exporter.logstash.logstash.node.stats.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class YoungX {
    private Long collection_count;
    private Long collection_time_in_millis;
}
