package com.shiji.exporter.logstash.logstash.target;

import lombok.Data;

@Data
public class LogstashPrometheusTarget {

    private String instanceName;
    private String instance;
    private String uploadType;
    private String socPartner;
    private String socSolutionName;
    private String targetInstance;
    private String targetInstanceName;
    private String socDeployChannel;
    private String pushGateWayInstance;
    private String logstashVersion;
    private String viewBoard;
}
