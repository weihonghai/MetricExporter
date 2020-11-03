package com.shiji.exporter.logstash.logstash.node.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Os {
    /**
     * name : Linux
     * arch : amd64
     * version : 3.10.0-327.el7.x86_64
     * available_processors : 4
     */

    @ApiModelProperty(name = "logstash name", dataType = "String",
            required = true, value = "logstash name", notes = "logstash name")
    private String name;
    @ApiModelProperty(name = "logstash arch", dataType = "String",
            required = true, value = "logstash arch", notes = "logstash arch")
    private String arch;
    @ApiModelProperty(name = "logstash version", dataType = "String",
            required = true, value = "logstash version", notes = "logstash version")
    private String version;
    @ApiModelProperty(name = "logstash available processors", dataType = "Integer",
            required = true, value = "logstash available processors", notes = "logstash available processors")
    private Integer available_processors;
}
