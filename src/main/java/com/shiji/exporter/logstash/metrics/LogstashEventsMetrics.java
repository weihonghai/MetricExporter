package com.shiji.exporter.logstash.metrics;

import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.common.client.MetricGauge;
import com.shiji.exporter.logstash.constants.LogstashEventsMetricsConstants;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import lombok.Data;

@Data
public class LogstashEventsMetrics {

    private MetricGauge eventsDurationInMillis;

    private MetricGauge eventsFiltered;

    private MetricGauge eventsIn;

    private MetricGauge eventsOut;

    private MetricGauge eventsQueuePushDurationInMillis;

    public LogstashEventsMetrics(String uploadType, MetricCollectorRegistry registry) {
        this.eventsDurationInMillis = eventsDurationInMillisRegister(uploadType, registry);
        this.eventsFiltered = eventsFilteredRegister(uploadType, registry);
        this.eventsIn = eventsInRegister(uploadType, registry);
        this.eventsOut = eventsOutRegister(uploadType, registry);
        this.eventsQueuePushDurationInMillis = eventsQueuePushDurationInMillisRegister(uploadType, registry);
    }

    private MetricGauge eventsDurationInMillisRegister(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashEventsMetricsConstants.Metrics.EVENTS_DURATION_IN_MILLIS.getName(),
                LogstashEventsMetricsConstants.Metrics.EVENTS_DURATION_IN_MILLIS.getValue(), uploadType).create().register(registry);
    }


    private MetricGauge eventsFilteredRegister(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashEventsMetricsConstants.Metrics.EVENTS_FILTERED.getName(),
                LogstashEventsMetricsConstants.Metrics.EVENTS_FILTERED.getValue(), uploadType).create().register(registry);

    }


    private MetricGauge eventsInRegister(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashEventsMetricsConstants.Metrics.EVENTS_IN.getName(),
                LogstashEventsMetricsConstants.Metrics.EVENTS_IN.getValue(), uploadType).create().register(registry);

    }


    private MetricGauge eventsOutRegister(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashEventsMetricsConstants.Metrics.EVENTS_OUT.getName(),
                LogstashEventsMetricsConstants.Metrics.EVENTS_OUT.getValue(), uploadType).create().register(registry);

    }

    private MetricGauge eventsQueuePushDurationInMillisRegister(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashEventsMetricsConstants.Metrics.EVENTS_QUEUE_PUSH_DURATION_IN_MILLIS.getName(),
                LogstashEventsMetricsConstants.Metrics.EVENTS_QUEUE_PUSH_DURATION_IN_MILLIS.getValue(), uploadType).create().register(registry);
    }

    public void cleanMetrics() {
        this.eventsDurationInMillis.clear();
        this.eventsFiltered.clear();
        this.eventsIn.clear();
        this.eventsOut.clear();
        this.eventsQueuePushDurationInMillis.clear();
    }

}
