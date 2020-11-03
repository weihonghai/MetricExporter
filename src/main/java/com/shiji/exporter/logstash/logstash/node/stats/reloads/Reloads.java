package com.shiji.exporter.logstash.logstash.node.stats.reloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Reloads {

    private Long failures;
    private Long successes;
}
