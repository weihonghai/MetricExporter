package com.shiji.exporter.logstash.logstash.node.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pipeline {
    /**
     * workers : 4
     * batch_size : 125
     * batch_delay : 50
     */

    @ApiModelProperty(name = "logstash workers", dataType = "Integer",
            required = true, value = "logstash workers", notes = "logstash workers")
    private Integer workers;
    @ApiModelProperty(name = "logstash batch size", dataType = "Integer",
            required = true, value = "logstash batch size", notes = "logstash batch size")
    private Integer batch_size;
    @ApiModelProperty(name = "logstash batch batch delay", dataType = "Integer",
            required = true, value = "logstash batch batch delay", notes = "logstash batch delay")
    private Integer batch_delay;
}
