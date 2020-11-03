package com.shiji.exporter.logstash.logstash.node.stats.pipeline;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pipeline {

    private Long workers;
    private Long batch_size;
    private Long batch_delay;
}
