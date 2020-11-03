package com.shiji.exporter.logstash.logstash.node.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Main {
    /**
     * ephemeral_id : 22287079-1348-44e5-afd9-443e94c4f75e
     * hash : 59ffb75c09974843fc373bdde79334737ca4ad23b0a05270934a04341ded7e67
     * workers : 4
     * batch_size : 125
     * batch_delay : 50
     * config_reload_automatic : false
     * config_reload_interval : 3000000000
     * dead_letter_queue_enabled : false
     */

    @ApiModelProperty(name = "logstash ephemeral id", dataType = "String",
            required = true, value = "logstash ephemeral id", notes = "logstash ephemeral id")
    private String ephemeral_id;
    @ApiModelProperty(name = "logstash hash", dataType = "String",
            required = true, value = "logstash hash", notes = "logstash hash")
    private String hash;
    @ApiModelProperty(name = "logstash workers", dataType = "Integer",
            required = true, value = "logstash workers", notes = "logstash workers")
    private Integer workers;
    @ApiModelProperty(name = "logstash batch size", dataType = "Integer",
            required = true, value = "logstash batch size", notes = "logstash batch size")
    private Integer batch_size;
    @ApiModelProperty(name = "logstash batch delay", dataType = "Integer",
            required = true, value = "logstash batch delay", notes = "logstash batch delay")
    private Integer batch_delay;
    private Boolean config_reload_automatic;
    private Long config_reload_interval;
    private Boolean dead_letter_queue_enabled;
}
