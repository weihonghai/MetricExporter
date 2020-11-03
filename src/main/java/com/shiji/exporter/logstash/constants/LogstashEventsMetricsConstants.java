package com.shiji.exporter.logstash.constants;

public class LogstashEventsMetricsConstants {

    public static enum Metrics{

        EVENTS_DURATION_IN_MILLIS("events_duration_in_millis","The total process duration time in millis seconds"),
        EVENTS_FILTERED("events_filtered","The total numbers of filtered"),
        EVENTS_IN("events_in","The total number of events in"),
        EVENTS_OUT("events_out","The total number of events out"),
        EVENTS_QUEUE_PUSH_DURATION_IN_MILLIS("events_queue_push_duration_in_millis","The total in queue duration time in millis seconds");

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
