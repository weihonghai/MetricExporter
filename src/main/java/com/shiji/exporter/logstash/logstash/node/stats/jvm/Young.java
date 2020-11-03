package com.shiji.exporter.logstash.logstash.node.stats.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Young {
    private Long used_in_bytes;
    private Long max_in_bytes;
    private Long peak_used_in_bytes;
    private Long peak_max_in_bytes;
    private Long committed_in_bytes;
}
