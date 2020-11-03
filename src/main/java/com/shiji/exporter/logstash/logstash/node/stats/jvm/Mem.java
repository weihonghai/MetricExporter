package com.shiji.exporter.logstash.logstash.node.stats.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Mem {
    private Long heap_used_percent;
    private Long heap_committed_in_bytes;
    private Long heap_max_in_bytes;
    private Long heap_used_in_bytes;
    private Long non_heap_used_in_bytes;
    private Long non_heap_committed_in_bytes;
    private Pools pools;
}
