package com.shiji.exporter.logstash.service.impl;

import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.node.stats.events.Events;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashEventsMetrics;
import com.shiji.exporter.logstash.service.LogstashEventsService;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import org.springframework.stereotype.Service;

@Service
public class LogstashEventsServiceImpl implements LogstashEventsService {

    @Override
    public void eventsParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashEventsMetrics eventsMetrics) {
        if (null != stats) {
            Events events = stats.getEvents();
            if (null != events) {
                Long duration = events.getDuration_in_millis();
                Long filtered = events.getFiltered();
                Long out = events.getOut();
                Long in = events.getIn();
                Long queueDuration = events.getQueue_push_duration_in_millis();
                if (null != duration) {
                    LogstashMetricsUtils.setMetricGauge(eventsMetrics.getEventsDurationInMillis(), target, duration.doubleValue());
                }
                if (null != filtered) {
                    LogstashMetricsUtils.setMetricGauge(eventsMetrics.getEventsFiltered(), target, filtered.doubleValue());
                }
                if (null != out) {
                    LogstashMetricsUtils.setMetricGauge(eventsMetrics.getEventsOut(), target, out.doubleValue());
                }
                if (null != in) {
                    LogstashMetricsUtils.setMetricGauge(eventsMetrics.getEventsIn(), target, in.doubleValue());
                }
                if (null != queueDuration) {
                    LogstashMetricsUtils.setMetricGauge(eventsMetrics.getEventsQueuePushDurationInMillis(), target, queueDuration.doubleValue());
                }
            }
        }
    }

}
