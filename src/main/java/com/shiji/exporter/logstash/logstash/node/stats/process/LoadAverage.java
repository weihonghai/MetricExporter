package com.shiji.exporter.logstash.logstash.node.stats.process;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoadAverage {
    private Double _1m;
    private Double _5m;
    private Double _15m;
}
