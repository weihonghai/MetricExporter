package com.shiji.exporter.logstash.constants;

public class LogstashPipelinesMetricsConstants {

    public static enum Metrics {
//        PIPELINES_EVENTS_DURATION_SECONDS_TOTAL("pipelines_events_duration_seconds_total", "The total process duration time in seconds."),
//        PIPELINES_EVENTS_IN_TOTAL("pipelines_events_in_total", "The total number of events in."),
//        PIPELINES_EVENTS_FILTERED_TOTAL("pipelines_events_filtered_total","The total numbers of filtered."),
//        PIPELINES_EVENTS_OUT_TOTAL("pipelines_events_out_total","The total number of events out."),
//        PIPELINES_EVENTS_QUEUE_PUSH_DURATION_SECONDS_TOTAL("pipelines_events_queue_push_duration_seconds_total", "The total in queue duration time in seconds.");



        PIPELINES_EVENTS_DURATION_IN_MILLIS("pipelines_events_duration_in_millis","The total process duration time in millis seconds of this pipeline"),
        PIPELINES_EVENTS_FILTERED("pipelines_events_filtered","The total numbers of filtered of this pipeline"),
        PIPELINES_EVENTS_IN("pipelines_events_in","The total number of events in of this pipeline"),
        PIPELINES_EVENTS_OUT("pipelines_events_out","The total number of events out of this pipeline"),
        PIPELINES_EVENTS_QUEUE_PUSH_DURATION_IN_MILLIS("pipelines_events_queue_push_duration_in_millis","The total in queue duration time in millis seconds of this pipeline"),

//        PIPELINES_PLUGINS_FILTERS_EVENTS_DURATION_IN_MILLIS("pipelines_plugins_filters_events_duration_in_millis","The total process duration time in millis seconds of this pipeline of this filter plugins"),
//        PIPELINES_PLUGINS_FILTERS_EVENTS_IN("pipelines_plugins_filters_events_in","The total number of events in of this pipeline"),
//        PIPELINES_PLUGINS_FILTERS_EVENTS_OUT("pipelines_plugins_filters_events_out","The total number of events out of this filter plugins"),
//

        PIPELINES_PLUGINS_INPUTS_EVENTS_OUT("pipelines_plugins_inputs_events_out","The total number of events out of input plugin"),
        PIPELINES_PLUGINS_INPUTS_QUEUE_PUSH_DURATION_IN_MILLIS("pipelines_plugins_inputs_queue_push_duration_in_millis","The total process duration time in millis seconds of events out of input plugin"),
        PIPELINE_PLUGINS_INPUTS_WORKERS("pipeline_plugins_inputs_works","The total works of  input plugin"),
        PIPELINE_PLUGINS_INPUTS_QUEUE_SIZE("pipeline_plugins_inputs_queue_size","The total queue size of  input plugin"),

        PIPELINES_PLUGINS_OUTPUTS_EVENTS_DURATION_IN_MILLIS("pipelines_plugins_outputs_events_duration_in_millis","The total process duration time in millis seconds of events out of input plugin"),
        PIPELINES_PLUGINS_OUTPUTS_EVENTS_IN("pipelines_plugins_outputs_events_in","The total number of events in of input plugin"),
        PIPELINES_PLUGINS_OUTPUTS_EVENTS_OUT("pipelines_plugins_outputs_events_out","The total number of events out of input plugin"),

        PIPELINES_QUEUE_EVENTS_COUNT("pipelines_queue_events_count","The current events in queue of this pipeline"),
        PIPELINES_QUEUE_SIZE_IN_BYTES("pipelines_queue_size_in_bytes","The current queue size in bytes of this pipeline"),
        PIPELINES_QUEUE_MAX_QUEUE_SIZE_IN_BYTES("pipelines_queue_max_queue_size_in_bytes","The max queue size in bytes of this pipeline");


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