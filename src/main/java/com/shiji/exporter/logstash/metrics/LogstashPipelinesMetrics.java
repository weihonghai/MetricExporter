package com.shiji.exporter.logstash.metrics;

import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.common.client.MetricGauge;
import com.shiji.exporter.logstash.constants.LogstashPipelinesMetricsConstants;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import lombok.Data;


@Data
public class LogstashPipelinesMetrics {

    private MetricGauge pipelinesEventsDurationInMillis;

    private MetricGauge pipelinesEventsFiltered;

    private MetricGauge pipelinesEventsIn;

    private MetricGauge pipelinesEventsOut;

    private MetricGauge pipelinesEventsQueuePushDurationInMillis;
    
    private MetricGauge pipelinesPluginsInputsEventsOut;

    private MetricGauge pipelinesPluginsInputsQueuePushDurationInMillis;
    
    private MetricGauge pipelinePluginsInputsWorkers;

    private MetricGauge pipelinePluginsInputsQueueSize;
    
    private MetricGauge pipelinesPluginsOutPutsEventsDurationInMillis;
    
    private MetricGauge pipelinesPluginsOutPutsEventsIn;

    private MetricGauge pipelinesPluginsOutPutsEventsOut;

    private MetricGauge pipelinesQueueEventsCount;

    private MetricGauge pipelinesQueueSizeInBytes;

    private MetricGauge pipelinesQueueMaxQueueSizeInBytes;

    public LogstashPipelinesMetrics(String uploadType, MetricCollectorRegistry registry) {
        this.pipelinesEventsDurationInMillis = pipelinesEventsDurationInMillis(uploadType, registry);

        this.pipelinesEventsFiltered = pipelinesEventsFiltered(uploadType, registry);

        this.pipelinesEventsIn = pipelinesEventsIn(uploadType, registry);

        this.pipelinesEventsOut = pipelinesEventsOut(uploadType, registry);

        this.pipelinesEventsQueuePushDurationInMillis = pipelinesEventsQueuePushDurationInMillis(uploadType, registry);

        this.pipelinesPluginsInputsEventsOut = pipelinesPluginsInputsEventsOut(uploadType, registry);

        this.pipelinesPluginsInputsQueuePushDurationInMillis = pipelinesPluginsInputsQueuePushDurationInMillis(uploadType, registry);

        this.pipelinePluginsInputsWorkers = pipelinePluginsInputsWorkers(uploadType, registry);

        this.pipelinePluginsInputsQueueSize = pipelinePluginsInputsQueueSize(uploadType, registry);

        this.pipelinesPluginsOutPutsEventsDurationInMillis = pipelinesPluginsOutPutsEventsDurationInMillis(uploadType, registry);

        this.pipelinesPluginsOutPutsEventsIn = pipelinesPluginsOutPutsEventsIn(uploadType, registry);

        this.pipelinesPluginsOutPutsEventsOut = pipelinesPluginsOutPutsEventsOut(uploadType, registry);

        this.pipelinesQueueEventsCount = pipelinesQueueEventsCount(uploadType, registry);

        this.pipelinesQueueSizeInBytes = pipelinesQueueSizeInBytes(uploadType, registry);

        this.pipelinesQueueMaxQueueSizeInBytes = pipelinesQueueMaxQueueSizeInBytes(uploadType, registry);
    }

    public MetricGauge pipelinesEventsDurationInMillis(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesEventsMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_DURATION_IN_MILLIS.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_DURATION_IN_MILLIS.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesEventsFiltered(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesEventsMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_FILTERED.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_FILTERED.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesEventsIn(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesEventsMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_IN.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_IN.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesEventsOut(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesEventsMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_OUT.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_OUT.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesEventsQueuePushDurationInMillis(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesEventsMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_QUEUE_PUSH_DURATION_IN_MILLIS.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_EVENTS_QUEUE_PUSH_DURATION_IN_MILLIS.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesPluginsInputsEventsOut(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesInputOrOutPutMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_INPUTS_EVENTS_OUT.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_INPUTS_EVENTS_OUT.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesPluginsInputsQueuePushDurationInMillis(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesInputOrOutPutMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_INPUTS_QUEUE_PUSH_DURATION_IN_MILLIS.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_INPUTS_QUEUE_PUSH_DURATION_IN_MILLIS.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinePluginsInputsWorkers(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesInputOrOutPutMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINE_PLUGINS_INPUTS_WORKERS.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINE_PLUGINS_INPUTS_WORKERS.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinePluginsInputsQueueSize(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesInputOrOutPutMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINE_PLUGINS_INPUTS_QUEUE_SIZE.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINE_PLUGINS_INPUTS_QUEUE_SIZE.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesPluginsOutPutsEventsDurationInMillis(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesInputOrOutPutMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_OUTPUTS_EVENTS_DURATION_IN_MILLIS.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_OUTPUTS_EVENTS_DURATION_IN_MILLIS.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesPluginsOutPutsEventsIn(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesInputOrOutPutMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_OUTPUTS_EVENTS_IN.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_OUTPUTS_EVENTS_IN.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesPluginsOutPutsEventsOut(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesInputOrOutPutMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_OUTPUTS_EVENTS_OUT.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_PLUGINS_OUTPUTS_EVENTS_OUT.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesQueueEventsCount(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesQueueMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_QUEUE_EVENTS_COUNT.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_QUEUE_EVENTS_COUNT.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesQueueSizeInBytes(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesQueueMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_QUEUE_SIZE_IN_BYTES.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_QUEUE_SIZE_IN_BYTES.getValue(),uploadType).create().register(registry);

    }

    
    public MetricGauge pipelinesQueueMaxQueueSizeInBytes(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugePipelinesQueueMetricsBuilder(
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_QUEUE_MAX_QUEUE_SIZE_IN_BYTES.getName(),
                LogstashPipelinesMetricsConstants.Metrics.PIPELINES_QUEUE_MAX_QUEUE_SIZE_IN_BYTES.getValue(),uploadType).create().register(registry);

    }
    public void cleanMetrics() {
        this.pipelinesEventsDurationInMillis.clear();
        this.pipelinesEventsFiltered.clear();
        this.pipelinesEventsIn.clear();
        this.pipelinesEventsOut.clear();
        this.pipelinesEventsQueuePushDurationInMillis.clear();
        this.pipelinesPluginsInputsEventsOut.clear();
        this.pipelinesPluginsInputsQueuePushDurationInMillis.clear();
        this.pipelinePluginsInputsWorkers.clear();
        this.pipelinePluginsInputsQueueSize.clear();
        this.pipelinesPluginsOutPutsEventsDurationInMillis.clear();
        this.pipelinesPluginsOutPutsEventsIn.clear();
        this.pipelinesPluginsOutPutsEventsOut.clear();
        this.pipelinesQueueEventsCount.clear();
        this.pipelinesQueueSizeInBytes.clear();
        this.pipelinesQueueMaxQueueSizeInBytes.clear();

    }

}
