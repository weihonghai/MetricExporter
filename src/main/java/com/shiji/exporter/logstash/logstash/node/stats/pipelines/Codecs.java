package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Codecs {
    private String id;
    private Decode decode;
    private Encode encode;
    private String name;
}
