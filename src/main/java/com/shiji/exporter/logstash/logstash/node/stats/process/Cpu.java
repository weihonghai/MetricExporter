package com.shiji.exporter.logstash.logstash.node.stats.process;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Cpu {
    private Long total_in_millis;
    private Long percent;
    private LoadAverage load_average;
}
