package com.shiji.exporter.logstash.constants;

public class LogstashProcessMetricsConstants {

    public static enum Metrics{

        PROCESS_MAX_FILE_DESCRIPTORS("process_max_file_descriptors","Max file descriptors"),
        PROCESS_OPEN_FILE_DESCRIPTORS("process_open_file_descriptors","Current open file descriptors"),
        PROCESS_PEAK_OPEN_FILE_DESCRIPTORS("process_peak_open_file_descriptors","Current peak open file descriptors"),
        PROCESS_CPU_LOAD_AVERAGE_15M("process_cpu_load_average_15m","Avg CPU load for 15m."),
        PROCESS_CPU_LOAD_AVERAGE_5M("process_cpu_load_average_5m","Avg CPU load for 5m."),
        PROCESS_CPU_LOAD_AVERAGE_1M("process_cpu_load_average_1m","Avg CPU load for 1m."),
        PROCESS_MEM_TOTAL_VIRTUAL_IN_BYTES("process_mem_total_virtual_in_bytes","Was the used virtual memory.");


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
