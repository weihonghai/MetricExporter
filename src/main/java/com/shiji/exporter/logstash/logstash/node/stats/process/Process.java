package com.shiji.exporter.logstash.logstash.node.stats.process;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Process {


    private Long open_file_descriptors;
    private Long peak_open_file_descriptors;
    private Long max_file_descriptors;
    private Mem mem;
    private Cpu cpu;
}
