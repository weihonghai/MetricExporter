package com.shiji.task;

import com.alibaba.fastjson.JSONObject;
import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.exporter.logstash.constants.LogstashConstants;
import com.shiji.exporter.logstash.constants.LogstashMetricsConstants;
import com.shiji.exporter.logstash.dto.Logstash;
import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashEventsMetrics;
import com.shiji.exporter.logstash.metrics.LogstashJvmMetrics;
import com.shiji.exporter.logstash.metrics.LogstashPipelinesMetrics;
import com.shiji.exporter.logstash.metrics.LogstashProcessMetrics;
import com.shiji.exporter.logstash.properties.LogstashProperties;
import com.shiji.exporter.logstash.service.LogstashEventsService;
import com.shiji.exporter.logstash.service.LogstashJvmService;
import com.shiji.exporter.logstash.service.LogstashPipelinesService;
import com.shiji.exporter.logstash.service.LogstashProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;

@Component
@Slf4j
public class LogstashMetricsExportTask {

    @Value("${soc.upload-type:pull}")
    private String uploadType;

    @Autowired
    private List<MetricCollectorRegistry> allLogstashRegistry;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LogstashEventsService logstashEventsService;
    @Autowired
    private LogstashJvmService logstashJvmService;
    @Autowired
    private LogstashPipelinesService logstashPipelinesService;
    @Autowired
    private LogstashProcessService logstashProcessService;

    @Autowired
    private LogstashProperties logstashProperties;

    @Value("${soc.prometheus.target.soc-partner}")
    private String MetricPartner;

    @Value("${spring.application.ip}")
    private String ip;
    @Value("${server.port}")
    private String port;
    @Value("${soc.prometheus.target.soc-solution-name}")
    private String MetricSolutionName;
    @Value("${soc.deploy.channel}")
    private String MetricDeployChannel;
    @Value("${soc.prometheus.target.monitored-program-view-board}")
    private String logstashViewBoard;

    @Value("${soc.push-gate-way.ip}")
    private String pushGateWayIp;
    @Value("${soc.push-gate-way.port}")
    private String pushGateWayPort;

    @PostConstruct
    public void init() throws Exception{
        startLogstashTask();
    }

    private void startLogstashTask() throws Exception{
        if (null != allLogstashRegistry && !allLogstashRegistry.isEmpty()) {
            Stream.iterate(0, i -> i + 1).limit(allLogstashRegistry.size()).forEach(i -> {
                Logstash logstash = logstashProperties.getLogstashes().get(i);
                MetricCollectorRegistry registry = allLogstashRegistry.get(i);
                LogstashPrometheusTarget target = getTarget(logstash);
                LogstashEventsMetrics eventsMetrics = new LogstashEventsMetrics(uploadType, registry);
                LogstashJvmMetrics jvmMetrics = new LogstashJvmMetrics(uploadType, registry);
                LogstashPipelinesMetrics pipelinesMetrics = new LogstashPipelinesMetrics(uploadType, registry);
                LogstashProcessMetrics processMetrics = new LogstashProcessMetrics(uploadType, registry);
                taskScheduler.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String result = restTemplate.getForObject("http://" + logstash.getIp() + ":" + logstash.getPort() + "/_node/stats", String.class);
                            LogstashNodeStats stats = JSONObject.parseObject(result, LogstashNodeStats.class);
                            if (stats != null && target !=null) {
                                target.setLogstashVersion(stats.getVersion());
                                logstashEventsService.eventsParse(stats, target, eventsMetrics);
                                logstashJvmService.jvmParse(stats, target, jvmMetrics);
                                logstashPipelinesService.pipelinesParse(stats, target, pipelinesMetrics);
                                logstashProcessService.processParse(stats, target, processMetrics);
                            }
                        } catch (Exception e) {
                            log.error("Prometheus export format,url:{},target:{},error:{}","http://" + logstash.getIp() + ":" + logstash.getPort() + "/_node/stats",target.toString(),e.getMessage());
                            eventsMetrics.cleanMetrics();
                            jvmMetrics.cleanMetrics();
                            pipelinesMetrics.cleanMetrics();
                            processMetrics.cleanMetrics();
                        }
                    }
                }, 2000L);
            });
        }
    }

    private LogstashPrometheusTarget getTarget(Logstash logstash) {
        if (null != logstash && StringUtils.hasText(ip) && StringUtils.hasText(port)
                && StringUtils.hasText(MetricPartner) &&
                StringUtils.hasText(logstash.getIp()) && StringUtils.hasText(logstash.getPort()) && StringUtils.hasText(logstash.getName()) && StringUtils.hasText(MetricSolutionName)) {
            String uploadTypeStr = StringUtils.hasText(uploadType) && LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType) ? "push" : "pull";
            String instance = ip + ":" + port;
            String instanceName = MetricPartner + "-" + LogstashMetricsConstants.Metrics.LOGSTASH_PROMETHEUS_EXPORT.getName();
            String targetInstance = logstash.getIp() + ":" + logstash.getPort();
            String targetInstanceName = MetricPartner + "-" + MetricSolutionName + "-" + logstash.getName();
            LogstashPrometheusTarget target = new LogstashPrometheusTarget();
            target.setSocDeployChannel(MetricDeployChannel);
            target.setInstance(instance);
            target.setInstanceName(instanceName);
            target.setSocPartner(MetricPartner);
            target.setUploadType(uploadTypeStr);
            target.setSocSolutionName(MetricSolutionName);
            target.setTargetInstance(targetInstance);
            target.setTargetInstanceName(targetInstanceName);
            if (!StringUtils.hasText(logstashViewBoard)) {
                logstashViewBoard = LogstashConstants.VIEW_BOARD_LOGSTASH;
            }
            target.setViewBoard(logstashViewBoard);
            if (LogstashMetricsConstants.Metrics.UPLOAD_PUSH.getName().equalsIgnoreCase(uploadType)) {
                target.setPushGateWayInstance(pushGateWayIp + ":" + pushGateWayPort);
            }
            return target;
        }
        return null;
    }
}