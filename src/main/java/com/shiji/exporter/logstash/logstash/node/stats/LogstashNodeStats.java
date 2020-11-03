package com.shiji.exporter.logstash.logstash.node.stats;

import com.alibaba.fastjson.JSONObject;
import com.shiji.exporter.logstash.logstash.node.stats.events.Events;
import com.shiji.exporter.logstash.logstash.node.stats.jvm.JVM;
import com.shiji.exporter.logstash.logstash.node.stats.pipeline.Pipeline;
import com.shiji.exporter.logstash.logstash.node.stats.pipelines.Pipelines;
import com.shiji.exporter.logstash.logstash.node.stats.queue.Queue;
import com.shiji.exporter.logstash.logstash.node.stats.reloads.Reloads;
import com.shiji.exporter.logstash.logstash.node.stats.process.Process;
import com.shiji.exporter.logstash.util.DoubleUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@NoArgsConstructor
@Data
public class LogstashNodeStats {

    private String host;
    private String version;
    private String http_address;
    private String id;
    private String name;
    private String ephemeral_id;
    private String status;
    private boolean snapshot;
    private Pipeline pipeline;
    private JVM jvm;
    private Process process;
    private Events events;
    private Reloads reloads;
    private String os;
    private Queue queue;
    private String pipelines;
    private Pipelines pipelinesObj;

    public static void main(String[] args) {
        String str = "{\n" +
                "  \"host\" : \"LAPTOP-PHNU0LVV\",\n" +
                "  \"version\" : \"7.4.2\",\n" +
                "  \"http_address\" : \"10.15.20.99:9600\",\n" +
                "  \"id\" : \"6aab3104-38f6-40b6-bb89-1e2d49ac1679\",\n" +
                "  \"name\" : \"LAPTOP-PHNU0LVV\",\n" +
                "  \"ephemeral_id\" : \"dcad4416-a2ab-40fe-9f8e-3546906ed142\",\n" +
                "  \"status\" : \"green\",\n" +
                "  \"snapshot\" : false,\n" +
                "  \"pipeline\" : {\n" +
                "    \"workers\" : 12,\n" +
                "    \"batch_size\" : 125,\n" +
                "    \"batch_delay\" : 50\n" +
                "  },\n" +
                "  \"jvm\" : {\n" +
                "    \"threads\" : {\n" +
                "      \"count\" : 25,\n" +
                "      \"peak_count\" : 27\n" +
                "    },\n" +
                "    \"mem\" : {\n" +
                "      \"heap_used_percent\" : 20,\n" +
                "      \"heap_committed_in_bytes\" : 1037959168,\n" +
                "      \"heap_max_in_bytes\" : 1037959168,\n" +
                "      \"heap_used_in_bytes\" : 209634256,\n" +
                "      \"non_heap_used_in_bytes\" : 164954328,\n" +
                "      \"non_heap_committed_in_bytes\" : 187482112,\n" +
                "      \"pools\" : {\n" +
                "        \"survivor\" : {\n" +
                "          \"used_in_bytes\" : 16839728,\n" +
                "          \"max_in_bytes\" : 35782656,\n" +
                "          \"peak_used_in_bytes\" : 35782656,\n" +
                "          \"peak_max_in_bytes\" : 35782656,\n" +
                "          \"committed_in_bytes\" : 35782656\n" +
                "        },\n" +
                "        \"old\" : {\n" +
                "          \"used_in_bytes\" : 141493584,\n" +
                "          \"max_in_bytes\" : 715849728,\n" +
                "          \"peak_used_in_bytes\" : 252397376,\n" +
                "          \"peak_max_in_bytes\" : 715849728,\n" +
                "          \"committed_in_bytes\" : 715849728\n" +
                "        },\n" +
                "        \"young\" : {\n" +
                "          \"used_in_bytes\" : 51300944,\n" +
                "          \"max_in_bytes\" : 286326784,\n" +
                "          \"peak_used_in_bytes\" : 286326784,\n" +
                "          \"peak_max_in_bytes\" : 286326784,\n" +
                "          \"committed_in_bytes\" : 286326784\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"gc\" : {\n" +
                "      \"collectors\" : {\n" +
                "        \"old\" : {\n" +
                "          \"collection_count\" : 4,\n" +
                "          \"collection_time_in_millis\" : 609\n" +
                "        },\n" +
                "        \"young\" : {\n" +
                "          \"collection_count\" : 10,\n" +
                "          \"collection_time_in_millis\" : 304\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"uptime_in_millis\" : 925795\n" +
                "  },\n" +
                "  \"process\" : {\n" +
                "    \"open_file_descriptors\" : -1,\n" +
                "    \"peak_open_file_descriptors\" : -1,\n" +
                "    \"max_file_descriptors\" : -1,\n" +
                "    \"mem\" : {\n" +
                "      \"total_virtual_in_bytes\" : -1\n" +
                "    },\n" +
                "    \"cpu\" : {\n" +
                "      \"total_in_millis\" : -1,\n" +
                "      \"percent\" : -3,\n" +
                "      \"load_average\": {\n" +
                "        \"1m\": 0.73,\n" +
                "        \"5m\": 1.13,\n" +
                "        \"15m\": 1.06\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"events\" : {\n" +
                "    \"in\" : 887,\n" +
                "    \"filtered\" : 883,\n" +
                "    \"out\" : 883,\n" +
                "    \"duration_in_millis\" : 899701,\n" +
                "    \"queue_push_duration_in_millis\" : 900121\n" +
                "  },\n" +
                "  \"pipelines\" : {\n" +
                "    \"test\" : {\n" +
                "      \"events\" : {\n" +
                "        \"duration_in_millis\" : 0,\n" +
                "        \"out\" : 0,\n" +
                "        \"queue_push_duration_in_millis\" : 0,\n" +
                "        \"filtered\" : 0,\n" +
                "        \"in\" : 1\n" +
                "      },\n" +
                "      \"plugins\" : {\n" +
                "        \"inputs\" : [ {\n" +
                "          \"id\" : \"7334b34b630b17f743f8922844342bf7bbc97ab1094a9c2b46611910dbf93a37\",\n" +
                "          \"events\" : {\n" +
                "            \"out\" : 1,\n" +
                "            \"queue_push_duration_in_millis\" : 0\n" +
                "          },\n" +
                "          \"name\" : \"generator\"\n" +
                "        } ],\n" +
                "        \"codecs\" : [ {\n" +
                "          \"id\" : \"plain_32a5bacc-105e-4f37-a942-0b10f328f87d\",\n" +
                "          \"decode\" : {\n" +
                "            \"duration_in_millis\" : 0,\n" +
                "            \"out\" : 0,\n" +
                "            \"writes_in\" : 0\n" +
                "          },\n" +
                "          \"encode\" : {\n" +
                "            \"duration_in_millis\" : 0,\n" +
                "            \"writes_in\" : 0\n" +
                "          },\n" +
                "          \"name\" : \"plain\"\n" +
                "        }, {\n" +
                "          \"id\" : \"plain_7f231fad-e69a-4d10-a65f-7ac1c01ae249\",\n" +
                "          \"decode\" : {\n" +
                "            \"duration_in_millis\" : 0,\n" +
                "            \"out\" : 1,\n" +
                "            \"writes_in\" : 1\n" +
                "          },\n" +
                "          \"encode\" : {\n" +
                "            \"duration_in_millis\" : 0,\n" +
                "            \"writes_in\" : 0\n" +
                "          },\n" +
                "          \"name\" : \"plain\"\n" +
                "        } ],\n" +
                "        \"filters\" : [ {\n" +
                "          \"id\" : \"0c00975d5d8964e1b74e4114b7d73f38c2d4e3eaf876e83103b64912a9519e25\",\n" +
                "          \"events\" : {\n" +
                "            \"duration_in_millis\" : 1001,\n" +
                "            \"out\" : 1,\n" +
                "            \"in\" : 1\n" +
                "          },\n" +
                "          \"name\" : \"sleep\"\n" +
                "        } ],\n" +
                "        \"outputs\" : [ {\n" +
                "          \"id\" : \"6a1bb5c7e92fcc87a75578374421861345ea859be1b1b9ea2ee0bb9fd4445cd0\",\n" +
                "          \"events\" : {\n" +
                "            \"duration_in_millis\" : 0,\n" +
                "            \"out\" : 0,\n" +
                "            \"in\" : 1\n" +
                "          },\n" +
                "          \"name\" : \"elasticsearch\"\n" +
                "        } ]\n" +
                "      },\n" +
                "      \"reloads\" : {\n" +
                "        \"last_error\" : null,\n" +
                "        \"successes\" : 0,\n" +
                "        \"last_success_timestamp\" : null,\n" +
                "        \"failures\" : 0,\n" +
                "        \"last_failure_timestamp\" : null\n" +
                "      },\n" +
                "      \"queue\" : {\n" +
                "        \"type\" : \"persisted\",\n" +
                "        \"events_count\" : 3715371,\n" +
                "        \"queue_size_in_bytes\" : 1073742527,\n" +
                "        \"max_queue_size_in_bytes\" : 1073741824\n" +
                "      },\n" +
                "      \"dead_letter_queue\" : {\n" +
                "        \"queue_size_in_bytes\" : 1\n" +
                "      },\n" +
                "      \"hash\" : \"be0a2742607412952e3d807b106ae50601e28fc2db3e0e58bce58580e8744f7a\",\n" +
                "      \"ephemeral_id\" : \"e7a2e24b-4cff-4349-8dff-97a6f68fa874\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"reloads\" : {\n" +
                "    \"successes\" : 0,\n" +
                "    \"failures\" : 0\n" +
                "  },\n" +
                "  \"os\" : { },\n" +
                "  \"queue\" : {\n" +
                "    \"events_count\" : 3715371\n" +
                "  }\n" +
                "}\n";
        LogstashNodeStats stats = JSONObject.parseObject(str,LogstashNodeStats.class);
        if(StringUtils.hasText(str)){
            stats = JSONObject.parseObject(str,LogstashNodeStats.class);
            if(null != stats && StringUtils.hasText(stats.getPipelines())){
                JSONObject obj = JSONObject.parseObject(stats.getPipelines());
                obj.forEach((k,v)->{
                    System.out.println(JSONObject.parseObject(v.toString(), Pipelines.class));
                });


            }
        }
        System.out.println(stats.getProcess().getCpu().getLoad_average().get_1m());

        System.out.println(DoubleUtils.getDoubleFormat(609/1000.0));
    }
}
