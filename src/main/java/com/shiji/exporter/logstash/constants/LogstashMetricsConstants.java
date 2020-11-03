package com.shiji.exporter.logstash.constants;

public class LogstashMetricsConstants {
    public static enum Metrics {
        METRICS_PREFIX("logstash_node_", "Prefix for metrics name "),
        INSTANCE("instance", "Prometheus tags,The IP and port of this java program"),
        APPLICATION("application", "Prometheus tags,The name of this java program"),
        UPLOAD_TYPE("upload_type", "Prometheus tags,value is pull or push"),
        TARGET_MONITOR_INSTANCE("target_monitor_instance", "Prometheus tags,The IP and port of the monitored instance"),
        TARGET_MONITOR_APPLICATION("target_monitor_application", "Prometheus tags,The name of the monitored application"),
        PUSH_GATE_WAY_INSTANCE("push_gate_way_instance", "Prometheus tags,The IP and port of the pushgateway"),
        PUSH_GATE_WAY_APPLICATION("push_gate_way_application", "Prometheus tags,The name of the pushgateway"),
        LOGSTASH_PIPELINES("logstash_pipelines", "Logstash pipeline name"),
        ENV("env", "Application deployment environment"),
        SOC_DEPLOY_CHANNEL("soc_deploy_channel","soc_deploy_channel"),
        SOC_PARTNER("soc_partner", "soc_partner"),
        SOC_SOLUTION_NAME("soc_solution_name","soc_solution_name"),
        SOC_SOLUTION_MONITOR_INSTANCE("soc_monitor_instance","soc_monitor_instance"),
        SOC_SOLUTION_MONITOR_INSTANCE_NAME("soc_monitor_instance_name","soc_monitor_instance_name"),
        LOGSTASH_PROMETHEUS_EXPORT("logstash-prometheus-export", "logstash-prometheus-export"),
        PIPELINE("pipeline", "pipeline"),
        PIPELINE_QUEUE_TYPE("queue_type", "queue type:memory or persisted"),
        PLUGIN_ID("plugin_id","plugin id"),
        NAME("name","name"),
        UPLOAD_PUSH("push", "push"),
        UPLOAD_PULL("pull", "pull"),
        LOGSTASH_VERSION("logstash_version", "logstash_version"),
        VIEW_BOARD("view_board", "tags ,show on grafana"),
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
