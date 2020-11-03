package com.shiji.exporter.logstash.logstash.node.stats.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pools {
    private Survivor survivor;
    private Old old;
    private Young young;
}
