package com.shiji.exporter.logstash.logstash.node.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Jvm {
    /**
     * pid : 3882
     * version : 1.8.0_171
     * vm_version : 1.8.0_171
     * vm_vendor : Oracle Corporation
     * vm_name : Java HotSpot(TM) 64-Bit Server VM
     * start_time_in_millis : 1594691053616
     * mem : {"heap_init_in_bytes":1073741824,"heap_max_in_bytes":1038876672,"non_heap_init_in_bytes":2555904,"non_heap_max_in_bytes":0}
     * gc_collectors : ["ParNew","ConcurrentMarkSweep"]
     */
    @ApiModelProperty(name = "logstash jvm pid", dataType = "Integer",
                      required = true, value = "logstash jvm pid", notes = "logstash jvm pid")
    private Integer pid;
    @ApiModelProperty(name = "logstash version", dataType = "String",
            required = true, value = "logstash version", notes = "logstash version")
    private String version;
    @ApiModelProperty(name = "logstash vm version", dataType = "String",
            required = true, value = "logstash vm version", notes = "logstash vm version")
    private String vm_version;
    @ApiModelProperty(name = "logstash vm vendor", dataType = "String",
            required = true, value = "logstash vm vendor", notes = "logstash vm vendor")
    private String vm_vendor;
    @ApiModelProperty(name = "logstash vm name", dataType = "String",
            required = true, value = "logstash vm name", notes = "logstash vm name")
    private String vm_name;
    @ApiModelProperty(name = "logstash start time", dataType = "Long ",
            required = true, value = "logstash start time", notes = "logstash start time")
    private Long start_time_in_millis;
    private Mem mem;
    private List<String> gc_collectors;
}
