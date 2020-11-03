package com.shiji.task;

import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.exporter.logstash.constants.LogstashConstants;
import com.shiji.exporter.logstash.constants.LogstashMetricsConstants;
import com.shiji.exporter.logstash.dto.Logstash;
import com.shiji.exporter.logstash.properties.LogstashProperties;
import com.shiji.exporter.logstash.pushgateway.MetricPushGateway;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.exporter.PushGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Component
@Slf4j
public class PushGateWayMetricsTask {
    @Autowired
    private PushGateway pushGatewayExport;

    @Autowired
    private MetricPushGateway pushGatewayLogstash;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private PrometheusMeterRegistry prometheusMeterRegistry;
    @Autowired
    private LogstashProperties logstashProperties;


    @Value("${soc.deploy.channel}")
    private String channel;
    @Value("${server.port}")
    private String port;
    @Value("${spring.application.ip}")
    private String ip;
    @Value("${soc.prometheus.target.soc-partner}")
    private String MetricPartner;
    @Value("${soc.prometheus.target.soc-solution-name}")
    private String MetricSolutionName;
    @Value("${soc.push-gate-way.push-interval}")
    private Long pushInterval;
    @Value("${soc.logstash-server.push-interval}")
    private Long pushLogstashInterval;
    @Value("${soc.prometheus.target.program-view-board}")
    private String exportViewBoard;
    @Value("${soc.prometheus.target.monitored-program-view-board}")
    private String logstashViewBoard;

    @Value("${soc.upload-type}")
    private String uploadType;

    @Autowired
    private List<MetricCollectorRegistry> allLogstashRegistry;

    @PostConstruct
    public void init() {
        startPushLogStashTaskRun();
        startPushExportTaskRun();
    }

    public void startPushLogStashTaskRun() {
        if (StringUtils.hasText(MetricPartner) && StringUtils.hasText(MetricSolutionName)
                && !StringUtils.isEmpty(pushInterval) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType)) {
            if (pushInterval < 5000L) {
                pushInterval = 5000L;
            }
            if(!StringUtils.hasText(logstashViewBoard)){
                logstashViewBoard = LogstashConstants.VIEW_BOARD_LOGSTASH;
            }
//            map.put(LogstashMetricsConstants.Metrics.VIEW_BOARD.getName(), logstashViewBoard);
            Stream.iterate(0, i -> i + 1).limit(allLogstashRegistry.size()).forEach(i -> {
                Logstash logstash = logstashProperties.getLogstashes().get(i);
                MetricCollectorRegistry registry = allLogstashRegistry.get(i);
                //String job = channel+"_"+MetricPartner+"_"+ip+":"+port+"_"+logstash.getIp()+":"+logstash.getPort();
                String job = logstashViewBoard;
                HashMap<String, String> map = new HashMap<>();
                map.put("groupByKey",channel+"_"+MetricPartner+"_"+"instance_"+ip+":"+port+"_monitorInstance_"+logstash.getIp()+":"+logstash.getPort());
                taskScheduler.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            pushGatewayLogstash.pushAdd(registry, job, map);
                            pushGatewayLogstash.push(registry, job, map);
                        } catch (IOException e) {
                            log.error("Push Metrics Error,PushGateWay JobName :{},Error message: {}",job,e.getMessage());
                        }
                    }
                }, pushLogstashInterval);
            });
        }
    }

    public void startPushExportTaskRun() {
        if (StringUtils.hasText(MetricPartner) && StringUtils.hasText(MetricSolutionName)
                && !StringUtils.isEmpty(pushInterval) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType)) {
            if (pushInterval < 5000L) {
                pushInterval = 5000L;
            }
            HashMap<String, String> map = new HashMap<>();
            if(!StringUtils.hasText(exportViewBoard)){
                exportViewBoard = LogstashConstants.VIEW_BOARD_SPRINGBOOT_2_X;
            }
            map.put("groupByKey", channel+"_"+MetricPartner+"_"+"instance_"+ip+":"+port);
//            String job = channel+"_"+MetricPartner+"_"+ip+":"+port;
            String job = exportViewBoard;
            taskScheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
//                        pushGatewayExport.pushAdd(prometheusMeterRegistry.getPrometheusRegistry(), job, map);
                          pushGatewayExport.push(prometheusMeterRegistry.getPrometheusRegistry(), job, map);
                    } catch (IOException e) {
                        log.error("Push Metrics Error,PushGateWay JobName :{},Error message: {}",job,e.getMessage());
                    }
                }
            }, pushInterval);
        }
    }
}
