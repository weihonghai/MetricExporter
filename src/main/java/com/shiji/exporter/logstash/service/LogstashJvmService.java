package com.shiji.exporter.logstash.service;


import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashJvmMetrics;

public interface LogstashJvmService {

    public void jvmParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashJvmMetrics jvmMetrics);
}
