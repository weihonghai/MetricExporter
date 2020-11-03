package com.shiji.exporter.logstash.constants;

public class LogstashJvmMetricsConstants {

    public static enum Metrics {
        JVM_THREADS_COUNT("jvm_threads_count", "Current JVM thread count."),
        JVM_THREADS_PEAK_COUNT("jvm_threads_peak_count", "Current JVM thread count."),
        JVM_UPTIME_MILLIS("jvm_uptime_millis", "Current JVM uptime millis."),
        OLD("old","old"),
        YOUNG("yong","yong"),
        SURVIVOR("survivor","survivor"),
        HEAP("heap_","heap"),
        NO_HEAP("no_heap_","no_heap"),
        JVM_MEM("jvm_mem_","jvm_mem"),
        JVM_MEM_POOLS("jvm_mem_pools_","jvm_mem_pools"),
        MAX_IN_BYTES("max_in_bytes","max_in_bytes"),
        COMMITTED_IN_BYTES("committed_in_bytes","committed_in_bytes"),
        USED_IN_BYTES("used_in_bytes","used_in_bytes"),
        PEAK("peak_","peak"),
        JVM_GC_COLLECTORS("collectors_","collectors"),
        COLLECTION_TIME_IN_MILLIS("collection_time_in_millis","collection_time_in_millis"),
        COLLECTION_COUNT("collection_count","collection_count"),

        JVM_MEM_POOLS_OLD_COMMITTED_IN_BYTES("jvm_mem_pools_old_committed_in_bytes","Current JVM  heap pool old area committed size"),
        JVM_MEM_POOLS_OLD_MAX_IN_BYTES("jvm_mem_pools_old_max_in_bytes","Current JVM  heap pool old area max size"),
        JVM_MEM_POOLS_OLD_PEAK_MAX_IN_BYTES("jvm_mem_pools_old_peak_max_in_bytes","Current JVM  heap pool old area peak max size"),
        JVM_MEM_POOLS_OLD_PEAK_USED_IN_BYTES("jvm_mem_pools_old_peak_used_in_bytes","Current JVM  heap pool old area peak used size"),
        JVM_MEM_POOLS_OLD_USED_IN_BYTES("jvm_mem_pools_old_used_in_bytes","Current JVM  heap pool old area used size"),

        JVM_MEM_POOLS_YOUNG_COMMITTED_IN_BYTES("jvm_mem_pools_young_committed_in_bytes","Current JVM  heap pool young area committed size"),
        JVM_MEM_POOLS_YOUNG_MAX_IN_BYTES("jvm_mem_pools_young_max_in_bytes","Current JVM  heap pool young area max size"),
        JVM_MEM_POOLS_YOUNG_PEAK_MAX_IN_BYTES("jvm_mem_pools_young_peak_max_in_bytes","Current JVM  heap pool young area peak max size"),
        JVM_MEM_POOLS_YOUNG_PEAK_USED_IN_BYTES("jvm_mem_pools_young_peak_used_in_bytes","Current JVM  heap pool young area peak used size"),
        JVM_MEM_POOLS_YOUNG_USED_IN_BYTES("jvm_mem_pools_young_used_in_bytes","Current JVM  heap pool young area used size"),

        JVM_MEM_POOLS_SURVIVOR_COMMITTED_IN_BYTES("jvm_mem_pools_survivor_committed_in_bytes","Current JVM  heap pool survivor area committed size"),
        JVM_MEM_POOLS_SURVIVOR_MAX_IN_BYTES("jvm_mem_pools_survivor_max_in_bytes","Current JVM  heap pool survivor area max size"),
        JVM_MEM_POOLS_SURVIVOR_PEAK_MAX_IN_BYTES("jvm_mem_pools_survivor_peak_max_in_bytes","Current JVM  heap pool survivor area peak max size"),
        JVM_MEM_POOLS_SURVIVOR_PEAK_USED_IN_BYTES("jvm_mem_pools_survivor_peak_used_in_bytes","Current JVM  heap pool survivor area peak used size"),
        JVM_MEM_POOLS_SURVIVOR_USED_IN_BYTES("jvm_mem_pools_survivor_used_in_bytes","Current JVM  heap pool survivor area used size"),


        JVM_MEM_HEAP_USED_PERCENT("jvm_mem_heap_used_percent","Current JVM  heap used percent."),
        JVM_MEM_HEAP_COMMITTED_IN_BYTES("jvm_mem_heap_committed_in_bytes","Current JVM  heap committed size."),
        JVM_MEM_HEAP_MAX_IN_BYTES("jvm_mem_heap_max_in_bytes","Current JVM  heap max size."),
        JVM_MEM_HEAP_USED_IN_BYTES("jvm_mem_heap_used_in_bytes","Current JVM  heap used size."),
        JVM_MEM_NON_HEAP_USED_IN_BYTES("jvm_mem_non_heap_used_in_bytes","Current JVM  no heap used size."),
        JVM_MEM_NON_HEAP_COMMITTED_IN_BYTES("jvm_mem_non_heap_committed_in_bytes","Current JVM  no heap committed size."),

        JVM_GC_COLLECTORS_OLD_COLLECTION_COUNT("jvm_gc_collectors_old_collection_count","GC collection count for old area."),
        JVM_GC_COLLECTORS_OLD_COLLECTION_TIME_IN_MILLIS("jvm_gc_collectors_old_collection_time_in_millis","GC collection duration time millis for old area."),
        JVM_GC_COLLECTORS_YOUNG_COLLECTION_COUNT("jvm_gc_collectors_young_collection_count","GC collection count for young area."),
        JVM_GC_COLLECTORS_YOUNG_COLLECTION_TIME_IN_MILLIS("jvm_gc_collectors_young_collection_time_in_millis","GC collection duration time millis for young area."),


        gc_collection_duration_seconds_total("gc_collection_duration_seconds_total","GC collection duration seconds"),
        gc_collection_total("gc_collection_total","GC collection total")

        ;

        private Metrics(String name, String value) {
            this.value = value;
            this.name = name;
        }

        private final String value;
        private final String name;

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}