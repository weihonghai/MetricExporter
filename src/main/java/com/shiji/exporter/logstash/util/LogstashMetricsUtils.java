package com.shiji.exporter.logstash.util;

import com.shiji.common.client.MetricGauge;
import com.shiji.exporter.logstash.constants.LogstashMetricsConstants;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import org.springframework.util.StringUtils;

public class LogstashMetricsUtils {

    public static MetricGauge.Builder getGaugeMetricsBuilder(String metricsNameLabel, String metricsDescribeLabel, String uploadType) {
        if (StringUtils.hasText(uploadType) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType)){
             return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.PUSH_GATE_WAY_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        }else {
            return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        }

    }

    public static MetricGauge.Builder getGaugePipelinesQueueMetricsBuilder(String metricsNameLabel, String metricsDescribeLabel,String uploadType) {
        if (StringUtils.hasText(uploadType) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType)){
            return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE_QUEUE_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.PUSH_GATE_WAY_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        } else {
            return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE_QUEUE_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        }

    }

    public static MetricGauge.Builder getGaugePipelinesInputOrOutPutMetricsBuilder(String metricsNameLabel, String metricsDescribeLabel,String uploadType) {
        if (StringUtils.hasText(uploadType) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType)){
            return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE.getName(),
                            LogstashMetricsConstants.Metrics.PLUGIN_ID.getName(),
                            LogstashMetricsConstants.Metrics.NAME.getName(),
                            LogstashMetricsConstants.Metrics.PUSH_GATE_WAY_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        }else{
            return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE.getName(),
                            LogstashMetricsConstants.Metrics.PLUGIN_ID.getName(),
                            LogstashMetricsConstants.Metrics.NAME.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        }

    }

    public static MetricGauge.Builder getGaugePipelinesEventsMetricsBuilder(String metricsNameLabel, String metricsDescribeLabel,String uploadType) {
        if (StringUtils.hasText(uploadType) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType)){
            return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE.getName(),
                            LogstashMetricsConstants.Metrics.PUSH_GATE_WAY_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        }else{
            return MetricGauge.build().name(LogstashMetricsConstants.Metrics.METRICS_PREFIX.getName() + metricsNameLabel)
                    .labelNames(
                            LogstashMetricsConstants.Metrics.INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.APPLICATION.getName(),
                            LogstashMetricsConstants.Metrics.UPLOAD_TYPE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_PARTNER.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE.getName(),
                            LogstashMetricsConstants.Metrics.SOC_SOLUTION_MONITOR_INSTANCE_NAME.getName(),
                            LogstashMetricsConstants.Metrics.SOC_DEPLOY_CHANNEL.getName(),
                            LogstashMetricsConstants.Metrics.PIPELINE.getName(),
                            LogstashMetricsConstants.Metrics.LOGSTASH_VERSION.getName(),
                            LogstashMetricsConstants.Metrics.VIEW_BOARD.getName()
                    ).help(metricsDescribeLabel);
        }

    }

    public static void setMetricGauge(MetricGauge MetricGauge, LogstashPrometheusTarget target, Double value){
        if (StringUtils.hasText(target.getUploadType()) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(target.getUploadType())){
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    target.getPushGateWayInstance(),
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value);
        }else{
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value);
        }
    }

    public static void setMetricGaugePipelinesInputOrOutPutMetrics(MetricGauge MetricGauge,LogstashPrometheusTarget target,String pipeline,String pluginId,String pluginName,Double value) {
        if (StringUtils.hasText(target.getUploadType()) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(target.getUploadType())){
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    pipeline,
                    pluginId,
                    pluginName,
                    target.getPushGateWayInstance(),
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value.doubleValue());
        } else {
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    pipeline,
                    pluginId,
                    pluginName,
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value.doubleValue());
        }

    }


    public static void setMetricGaugePipelinesEventsMetrics(MetricGauge MetricGauge,LogstashPrometheusTarget target,String pipeline,Double value) {
        if (StringUtils.hasText(target.getUploadType()) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(target.getUploadType())){
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    pipeline,
                    target.getPushGateWayInstance(),
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value.doubleValue());
        } else {
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    pipeline,
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value.doubleValue());
        }

    }

    public static void setMetricGaugePipelinesQueueMetrics(MetricGauge MetricGauge,LogstashPrometheusTarget target,String pipeline,String queueType,Double value) {
        if (StringUtils.hasText(target.getUploadType()) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(target.getUploadType())){
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    pipeline,
                    queueType,
                    target.getPushGateWayInstance(),
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value.doubleValue());
        } else {
            MetricGauge.labels(target.getInstance(),
                    target.getInstanceName(),
                    target.getUploadType(),
                    target.getSocPartner(),
                    target.getSocSolutionName(),
                    target.getTargetInstance(),
                    target.getTargetInstanceName(),
                    target.getSocDeployChannel(),
                    pipeline,
                    queueType,
                    target.getLogstashVersion(),
                    target.getViewBoard())
                    .set(value.doubleValue());
        }

    }

}
