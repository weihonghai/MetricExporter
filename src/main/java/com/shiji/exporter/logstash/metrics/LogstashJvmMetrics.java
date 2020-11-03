package com.shiji.exporter.logstash.metrics;

import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.common.client.MetricGauge;
import com.shiji.exporter.logstash.constants.LogstashJvmMetricsConstants;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import lombok.Data;


@Data
public class LogstashJvmMetrics {

    private MetricGauge jvmThreadCounts;
    private MetricGauge jvmThreadPeakCounts;
    private MetricGauge jvmMemHeapUsedPercent;
    private MetricGauge jvmMemHeapCommittedInBytes;
    private MetricGauge jvmMemHeapMaxInBytes;
    private MetricGauge jvmMemHeapUsedInBytes;
    private MetricGauge jvmMemNonHeapUsedInBytes;
    private MetricGauge jvmMemNonHeapCommittedInBytes;
    private MetricGauge jvmMemPoolsSurvivorUsedInBytes;
    private MetricGauge jvmMemPoolsSurvivorPeakUsedInBytes;
    private MetricGauge jvmMemPoolsSurvivorPeakMaxInBytes;
    private MetricGauge jvmMemPoolsSurvivorMaxInBytes;
    private MetricGauge jvmMemPoolsSurvivorCommittedInBytes;
    private MetricGauge jvmMemPoolsYoungUsedInBytes;
    private MetricGauge jvmMemPoolsYoungPeakMaxInBytes;
    private MetricGauge jvmMemPoolsYoungMaxInBytes;
    private MetricGauge jvmMemPoolsYoungCommittedInBytes;
    private MetricGauge jvmMemPoolsOldUsedInBytes;
    private MetricGauge jvmMemPoolsOldPeakUsedInBytes;
    private MetricGauge jvmMemPoolsOldPeakMaxInBytes;
    private MetricGauge jvmMemPoolsOldMaxInBytes;
    private MetricGauge jvmMemPoolsOldCommittedInBytes;
    private MetricGauge jvmGcCollectorsOldCollectionCount;
    private MetricGauge jvmGcCollectorsOldCollectionTimeInMillis;
    private MetricGauge jvmGcCollectorsYoungCollectionCount;
    private MetricGauge jvmGcCollectorsYoungCollectionTimeInMillis;
    private MetricGauge jvmUptimeMillis;
    private MetricGauge jvmMemPoolsYoungPeakUsedInBytes;

    public LogstashJvmMetrics(String uploadType, MetricCollectorRegistry registry) {
        this.jvmThreadCounts = jvmThreadCounts(uploadType, registry);
        this.jvmThreadPeakCounts = jvmThreadPeakCounts(uploadType, registry);
        this.jvmMemHeapUsedPercent = jvmMemHeapUsedPercent(uploadType, registry);
        this.jvmMemHeapCommittedInBytes = jvmMemHeapCommittedInBytes(uploadType, registry);
        this.jvmMemHeapMaxInBytes = jvmMemHeapMaxInBytes(uploadType, registry);
        this.jvmMemHeapUsedInBytes = jvmMemHeapUsedInBytes(uploadType, registry);
        this.jvmMemNonHeapUsedInBytes = jvmMemNonHeapUsedInBytes(uploadType, registry);
        this.jvmMemNonHeapCommittedInBytes = jvmMemNonHeapCommittedInBytes(uploadType, registry);
        this.jvmMemPoolsSurvivorUsedInBytes = jvmMemPoolsSurvivorUsedInBytes(uploadType, registry);
        this.jvmMemPoolsSurvivorPeakUsedInBytes = jvmMemPoolsSurvivorPeakUsedInBytes(uploadType, registry);
        this.jvmMemPoolsSurvivorPeakMaxInBytes = jvmMemPoolsSurvivorPeakMaxInBytes(uploadType, registry);
        this.jvmMemPoolsSurvivorMaxInBytes = jvmMemPoolsSurvivorMaxInBytes(uploadType, registry);
        this.jvmMemPoolsSurvivorCommittedInBytes = jvmMemPoolsSurvivorCommittedInBytes(uploadType, registry);
        this.jvmMemPoolsYoungUsedInBytes = jvmMemPoolsYoungUsedInBytes(uploadType, registry);
        this.jvmMemPoolsYoungPeakMaxInBytes = jvmMemPoolsYoungPeakMaxInBytes(uploadType, registry);
        this.jvmMemPoolsYoungMaxInBytes = jvmMemPoolsYoungMaxInBytes(uploadType, registry);
        this.jvmMemPoolsYoungCommittedInBytes = jvmMemPoolsYoungCommittedInBytes(uploadType, registry);
        this.jvmMemPoolsOldUsedInBytes = jvmMemPoolsOldUsedInBytes(uploadType, registry);
        this.jvmMemPoolsOldPeakUsedInBytes = jvmMemPoolsOldPeakUsedInBytes(uploadType, registry);
        this.jvmMemPoolsOldPeakMaxInBytes = jvmMemPoolsOldPeakMaxInBytes(uploadType, registry);
        this.jvmMemPoolsOldMaxInBytes = jvmMemPoolsOldMaxInBytes(uploadType, registry);
        this.jvmMemPoolsOldCommittedInBytes = jvmMemPoolsOldCommittedInBytes(uploadType, registry);
        this.jvmGcCollectorsOldCollectionCount = jvmGcCollectorsOldCollectionCount(uploadType, registry);
        this.jvmGcCollectorsOldCollectionTimeInMillis = jvmGcCollectorsOldCollectionTimeInMillis(uploadType, registry);
        this.jvmGcCollectorsYoungCollectionCount = jvmGcCollectorsYoungCollectionCount(uploadType, registry);
        this.jvmGcCollectorsYoungCollectionTimeInMillis = jvmGcCollectorsYoungCollectionTimeInMillis(uploadType, registry);
        this.jvmUptimeMillis = jvmUptimeMillis(uploadType, registry);
        this.jvmMemPoolsYoungPeakUsedInBytes = jvmMemPoolsYoungPeakUsedInBytes(uploadType, registry);
    }

    public MetricGauge jvmThreadCounts(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_THREADS_COUNT.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_THREADS_COUNT.getValue(), uploadType).create().register(registry);
    }

    public MetricGauge jvmThreadPeakCounts(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_THREADS_PEAK_COUNT.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_THREADS_PEAK_COUNT.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemHeapUsedPercent(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_USED_PERCENT.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_USED_PERCENT.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemHeapCommittedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_COMMITTED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_COMMITTED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemHeapMaxInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_MAX_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_MAX_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemHeapUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_HEAP_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemNonHeapUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_NON_HEAP_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_NON_HEAP_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemNonHeapCommittedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_NON_HEAP_COMMITTED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_NON_HEAP_COMMITTED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsSurvivorUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsSurvivorPeakUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_PEAK_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_PEAK_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsSurvivorPeakMaxInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_PEAK_MAX_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_PEAK_MAX_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsSurvivorMaxInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_MAX_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_MAX_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsSurvivorCommittedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_COMMITTED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_SURVIVOR_COMMITTED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsYoungUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsYoungPeakUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_PEAK_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_PEAK_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsYoungPeakMaxInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_PEAK_MAX_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_PEAK_MAX_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsYoungMaxInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_MAX_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_MAX_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsYoungCommittedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_COMMITTED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_YOUNG_COMMITTED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsOldUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsOldPeakUsedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_PEAK_USED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_PEAK_USED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsOldPeakMaxInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_PEAK_MAX_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_PEAK_MAX_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsOldMaxInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_MAX_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_MAX_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmMemPoolsOldCommittedInBytes(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_COMMITTED_IN_BYTES.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_MEM_POOLS_OLD_COMMITTED_IN_BYTES.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmGcCollectorsOldCollectionCount(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_OLD_COLLECTION_COUNT.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_OLD_COLLECTION_COUNT.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmGcCollectorsOldCollectionTimeInMillis(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_OLD_COLLECTION_TIME_IN_MILLIS.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_OLD_COLLECTION_TIME_IN_MILLIS.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmGcCollectorsYoungCollectionCount(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_YOUNG_COLLECTION_COUNT.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_YOUNG_COLLECTION_COUNT.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmGcCollectorsYoungCollectionTimeInMillis(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_YOUNG_COLLECTION_TIME_IN_MILLIS.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_GC_COLLECTORS_YOUNG_COLLECTION_TIME_IN_MILLIS.getValue(), uploadType).create().register(registry);

    }

    public MetricGauge jvmUptimeMillis(String uploadType, MetricCollectorRegistry registry) {
        return LogstashMetricsUtils.getGaugeMetricsBuilder(
                LogstashJvmMetricsConstants.Metrics.JVM_UPTIME_MILLIS.getName(),
                LogstashJvmMetricsConstants.Metrics.JVM_UPTIME_MILLIS.getValue(), uploadType).create().register(registry);

    }
    public void cleanMetrics() {
        this.jvmThreadCounts.clear();
        this.jvmThreadPeakCounts.clear();
        this.jvmMemHeapUsedPercent.clear();
        this.jvmMemHeapCommittedInBytes.clear();
        this.jvmMemHeapMaxInBytes.clear();
        this.jvmMemHeapUsedInBytes.clear();
        this.jvmMemNonHeapUsedInBytes.clear();
        this.jvmMemNonHeapCommittedInBytes.clear();
        this.jvmMemPoolsSurvivorUsedInBytes.clear();
        this.jvmMemPoolsSurvivorPeakUsedInBytes.clear();
        this.jvmMemPoolsSurvivorPeakMaxInBytes.clear();
        this.jvmMemPoolsSurvivorMaxInBytes.clear();
        this.jvmMemPoolsSurvivorCommittedInBytes.clear();
        this.jvmMemPoolsYoungUsedInBytes.clear();
        this.jvmMemPoolsYoungPeakMaxInBytes.clear();
        this.jvmMemPoolsYoungMaxInBytes.clear();
        this.jvmMemPoolsYoungCommittedInBytes.clear();
        this.jvmMemPoolsOldUsedInBytes.clear();
        this.jvmMemPoolsOldPeakUsedInBytes.clear();
        this.jvmMemPoolsOldPeakMaxInBytes.clear();
        this.jvmMemPoolsOldMaxInBytes.clear();
        this.jvmMemPoolsOldCommittedInBytes.clear();
        this.jvmGcCollectorsOldCollectionCount.clear();
        this.jvmGcCollectorsOldCollectionTimeInMillis.clear();
        this.jvmGcCollectorsYoungCollectionCount.clear();
        this.jvmGcCollectorsYoungCollectionTimeInMillis.clear();
        this.jvmUptimeMillis.clear();
        this.jvmMemPoolsYoungPeakUsedInBytes.clear();

    }
}
