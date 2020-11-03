package com.shiji.exporter.logstash.controller;//package com.shiji.soc.prometheus.export.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.shiji.soc.prometheus.export.clinet.SocCollector;
//import com.shiji.soc.prometheus.export.clinet.SocCollectorRegistry;
//import com.shiji.soc.prometheus.export.clinet.SocTextFormat;
//import com.shiji.soc.prometheus.export.logstash.node.info.LogstashNodeInfo;
//import com.shiji.soc.prometheus.export.logstash.node.info.SocLogstashInfos;
//import com.shiji.soc.prometheus.export.logstash.node.stats.LogstashNodeStats;
//import com.shiji.soc.prometheus.export.service.LogstashPipelinesService;
//import io.micrometer.core.instrument.MeterRegistry;
//import io.micrometer.prometheus.PrometheusMeterRegistry;
//import io.prometheus.client.CollectorRegistry;
//import io.prometheus.client.Counter;
//import io.prometheus.client.Gauge;
//import io.prometheus.client.exporter.PushGateway;
//import io.prometheus.client.exporter.common.TextFormat;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.StringWriter;
//import java.text.DecimalFormat;
//import java.util.*;
//
//@RestController
//@CrossOrigin
////@RequestMapping("/soc/prometheus/logstash/metrics")
//@RequestMapping("/monitor")
//@Api(value = "SOC Logstash Metrics Monitor Endpoint For Pull Mode")
//public class LogstashMetricsController {
//
////    @Autowired
////    private Gauge jvmThreadsPeakCount;
////
////    @Autowired
////    private Counter jvmThreadsCount;
//
//    @Value("${spring.application.name}")
//    private String applicationName;
//    @Value("${spring.application.ip}")
//    private String ip;
//    @Value("${server.port}")
//    private String port;
//
//    @Value("${soc.upload-type}")
//    private String uploadType;
//
//    @Value("${soc.logstash-server.logstash-1.name}")
//    private String logstash1Name;
//    @Value("${soc.logstash-server.logstash-1.ip}")
//    private String logstash1IP;
//    @Value("${soc.logstash-server.logstash-1.port}")
//    private String logstash1Port;
//    @Value("${soc.logstash-server.logstash-2.name}")
//    private String logstash2Name;
//    @Value("${soc.logstash-server.logstash-2.ip}")
//    private String logstash2IP;
//    @Value("${soc.logstash-server.logstash-2.port}")
//    private String logstash2Port;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
////    @Autowired
////    private LogstashPipelinesService logstashPipelinesService;
////
////    @Autowired
////    PrometheusMeterRegistry prometheusMeterRegistry;
//
//    @ApiOperation(value = "logstash-1 node stats", nickname = "")
//    @GetMapping(value = "/logstash-1", produces = {"application/json;charset=UTF-8"})
//    public void exportMetrics1(HttpServletResponse response) {
//        StringWriter writer = new StringWriter();
//        if (StringUtils.hasText(logstash1IP) && StringUtils.hasText(logstash1Name) && StringUtils.hasText(logstash1Port) && StringUtils.hasText(uploadType)){
//            String type = "pull";
//            if ("push".equalsIgnoreCase(uploadType)){
//                writer.write("logstash-1 upload-type is push!!!");
//            }
//            Random random = new Random();
//            int ran1 = random.nextInt(100);
//            Double a = ran1 * 2.31233321d;
////            Double x = jvmThreadsCount.labels(ip+":"+port, applicationName, type, logstash1IP+":"+logstash1Port, logstash1Name).get();
//            System.out.println(a);
////            System.out.println(x);
////            System.out.println(a-x);
//            DecimalFormat df = new DecimalFormat("#.00");
////            jvmThreadsCount.labels(ip+":"+port, applicationName, type, logstash1IP+":"+logstash1Port, logstash1Name).inc(Double.valueOf(df.format(a-x)));
//            try {
//                TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
//            } catch (IOException var3) {
//                throw new RuntimeException(var3);
//            }
//        }else {
//            writer.write("Please config logstash-1 in application.yml!!!");
//        }
//        String response2 = writer.toString();
//        try (OutputStream os = response.getOutputStream()) {
//            os.write(response2.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @ApiOperation(value = "logstash-2 node stats", nickname = "")
//    @GetMapping(value = "/logstash-2", produces = {"application/json;charset=UTF-8"})
//    public void exportMetrics2(HttpServletResponse response) {
//        StringWriter writer = new StringWriter();
//        if (StringUtils.hasText(logstash2IP) && StringUtils.hasText(logstash2Name) && StringUtils.hasText(logstash2Port)){
//            String type = "pull";
//            if ("push".equalsIgnoreCase(uploadType)){
//                writer.write("logstash-2 upload-type is push!!!");
//            }
//            Random random = new Random();
//            int ran1 = random.nextInt(100);
//            Double a = (1.0d * ran1);
////            jvmThreadsCount.labels(ip+":"+port, applicationName, type, logstash2IP+":"+logstash2Port, logstash2Name).set(a);
//            try {
//                TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
//            } catch (IOException var3) {
//                throw new RuntimeException(var3);
//            }
//        }else{
//            writer.write("Please config logstash-2 in application.yml!!!");
//        }
//        String response2 = writer.toString();
//        try (OutputStream os = response.getOutputStream()) {
//            os.write(response2.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @ApiOperation(value = "push", nickname = "")
//    @GetMapping(value = "/push", produces = {"application/json;charset=UTF-8"})
//    public void ta() throws IOException {
//        CollectorRegistry registry = CollectorRegistry.defaultRegistry;
//        PushGateway pg = new PushGateway("127.0.0.1:9091");
//        pg.pushAdd(registry, "my_batch_job");
//    }
//
//    @ApiOperation(value = "pushPrometheus", nickname = "")
//    @GetMapping(value = "/pushPrometheus", produces = {"application/json;charset=UTF-8"})
//    public void taa() throws IOException {
//        PushGateway pg = new PushGateway("127.0.0.1:9091");
//        pg.pushAdd(CollectorRegistry.defaultRegistry, "pushPrometheus");
//    }
//
//    @Autowired
//    private Map<Integer, SocCollectorRegistry> logstashBeanMap;
//
//    @ApiOperation(value = "prometheus", nickname = "")
//    @GetMapping(value = "/prometheus", produces = {"application/json;charset=UTF-8"})
//    public void taadaa(HttpServletResponse response) throws IOException {
//        StringWriter writer = new StringWriter();
//        try {
//            List<Enumeration<SocCollector.MetricFamilySamples>> list = new ArrayList<>();
//            for (Integer i : logstashBeanMap.keySet()){
//                list.add(logstashBeanMap.get(i).metricFamilySamples());
//            }
//            SocTextFormat.write004(writer, list);
////            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
//        } catch (IOException var3) {
//            throw new RuntimeException(var3);
//        }
//        String response2 = writer.toString();
//        try (OutputStream os = response.getOutputStream()) {
//            os.write(response2.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
////    @ApiOperation(value = "nodeInfo/os", nickname = "")
////    @GetMapping(value = "/nodeInfo/os", produces = {"application/json;charset=UTF-8"})
////    public void getNodeInfoOs(HttpServletResponse response) throws IOException {
////        StringWriter writer = new StringWriter();
////        String result = this.restTemplate.getForObject("http://"+logstash1IP+":"+logstash1Port+"/_node/os",String.class);
////        writer.append(result);
////        String response2 = writer.toString();
////        try (OutputStream os = response.getOutputStream()) {
////            os.write(response2.getBytes());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//    @ApiOperation(value = "nodeInfo", nickname = "")
//    @GetMapping(value = "/nodeInfo", produces = {"application/json;charset=UTF-8"})
//    public SocLogstashInfos getNodeInfoPipelines(HttpServletResponse response) throws IOException {
//        String result = this.restTemplate.getForObject("http://"+logstash1IP+":"+logstash1Port+"/_node",String.class);
//        SocLogstashInfos infos = new SocLogstashInfos();
//        if (StringUtils.hasText(result)){
//            LogstashNodeInfo logstash1 = JSONObject.parseObject(result,LogstashNodeInfo.class);
//            infos.setLogstash1(logstash1);
//        }
//        return infos;
//    }
//
//    @ApiOperation(value = "aaa", nickname = "")
//    @GetMapping(value = "/aa", produces = {"application/json;charset=UTF-8"})
//    public void aa(HttpServletResponse response) throws Exception {
//        String str = "{\n" +
//                "  \"host\" : \"LAPTOP-PHNU0LVV\",\n" +
//                "  \"version\" : \"7.4.2\",\n" +
//                "  \"http_address\" : \"10.15.20.99:9600\",\n" +
//                "  \"id\" : \"6aab3104-38f6-40b6-bb89-1e2d49ac1679\",\n" +
//                "  \"name\" : \"LAPTOP-PHNU0LVV\",\n" +
//                "  \"ephemeral_id\" : \"3a74a5e4-d149-45cb-9f19-bab8d9e4d7ff\",\n" +
//                "  \"status\" : \"green\",\n" +
//                "  \"snapshot\" : false,\n" +
//                "  \"pipeline\" : {\n" +
//                "    \"workers\" : 12,\n" +
//                "    \"batch_size\" : 125,\n" +
//                "    \"batch_delay\" : 50\n" +
//                "  },\n" +
//                "  \"jvm\" : {\n" +
//                "    \"threads\" : {\n" +
//                "      \"count\" : 26,\n" +
//                "      \"peak_count\" : 26\n" +
//                "    },\n" +
//                "    \"mem\" : {\n" +
//                "      \"heap_used_percent\" : 23,\n" +
//                "      \"heap_committed_in_bytes\" : 1037959168,\n" +
//                "      \"heap_max_in_bytes\" : 1037959168,\n" +
//                "      \"heap_used_in_bytes\" : 244026752,\n" +
//                "      \"non_heap_used_in_bytes\" : 148389080,\n" +
//                "      \"non_heap_committed_in_bytes\" : 170561536,\n" +
//                "      \"pools\" : {\n" +
//                "        \"young\" : {\n" +
//                "          \"peak_max_in_bytes\" : 286326784,\n" +
//                "          \"peak_used_in_bytes\" : 286326784,\n" +
//                "          \"max_in_bytes\" : 286326784,\n" +
//                "          \"committed_in_bytes\" : 286326784,\n" +
//                "          \"used_in_bytes\" : 114751392\n" +
//                "        },\n" +
//                "        \"survivor\" : {\n" +
//                "          \"peak_max_in_bytes\" : 35782656,\n" +
//                "          \"peak_used_in_bytes\" : 35782656,\n" +
//                "          \"max_in_bytes\" : 35782656,\n" +
//                "          \"committed_in_bytes\" : 35782656,\n" +
//                "          \"used_in_bytes\" : 1111648\n" +
//                "        },\n" +
//                "        \"old\" : {\n" +
//                "          \"peak_max_in_bytes\" : 715849728,\n" +
//                "          \"peak_used_in_bytes\" : 252502584,\n" +
//                "          \"max_in_bytes\" : 715849728,\n" +
//                "          \"committed_in_bytes\" : 715849728,\n" +
//                "          \"used_in_bytes\" : 128163712\n" +
//                "        }\n" +
//                "      }\n" +
//                "    },\n" +
//                "    \"gc\" : {\n" +
//                "      \"collectors\" : {\n" +
//                "        \"young\" : {\n" +
//                "          \"collection_count\" : 72,\n" +
//                "          \"collection_time_in_millis\" : 448\n" +
//                "        },\n" +
//                "        \"old\" : {\n" +
//                "          \"collection_count\" : 3,\n" +
//                "          \"collection_time_in_millis\" : 482\n" +
//                "        }\n" +
//                "      }\n" +
//                "    },\n" +
//                "    \"uptime_in_millis\" : 59207\n" +
//                "  },\n" +
//                "  \"process\" : {\n" +
//                "    \"open_file_descriptors\" : -1,\n" +
//                "    \"peak_open_file_descriptors\" : -1,\n" +
//                "    \"max_file_descriptors\" : -1,\n" +
//                "    \"mem\" : {\n" +
//                "      \"total_virtual_in_bytes\" : -1\n" +
//                "    },\n" +
//                "    \"cpu\" : {\n" +
//                "      \"total_in_millis\" : -1,\n" +
//                "      \"percent\" : -3,\n" +
//                "      \"load_average\" : null\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"events\" : {\n" +
//                "    \"in\" : 2211193,\n" +
//                "    \"filtered\" : 39,\n" +
//                "    \"out\" : 39,\n" +
//                "    \"duration_in_millis\" : 39088,\n" +
//                "    \"queue_push_duration_in_millis\" : 46817\n" +
//                "  },\n" +
//                "  \"pipelines\" : {\n" +
//                "    \"test-1\" : {\n" +
//                "      \"events\" : {\n" +
//                "        \"in\" : 43,\n" +
//                "        \"filtered\" : 40,\n" +
//                "        \"queue_push_duration_in_millis\" : 40581,\n" +
//                "        \"duration_in_millis\" : 40088,\n" +
//                "        \"out\" : 40\n" +
//                "      },\n" +
//                "      \"plugins\" : {\n" +
//                "        \"inputs\" : [ {\n" +
//                "          \"id\" : \"f6e8f3a95fd74f5dad45178c9be8154d937351d81ecf9ce958f10793927707c9\",\n" +
//                "          \"events\" : {\n" +
//                "            \"queue_push_duration_in_millis\" : 40581,\n" +
//                "            \"out\" : 43\n" +
//                "          },\n" +
//                "          \"name\" : \"generator\"\n" +
//                "        } ],\n" +
//                "        \"codecs\" : [ {\n" +
//                "          \"id\" : \"plain_88aa447b-1640-4ce8-b73c-223872ba3269\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"plain\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 43,\n" +
//                "            \"duration_in_millis\" : 40612,\n" +
//                "            \"out\" : 43\n" +
//                "          }\n" +
//                "        }, {\n" +
//                "          \"id\" : \"dots_ee689d8b-9569-4492-be99-7fcacbebef21\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 40,\n" +
//                "            \"duration_in_millis\" : 4\n" +
//                "          },\n" +
//                "          \"name\" : \"dots\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0,\n" +
//                "            \"out\" : 0\n" +
//                "          }\n" +
//                "        } ],\n" +
//                "        \"filters\" : [ {\n" +
//                "          \"id\" : \"d2282d0eacdf348e91081a14be251548a723d16dce8504fa574604de64d908b2\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 41,\n" +
//                "            \"duration_in_millis\" : 40057,\n" +
//                "            \"out\" : 40\n" +
//                "          },\n" +
//                "          \"name\" : \"sleep\"\n" +
//                "        } ],\n" +
//                "        \"outputs\" : [ {\n" +
//                "          \"id\" : \"c9f6c3ccb5bd4fb92931e032e3568abe32409f881dbd5d312f846b7e28a741c4\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 40,\n" +
//                "            \"duration_in_millis\" : 10,\n" +
//                "            \"out\" : 40\n" +
//                "          },\n" +
//                "          \"name\" : \"stdout\"\n" +
//                "        } ]\n" +
//                "      },\n" +
//                "      \"reloads\" : {\n" +
//                "        \"failures\" : 0,\n" +
//                "        \"last_success_timestamp\" : null,\n" +
//                "        \"last_failure_timestamp\" : null,\n" +
//                "        \"last_error\" : null,\n" +
//                "        \"successes\" : 0\n" +
//                "      },\n" +
//                "      \"queue\" : {\n" +
//                "        \"type\" : \"memory\",\n" +
//                "        \"events_count\" : 0,\n" +
//                "        \"queue_size_in_bytes\" : 0,\n" +
//                "        \"max_queue_size_in_bytes\" : 0\n" +
//                "      },\n" +
//                "      \"hash\" : \"a3bf686e789a4fbb312192cf2f27667831f9c56b7c9394c1f2d8b933fe5c3ddc\",\n" +
//                "      \"ephemeral_id\" : \"6d0544c2-883f-4134-8210-92f6e73a809e\"\n" +
//                "    },\n" +
//                "    \"test\" : {\n" +
//                "      \"events\" : {\n" +
//                "        \"in\" : 2220585,\n" +
//                "        \"filtered\" : 0,\n" +
//                "        \"queue_push_duration_in_millis\" : 7269,\n" +
//                "        \"duration_in_millis\" : 0,\n" +
//                "        \"out\" : 0\n" +
//                "      },\n" +
//                "      \"plugins\" : {\n" +
//                "        \"inputs\" : [ {\n" +
//                "          \"id\" : \"7334b34b630b17f743f8922844342bf7bbc97ab1094a9c2b46611910dbf93a37\",\n" +
//                "          \"events\" : {\n" +
//                "            \"queue_push_duration_in_millis\" : 7269,\n" +
//                "            \"out\" : 2220592\n" +
//                "          },\n" +
//                "          \"name\" : \"generator\"\n" +
//                "        } ],\n" +
//                "        \"codecs\" : [ {\n" +
//                "          \"id\" : \"plain_aa19eaf0-ff4c-4dda-97b2-cadd9be33fd8\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"plain\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 2220601,\n" +
//                "            \"duration_in_millis\" : 7470,\n" +
//                "            \"out\" : 2220602\n" +
//                "          }\n" +
//                "        }, {\n" +
//                "          \"id\" : \"plain_d75fa63f-6c48-4b6e-8cb1-27ef8d58a31c\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"plain\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0,\n" +
//                "            \"out\" : 0\n" +
//                "          }\n" +
//                "        } ],\n" +
//                "        \"filters\" : [ {\n" +
//                "          \"id\" : \"0c00975d5d8964e1b74e4114b7d73f38c2d4e3eaf876e83103b64912a9519e25\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 1,\n" +
//                "            \"duration_in_millis\" : 1001,\n" +
//                "            \"out\" : 1\n" +
//                "          },\n" +
//                "          \"name\" : \"sleep\"\n" +
//                "        } ],\n" +
//                "        \"outputs\" : [ {\n" +
//                "          \"id\" : \"6a1bb5c7e92fcc87a75578374421861345ea859be1b1b9ea2ee0bb9fd4445cd0\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 1,\n" +
//                "            \"duration_in_millis\" : 0,\n" +
//                "            \"out\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"elasticsearch\"\n" +
//                "        } ]\n" +
//                "      },\n" +
//                "      \"reloads\" : {\n" +
//                "        \"failures\" : 0,\n" +
//                "        \"last_success_timestamp\" : null,\n" +
//                "        \"last_failure_timestamp\" : null,\n" +
//                "        \"last_error\" : null,\n" +
//                "        \"successes\" : 0\n" +
//                "      },\n" +
//                "      \"queue\" : {\n" +
//                "        \"type\" : \"persisted\",\n" +
//                "        \"events_count\" : 2072769,\n" +
//                "        \"queue_size_in_bytes\" : 599016378,\n" +
//                "        \"max_queue_size_in_bytes\" : 1073741824\n" +
//                "      },\n" +
//                "      \"hash\" : \"be0a2742607412952e3d807b106ae50601e28fc2db3e0e58bce58580e8744f7a\",\n" +
//                "      \"ephemeral_id\" : \"e3942fd7-34c8-456c-a18a-0bc0dfab840f\"\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"reloads\" : {\n" +
//                "    \"failures\" : 0,\n" +
//                "    \"successes\" : 0\n" +
//                "  },\n" +
//                "  \"os\" : { },\n" +
//                "  \"queue\" : {\n" +
//                "    \"events_count\" : 2072769\n" +
//                "  }\n" +
//                "}\n";
//        LogstashNodeStats stats = JSONObject.parseObject(str,LogstashNodeStats.class);
////        this.logstashPipelinesService.processPipelines(stats,"10.1.1.1","9600","logstash-1");
//    }
//
//
//    @ApiOperation(value = "bbb", nickname = "")
//    @GetMapping(value = "/bbb", produces = {"application/json;charset=UTF-8"})
//    public void bbb(HttpServletResponse response) throws Exception {
//        String str = "{\n" +
//                "  \"host\" : \"LAPTOP-PHNU0LVV\",\n" +
//                "  \"version\" : \"7.4.2\",\n" +
//                "  \"http_address\" : \"10.15.20.99:9600\",\n" +
//                "  \"id\" : \"6aab3104-38f6-40b6-bb89-1e2d49ac1679\",\n" +
//                "  \"name\" : \"LAPTOP-PHNU0LVV\",\n" +
//                "  \"ephemeral_id\" : \"3a74a5e4-d149-45cb-9f19-bab8d9e4d7ff\",\n" +
//                "  \"status\" : \"green\",\n" +
//                "  \"snapshot\" : false,\n" +
//                "  \"pipeline\" : {\n" +
//                "    \"workers\" : 12,\n" +
//                "    \"batch_size\" : 125,\n" +
//                "    \"batch_delay\" : 50\n" +
//                "  },\n" +
//                "  \"jvm\" : {\n" +
//                "    \"threads\" : {\n" +
//                "      \"count\" : 26,\n" +
//                "      \"peak_count\" : 26\n" +
//                "    },\n" +
//                "    \"mem\" : {\n" +
//                "      \"heap_used_percent\" : 23,\n" +
//                "      \"heap_committed_in_bytes\" : 1037959168,\n" +
//                "      \"heap_max_in_bytes\" : 1037959168,\n" +
//                "      \"heap_used_in_bytes\" : 244026752,\n" +
//                "      \"non_heap_used_in_bytes\" : 148389080,\n" +
//                "      \"non_heap_committed_in_bytes\" : 170561536,\n" +
//                "      \"pools\" : {\n" +
//                "        \"young\" : {\n" +
//                "          \"peak_max_in_bytes\" : 286326784,\n" +
//                "          \"peak_used_in_bytes\" : 286326784,\n" +
//                "          \"max_in_bytes\" : 286326784,\n" +
//                "          \"committed_in_bytes\" : 286326784,\n" +
//                "          \"used_in_bytes\" : 114751392\n" +
//                "        },\n" +
//                "        \"survivor\" : {\n" +
//                "          \"peak_max_in_bytes\" : 35782656,\n" +
//                "          \"peak_used_in_bytes\" : 35782656,\n" +
//                "          \"max_in_bytes\" : 35782656,\n" +
//                "          \"committed_in_bytes\" : 35782656,\n" +
//                "          \"used_in_bytes\" : 1111648\n" +
//                "        },\n" +
//                "        \"old\" : {\n" +
//                "          \"peak_max_in_bytes\" : 715849728,\n" +
//                "          \"peak_used_in_bytes\" : 252502584,\n" +
//                "          \"max_in_bytes\" : 715849728,\n" +
//                "          \"committed_in_bytes\" : 715849728,\n" +
//                "          \"used_in_bytes\" : 128163712\n" +
//                "        }\n" +
//                "      }\n" +
//                "    },\n" +
//                "    \"gc\" : {\n" +
//                "      \"collectors\" : {\n" +
//                "        \"young\" : {\n" +
//                "          \"collection_count\" : 72,\n" +
//                "          \"collection_time_in_millis\" : 448\n" +
//                "        },\n" +
//                "        \"old\" : {\n" +
//                "          \"collection_count\" : 3,\n" +
//                "          \"collection_time_in_millis\" : 482\n" +
//                "        }\n" +
//                "      }\n" +
//                "    },\n" +
//                "    \"uptime_in_millis\" : 59207\n" +
//                "  },\n" +
//                "  \"process\" : {\n" +
//                "    \"open_file_descriptors\" : -1,\n" +
//                "    \"peak_open_file_descriptors\" : -1,\n" +
//                "    \"max_file_descriptors\" : -1,\n" +
//                "    \"mem\" : {\n" +
//                "      \"total_virtual_in_bytes\" : -1\n" +
//                "    },\n" +
//                "    \"cpu\" : {\n" +
//                "      \"total_in_millis\" : -1,\n" +
//                "      \"percent\" : -3,\n" +
//                "      \"load_average\" : null\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"events\" : {\n" +
//                "    \"in\" : 2211193,\n" +
//                "    \"filtered\" : 39,\n" +
//                "    \"out\" : 39,\n" +
//                "    \"duration_in_millis\" : 39088,\n" +
//                "    \"queue_push_duration_in_millis\" : 46817\n" +
//                "  },\n" +
//                "  \"pipelines\" : {\n" +
//                "    \"test-1\" : {\n" +
//                "      \"events\" : {\n" +
//                "        \"in\" : 43,\n" +
//                "        \"filtered\" : 40,\n" +
//                "        \"queue_push_duration_in_millis\" : 50581,\n" +
//                "        \"duration_in_millis\" : 50088,\n" +
//                "        \"out\" : 41\n" +
//                "      },\n" +
//                "      \"plugins\" : {\n" +
//                "        \"inputs\" : [ {\n" +
//                "          \"id\" : \"f6e8f3a95fd74f5dad45178c9be8154d937351d81ecf9ce958f10793927707c9\",\n" +
//                "          \"events\" : {\n" +
//                "            \"queue_push_duration_in_millis\" : 40581,\n" +
//                "            \"out\" : 43\n" +
//                "          },\n" +
//                "          \"name\" : \"generator\"\n" +
//                "        } ],\n" +
//                "        \"codecs\" : [ {\n" +
//                "          \"id\" : \"plain_88aa447b-1640-4ce8-b73c-223872ba3269\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"plain\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 43,\n" +
//                "            \"duration_in_millis\" : 40612,\n" +
//                "            \"out\" : 43\n" +
//                "          }\n" +
//                "        }, {\n" +
//                "          \"id\" : \"dots_ee689d8b-9569-4492-be99-7fcacbebef21\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 40,\n" +
//                "            \"duration_in_millis\" : 4\n" +
//                "          },\n" +
//                "          \"name\" : \"dots\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0,\n" +
//                "            \"out\" : 0\n" +
//                "          }\n" +
//                "        } ],\n" +
//                "        \"filters\" : [ {\n" +
//                "          \"id\" : \"d2282d0eacdf348e91081a14be251548a723d16dce8504fa574604de64d908b2\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 41,\n" +
//                "            \"duration_in_millis\" : 40057,\n" +
//                "            \"out\" : 40\n" +
//                "          },\n" +
//                "          \"name\" : \"sleep\"\n" +
//                "        } ],\n" +
//                "        \"outputs\" : [ {\n" +
//                "          \"id\" : \"c9f6c3ccb5bd4fb92931e032e3568abe32409f881dbd5d312f846b7e28a741c4\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 40,\n" +
//                "            \"duration_in_millis\" : 10,\n" +
//                "            \"out\" : 40\n" +
//                "          },\n" +
//                "          \"name\" : \"stdout\"\n" +
//                "        } ]\n" +
//                "      },\n" +
//                "      \"reloads\" : {\n" +
//                "        \"failures\" : 0,\n" +
//                "        \"last_success_timestamp\" : null,\n" +
//                "        \"last_failure_timestamp\" : null,\n" +
//                "        \"last_error\" : null,\n" +
//                "        \"successes\" : 0\n" +
//                "      },\n" +
//                "      \"queue\" : {\n" +
//                "        \"type\" : \"memory\",\n" +
//                "        \"events_count\" : 0,\n" +
//                "        \"queue_size_in_bytes\" : 0,\n" +
//                "        \"max_queue_size_in_bytes\" : 0\n" +
//                "      },\n" +
//                "      \"hash\" : \"a3bf686e789a4fbb312192cf2f27667831f9c56b7c9394c1f2d8b933fe5c3ddc\",\n" +
//                "      \"ephemeral_id\" : \"6d0544c2-883f-4134-8210-92f6e73a809e\"\n" +
//                "    },\n" +
//                "    \"test\" : {\n" +
//                "      \"events\" : {\n" +
//                "        \"in\" : 2220586,\n" +
//                "        \"filtered\" : 0,\n" +
//                "        \"queue_push_duration_in_millis\" : 7269,\n" +
//                "        \"duration_in_millis\" : 0,\n" +
//                "        \"out\" : 0\n" +
//                "      },\n" +
//                "      \"plugins\" : {\n" +
//                "        \"inputs\" : [ {\n" +
//                "          \"id\" : \"7334b34b630b17f743f8922844342bf7bbc97ab1094a9c2b46611910dbf93a37\",\n" +
//                "          \"events\" : {\n" +
//                "            \"queue_push_duration_in_millis\" : 7269,\n" +
//                "            \"out\" : 2220592\n" +
//                "          },\n" +
//                "          \"name\" : \"generator\"\n" +
//                "        } ],\n" +
//                "        \"codecs\" : [ {\n" +
//                "          \"id\" : \"plain_aa19eaf0-ff4c-4dda-97b2-cadd9be33fd8\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"plain\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 2220601,\n" +
//                "            \"duration_in_millis\" : 7470,\n" +
//                "            \"out\" : 2220602\n" +
//                "          }\n" +
//                "        }, {\n" +
//                "          \"id\" : \"plain_d75fa63f-6c48-4b6e-8cb1-27ef8d58a31c\",\n" +
//                "          \"encode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"plain\",\n" +
//                "          \"decode\" : {\n" +
//                "            \"writes_in\" : 0,\n" +
//                "            \"duration_in_millis\" : 0,\n" +
//                "            \"out\" : 0\n" +
//                "          }\n" +
//                "        } ],\n" +
//                "        \"filters\" : [ {\n" +
//                "          \"id\" : \"0c00975d5d8964e1b74e4114b7d73f38c2d4e3eaf876e83103b64912a9519e25\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 1,\n" +
//                "            \"duration_in_millis\" : 1001,\n" +
//                "            \"out\" : 1\n" +
//                "          },\n" +
//                "          \"name\" : \"sleep\"\n" +
//                "        } ],\n" +
//                "        \"outputs\" : [ {\n" +
//                "          \"id\" : \"6a1bb5c7e92fcc87a75578374421861345ea859be1b1b9ea2ee0bb9fd4445cd0\",\n" +
//                "          \"events\" : {\n" +
//                "            \"in\" : 1,\n" +
//                "            \"duration_in_millis\" : 0,\n" +
//                "            \"out\" : 0\n" +
//                "          },\n" +
//                "          \"name\" : \"elasticsearch\"\n" +
//                "        } ]\n" +
//                "      },\n" +
//                "      \"reloads\" : {\n" +
//                "        \"failures\" : 0,\n" +
//                "        \"last_success_timestamp\" : null,\n" +
//                "        \"last_failure_timestamp\" : null,\n" +
//                "        \"last_error\" : null,\n" +
//                "        \"successes\" : 0\n" +
//                "      },\n" +
//                "      \"queue\" : {\n" +
//                "        \"type\" : \"persisted\",\n" +
//                "        \"events_count\" : 2072769,\n" +
//                "        \"queue_size_in_bytes\" : 599016378,\n" +
//                "        \"max_queue_size_in_bytes\" : 1073741824\n" +
//                "      },\n" +
//                "      \"hash\" : \"be0a2742607412952e3d807b106ae50601e28fc2db3e0e58bce58580e8744f7a\",\n" +
//                "      \"ephemeral_id\" : \"e3942fd7-34c8-456c-a18a-0bc0dfab840f\"\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"reloads\" : {\n" +
//                "    \"failures\" : 0,\n" +
//                "    \"successes\" : 0\n" +
//                "  },\n" +
//                "  \"os\" : { },\n" +
//                "  \"queue\" : {\n" +
//                "    \"events_count\" : 2072769\n" +
//                "  }\n" +
//                "}\n";
//        LogstashNodeStats stats = JSONObject.parseObject(str,LogstashNodeStats.class);
////        this.logstashPipelinesService.processPipelines(stats,"10.1.1.1","9600","logstash-1");
//    }
//}
