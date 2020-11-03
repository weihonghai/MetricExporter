package com.shiji.exporter.logstash.service;


import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashProcessMetrics;

public interface LogstashProcessService {

    public void processParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashProcessMetrics processMetrics);
}
