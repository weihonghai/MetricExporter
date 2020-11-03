package com.shiji.exporter.logstash.service.impl;

import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.node.stats.jvm.*;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashJvmMetrics;
import com.shiji.exporter.logstash.service.LogstashJvmService;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import org.springframework.stereotype.Service;


@Service
public class LogstashJvmServiceImpl implements LogstashJvmService {

    @Override
    public void jvmParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashJvmMetrics jvmMetrics) {
        if (null != stats) {
            JVM jvm = stats.getJvm();
            if (null != jvm) {
                this.jvmThreads(jvm.getThreads(), target,jvmMetrics);
                this.jvmMem(jvm.getMem(), target,jvmMetrics);
                this.jvmUptime(jvm.getUptime_in_millis(), target,jvmMetrics);
                this.jvmGC(jvm.getGc(),target,jvmMetrics);
            }

        }

    }

    private void jvmGC(Gc gc, LogstashPrometheusTarget target, LogstashJvmMetrics jvmMetrics){
        if (null != gc){
            Collectors collectors =  gc.getCollectors();
            if (null != collectors){
                OldX oldX = collectors.getOld();
                YoungX youngX = collectors.getYoung();
                if (null != oldX){
                    Long collectionCount = oldX.getCollection_count();
                    Long timeInMillis = oldX.getCollection_time_in_millis();
                    if (null != collectionCount){
                        LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmGcCollectorsOldCollectionCount(),target,collectionCount.doubleValue());
                    }
                    if (null != timeInMillis){
                        LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmGcCollectorsOldCollectionTimeInMillis(),target,timeInMillis.doubleValue());
                    }
                }
                if (null != youngX){
                    Long collectionCount = youngX.getCollection_count();
                    Long timeInMillis = youngX.getCollection_time_in_millis();
                    if (null != collectionCount){
                        LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmGcCollectorsYoungCollectionCount(),target,collectionCount.doubleValue());
                    }
                    if (null != timeInMillis){
                        LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmGcCollectorsYoungCollectionTimeInMillis(),target,timeInMillis.doubleValue());
                    }
                }
            }
        }

    }

    private void jvmUptime(Long uptime,LogstashPrometheusTarget target,LogstashJvmMetrics jvmMetrics){
        if (null != uptime) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmUptimeMillis(),target,uptime.doubleValue());
        }
    }

    private void jvmThreads(Threads threads, LogstashPrometheusTarget target,LogstashJvmMetrics jvmMetrics) {
        if (null != threads) {
            Long count = threads.getCount();
            Long peakCount = threads.getPeak_count();
            if (null != count) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmThreadCounts(),target,count.doubleValue());
            }
            if (null != peakCount) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmThreadPeakCounts(),target,peakCount.doubleValue());
            }
        }
    }

    private void jvmMem(Mem mem, LogstashPrometheusTarget target,LogstashJvmMetrics jvmMetrics) {
        if (null != mem) {
            Long heapCommitted = mem.getHeap_committed_in_bytes();
            Long heapMax = mem.getHeap_max_in_bytes();
            Long heapUsed = mem.getHeap_used_in_bytes();
            Long heapUsedPercent = mem.getHeap_used_percent();
            Long nonHeapCommitted = mem.getNon_heap_committed_in_bytes();
            Long nonHeapUsed = mem.getNon_heap_used_in_bytes();
            this.jvmMemPool(mem.getPools(), target,jvmMetrics);
            if (null != heapCommitted) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemHeapCommittedInBytes(),target,heapCommitted.doubleValue());
            }
            if (null != heapMax) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemHeapMaxInBytes(),target,heapMax.doubleValue());
            }
            if (null != heapUsed) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemHeapUsedInBytes(),target,heapUsed.doubleValue());
            }
            if (null != heapUsedPercent) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemHeapUsedPercent(),target,heapUsedPercent.doubleValue());
            }
            if (null != nonHeapCommitted) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemNonHeapCommittedInBytes(),target,nonHeapCommitted.doubleValue());
            }
            if (null != nonHeapUsed) {
                LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemNonHeapUsedInBytes(),target,nonHeapUsed.doubleValue());
            }
        }
    }

    private void jvmMemPool(Pools pools, LogstashPrometheusTarget target,LogstashJvmMetrics jvmMetrics) {
        Old old = pools.getOld();
        Young young = pools.getYoung();
        Survivor survivor = pools.getSurvivor();
        if (null != old) {
            this.jvmMenPoolOld(old, target,jvmMetrics);
        }
        if (null != young) {
            this.jvmMenPoolYoung(young, target,jvmMetrics);
        }
        if (null != survivor) {
            this.jvmMenPoolSurvivor(survivor, target,jvmMetrics);
        }
    }

    private void jvmMenPoolOld(Old old, LogstashPrometheusTarget target,LogstashJvmMetrics jvmMetrics) {
        Long committed = old.getCommitted_in_bytes();
        Long max = old.getMax_in_bytes();
        Long peakMax = old.getPeak_max_in_bytes();
        Long peakUsed = old.getPeak_used_in_bytes();
        Long used = old.getUsed_in_bytes();
        if (null != committed) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsOldCommittedInBytes(),target,committed.doubleValue());
        }
        if (null != max) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsOldMaxInBytes(),target,max.doubleValue());
        }
        if (null != peakMax) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsOldPeakMaxInBytes(),target,peakMax.doubleValue());
        }
        if (null != peakUsed) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsOldPeakUsedInBytes(),target,peakUsed.doubleValue());
        }
        if (null != used) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsOldUsedInBytes(),target,used.doubleValue());
        }
    }

    private void jvmMenPoolYoung(Young young, LogstashPrometheusTarget target,LogstashJvmMetrics jvmMetrics) {
        Long committed = young.getCommitted_in_bytes();
        Long max = young.getMax_in_bytes();
        Long peakMax = young.getPeak_max_in_bytes();
        Long peakUsed = young.getPeak_used_in_bytes();
        Long used = young.getUsed_in_bytes();
        if (null != committed) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsYoungCommittedInBytes(),target,committed.doubleValue());
        }
        if (null != max) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsYoungMaxInBytes(),target,max.doubleValue());
        }
        if (null != peakMax) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsYoungPeakMaxInBytes(),target,peakMax.doubleValue());
        }
        if (null != peakUsed) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsYoungPeakUsedInBytes(),target,peakUsed.doubleValue());
        }
        if (null != used) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsYoungUsedInBytes(),target,used.doubleValue());
        }
    }

    private void jvmMenPoolSurvivor(Survivor survivor, LogstashPrometheusTarget target,LogstashJvmMetrics jvmMetrics) {
        Long committed = survivor.getCommitted_in_bytes();
        Long max = survivor.getMax_in_bytes();
        Long peakMax = survivor.getPeak_max_in_bytes();
        Long peakUsed = survivor.getPeak_used_in_bytes();
        Long used = survivor.getUsed_in_bytes();
        if (null != committed) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsSurvivorCommittedInBytes(),target,committed.doubleValue());
        }
        if (null != max) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsSurvivorMaxInBytes(),target,max.doubleValue());
        }
        if (null != peakMax) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsSurvivorPeakMaxInBytes(),target,peakMax.doubleValue());
        }
        if (null != peakUsed) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsSurvivorPeakUsedInBytes(),target,peakUsed.doubleValue());
        }
        if (null != used) {
            LogstashMetricsUtils.setMetricGauge(jvmMetrics.getJvmMemPoolsSurvivorUsedInBytes(),target,used.doubleValue());
        }
    }

}
