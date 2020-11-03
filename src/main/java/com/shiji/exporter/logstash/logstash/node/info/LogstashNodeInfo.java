package com.shiji.exporter.logstash.logstash.node.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LogstashNodeInfo {

    /**
     * host : localhost.localdomain
     * version : 7.3.2
     * http_address : 192.168.48.11:9600
     * id : de378551-cde2-42eb-a0b0-866ac9f21a07
     * name : localhost.localdomain
     * ephemeral_id : 904e53e8-9e4a-4ac4-abf6-1d22ec80793e
     * status : green
     * snapshot : false
     * pipeline : {"workers":4,"batch_size":125,"batch_delay":50}
     * pipelines : {"main":{"ephemeral_id":"22287079-1348-44e5-afd9-443e94c4f75e","hash":"59ffb75c09974843fc373bdde79334737ca4ad23b0a05270934a04341ded7e67","workers":4,"batch_size":125,"batch_delay":50,"config_reload_automatic":false,"config_reload_interval":3000000000,"dead_letter_queue_enabled":false}}
     * os : {"name":"Linux","arch":"amd64","version":"3.10.0-327.el7.x86_64","available_processors":4}
     * jvm : {"pid":3882,"version":"1.8.0_171","vm_version":"1.8.0_171","vm_vendor":"Oracle Corporation","vm_name":"Java HotSpot(TM) 64-Bit Server VM","start_time_in_millis":1594691053616,"mem":{"heap_init_in_bytes":1073741824,"heap_max_in_bytes":1038876672,"non_heap_init_in_bytes":2555904,"non_heap_max_in_bytes":0},"gc_collectors":["ParNew","ConcurrentMarkSweep"]}
     */

    @ApiModelProperty(name = "logstash host", dataType = "String",
            required = true, value = "logstash host", notes = "logstash host")
    private String host;
    @ApiModelProperty(name = "logstash version", dataType = "version",
            required = true, value = "logstash version", notes = "logstash version")
    private String version;
    @ApiModelProperty(name = "logstash address", dataType = "address",
            required = true, value = "logstash address", notes = "logstash address")
    private String http_address;
    @ApiModelProperty(name = "logstash id", dataType = "id",
            required = true, value = "logstash id", notes = "logstash id")
    private String id;
    @ApiModelProperty(name = "logstash name", dataType = "name",
            required = true, value = "logstash name", notes = "logstash name")
    private String name;
    @ApiModelProperty(name = "logstash ephemeral id", dataType = "ephemeral id",
            required = true, value = "logstash ephemeral id", notes = "logstash ephemeral id")
    private String ephemeral_id;
    @ApiModelProperty(name = "logstash status", dataType = "status",
            required = true, value = "logstash status", notes = "logstash status")
    private String status;
    @ApiModelProperty(name = "logstash snapshot", dataType = "snapshot",
            required = true, value = "logstash snapshot", notes = "logstash snapshot")
    private Boolean snapshot;
    @ApiModelProperty(name = "logstash pipeline", dataType = "pipeline",
            required = true, value = "logstash pipeline", notes = "logstash pipeline")
    private Pipeline pipeline;
    private Pipelines pipelines;
    private Os os;
    private Jvm jvm;
}
