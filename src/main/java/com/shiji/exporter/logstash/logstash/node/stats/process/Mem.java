package com.shiji.exporter.logstash.logstash.node.stats.process;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Mem {
    private Long total_virtual_in_bytes;
}
