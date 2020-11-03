package com.shiji.exporter.logstash.dto;

import lombok.Data;

@Data
public class PushGateWay {
    private String ip;
    private String port;
    private String name;
    private String push_interval;
}
