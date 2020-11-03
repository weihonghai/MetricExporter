package com.shiji.exporter.logstash.logstash.node.stats.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JVM {

    private Threads threads;
    private Mem mem;
    private Gc gc;
    private Long uptime_in_millis;
}
