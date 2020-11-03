package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Inputs {
    private String id;
    private InputsEvents events;
    private String name;
    private Long workers;
    private Long queue_size;
}
