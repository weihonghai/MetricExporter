package com.shiji.exporter.logstash.logstash.node.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Mem {
    /**
     * heap_init_in_bytes : 1073741824
     * heap_max_in_bytes : 1038876672
     * non_heap_init_in_bytes : 2555904
     * non_heap_max_in_bytes : 0
     */

    @ApiModelProperty(name = "logstash heap init", dataType = "Integer",
            required = true, value = "logstash heap init", notes = "logstash heap init")
    private Integer heap_init_in_bytes;
    @ApiModelProperty(name = "logstash heap max", dataType = "Integer",
            required = true, value = "logstash heap max", notes = "logstash heap max")
    private Integer heap_max_in_bytes;
    @ApiModelProperty(name = "logstash non heap init", dataType = "Integer",
            required = true, value = "logstash non heap init", notes = "logstash non heap init")
    private Integer non_heap_init_in_bytes;
    @ApiModelProperty(name = "logstash non heap max", dataType = "Integer",
            required = true, value = "logstash non heap max", notes = "logstash non heap max")
    private Integer non_heap_max_in_bytes;
}
