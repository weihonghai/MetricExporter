package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Outputs {
    private String id;
    private OutputsEvents events;
    private String name;
}
