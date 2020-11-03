package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Plugins {
    private List<Inputs> inputs;
//    private List<Codecs> codecs;
    private List<Filters> filters;
    private List<Outputs> outputs;
}
