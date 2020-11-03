package com.shiji.exporter.logstash.metrics;

import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.common.client.MetricGauge;
import com.shiji.exporter.logstash.constants.LogstashProcessMetricsConstants;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import lombok.Data;

@Data
public class LogstashProcessMetrics {
    
    private MetricGauge processCpuLoadAverage15m;
    
    private MetricGauge processCpuLoadAverage5m;
    
    private MetricGauge processCpuLoadAverage1m;
    
    private MetricGauge processMemTotalVirtualInBytes;
    
    private MetricGauge processMaxFileDescriptors;
    
    private MetricGauge processOpenFileDescriptors;
    
    private MetricGauge processPeakOpenFileDescriptors;

    public LogstashProcessMetrics(String uploadType, MetricCollectorRegistry registry) {
        this.processCpuLoadAverage15m = processCpuLoadAverage15m(uploadType, registry);
        this.processCpuLoadAverage5m = processCpuLoadAverage5m(uploadType, registry);
        this.processCpuLoadAverage1m = processCpuLoadAverage1m(uploadType, registry);
        this.processMemTotalVirtualInBytes = processMemTotalVirtualInBytes(uploadType, registry);
        this.processMaxFileDescriptors = processMaxFileDescriptors(uploadType, registry);
        this.processOpenFileDescriptors = processOpenFileDescriptors(uploadType, registry);
        this.processPeakOpenFileDescriptors = processPeakOpenFileDescriptors(uploadType, registry);
    }

    public MetricGauge processCpuLoadAverage15m(String uploadType, MetricCollectorRegistry registry){
       return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashProcessMetricsConstants.Metrics.PROCESS_CPU_LOAD_AVERAGE_15M.getName(),
                LogstashProcessMetricsConstants.Metrics.PROCESS_CPU_LOAD_AVERAGE_15M.getValue(),uploadType).create().register(registry);

    }

    public MetricGauge processCpuLoadAverage5m(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashProcessMetricsConstants.Metrics.PROCESS_CPU_LOAD_AVERAGE_5M.getName(),
                LogstashProcessMetricsConstants.Metrics.PROCESS_CPU_LOAD_AVERAGE_5M.getValue(),uploadType).create().register(registry);

    }
    
    public MetricGauge processCpuLoadAverage1m(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashProcessMetricsConstants.Metrics.PROCESS_CPU_LOAD_AVERAGE_1M.getName(),
                LogstashProcessMetricsConstants.Metrics.PROCESS_CPU_LOAD_AVERAGE_1M.getValue(),uploadType).create().register(registry);

    }
    
    public MetricGauge processMemTotalVirtualInBytes(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashProcessMetricsConstants.Metrics.PROCESS_MEM_TOTAL_VIRTUAL_IN_BYTES.getName(),
                LogstashProcessMetricsConstants.Metrics.PROCESS_MEM_TOTAL_VIRTUAL_IN_BYTES.getValue(),uploadType).create().register(registry);

    }
    
    public MetricGauge processMaxFileDescriptors(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashProcessMetricsConstants.Metrics.PROCESS_MAX_FILE_DESCRIPTORS.getName(),
                LogstashProcessMetricsConstants.Metrics.PROCESS_MAX_FILE_DESCRIPTORS.getValue(),uploadType).create().register(registry);

    }
    
    public MetricGauge processOpenFileDescriptors(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashProcessMetricsConstants.Metrics.PROCESS_OPEN_FILE_DESCRIPTORS.getName(),
                LogstashProcessMetricsConstants.Metrics.PROCESS_OPEN_FILE_DESCRIPTORS.getValue(),uploadType).create().register(registry);
    }
    
    public MetricGauge processPeakOpenFileDescriptors(String uploadType, MetricCollectorRegistry registry){
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashProcessMetricsConstants.Metrics.PROCESS_PEAK_OPEN_FILE_DESCRIPTORS.getName(),
                LogstashProcessMetricsConstants.Metrics.PROCESS_PEAK_OPEN_FILE_DESCRIPTORS.getValue(),uploadType).create().register(registry);

    }
    public void cleanMetrics() {
        this.processCpuLoadAverage15m.clear();
        this.processCpuLoadAverage5m.clear();
        this.processCpuLoadAverage1m.clear();
        this.processMemTotalVirtualInBytes.clear();
        this.processMaxFileDescriptors.clear();
        this.processOpenFileDescriptors.clear();
        this.processPeakOpenFileDescriptors.clear();
    }
}
