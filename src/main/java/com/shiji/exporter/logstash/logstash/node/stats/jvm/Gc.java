package com.shiji.exporter.logstash.logstash.node.stats.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Gc {
    private Collectors collectors;
}
