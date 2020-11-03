package com.shiji.exporter.logstash.service;

import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashPipelinesMetrics;

public interface LogstashPipelinesService {

    public void pipelinesParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashPipelinesMetrics pipeMetrics);
}
