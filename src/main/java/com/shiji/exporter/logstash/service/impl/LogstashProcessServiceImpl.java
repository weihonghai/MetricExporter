package com.shiji.exporter.logstash.service.impl;

import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.node.stats.process.Cpu;
import com.shiji.exporter.logstash.logstash.node.stats.process.Mem;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashProcessMetrics;
import com.shiji.exporter.logstash.service.LogstashProcessService;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import com.shiji.exporter.logstash.logstash.node.stats.process.Process;
import org.springframework.stereotype.Service;


@Service
public class LogstashProcessServiceImpl implements LogstashProcessService {
    
    @Override
    public void processParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashProcessMetrics processMetrics) {
        if (null != stats) {
            Process process = stats.getProcess();
            if (null != process) {
                this.processFileDescriptors(
                        process.getMax_file_descriptors(),
                        process.getOpen_file_descriptors(),
                        process.getPeak_open_file_descriptors(), target,processMetrics);
                this.processMen(process.getMem(), target,processMetrics);
                this.processCpuLoadAverage(process.getCpu(), target,processMetrics);
            }
        }
    }

    private void processFileDescriptors(Long max, Long open, Long peak, LogstashPrometheusTarget target,LogstashProcessMetrics processMetrics) {
        if (null != max) {
            LogstashMetricsUtils.setMetricGauge(processMetrics.getProcessMaxFileDescriptors(),target,max.doubleValue());
        }
        if (null != open) {
            LogstashMetricsUtils.setMetricGauge(processMetrics.getProcessOpenFileDescriptors(),target,open.doubleValue());
        }
        if (null != peak) {
            LogstashMetricsUtils.setMetricGauge(processMetrics.getProcessPeakOpenFileDescriptors(),target,peak.doubleValue());
        }
    }

    private void processMen(Mem mem, LogstashPrometheusTarget target, LogstashProcessMetrics processMetrics) {
        if (null != mem) {
            Long bytes = mem.getTotal_virtual_in_bytes();
            if (null != bytes) {
                LogstashMetricsUtils.setMetricGauge(processMetrics.getProcessMemTotalVirtualInBytes(),target,bytes.doubleValue());
            }
        }
    }

    private void processCpuLoadAverage(Cpu cpu, LogstashPrometheusTarget target, LogstashProcessMetrics processMetrics) {
        if (null != cpu) {
            Double m1 = cpu.getLoad_average().get_1m();
            Double m5 = cpu.getLoad_average().get_5m();
            Double m15 = cpu.getLoad_average().get_15m();
            if (null != m1) {
                LogstashMetricsUtils.setMetricGauge(processMetrics.getProcessCpuLoadAverage1m(),target,m1.doubleValue());
            }
            if (null != m5) {
                LogstashMetricsUtils.setMetricGauge(processMetrics.getProcessCpuLoadAverage5m(),target,m5.doubleValue());
            }
            if (null != m15) {
                LogstashMetricsUtils.setMetricGauge(processMetrics.getProcessCpuLoadAverage15m(),target,m15.doubleValue());
            }

        }
    }
}
