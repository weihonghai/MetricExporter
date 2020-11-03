package com.shiji.exporter.logstash.service;


import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashEventsMetrics;

public interface LogstashEventsService {

    public void eventsParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashEventsMetrics eventsMetrics);

}
