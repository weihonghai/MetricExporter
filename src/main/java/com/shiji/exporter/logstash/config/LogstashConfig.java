package com.shiji.exporter.logstash.config;


import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.exporter.logstash.dto.Logstash;
import com.shiji.exporter.logstash.properties.LogstashProperties;
import com.shiji.exporter.logstash.util.MetricRegex;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(LogstashProperties.class)
public class LogstashConfig {

    @Autowired
    private LogstashProperties logstashProperties;

    @Bean
    public List<MetricCollectorRegistry> allLogstashRegistry() throws Exception {
        if (logstashProperties.getLogstashes().size() == 0){
            throw new Exception("Please configure logstashes!");
        }
        if(0<logstashProperties.getLogstashes()
                .stream()
                .filter(s -> StringUtils.isBlank(s.getIp()) || StringUtils.isBlank(s.getPort()) || StringUtils.isBlank(s.getName())).count()){
            throw new Exception("logstashes Ip or port or logstashName can`t be empty");
        }
        if(0<logstashProperties.getLogstashes()
                .stream()
                .filter(s -> !MetricRegex.isIp(s.getIp()) || !StringUtils.isNumeric(s.getPort())).count()){
            throw new Exception("logstashes Ip or port is format error ");
        }
        if(logstashProperties.getLogstashes().size()!=logstashProperties.getLogstashes().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Logstash::getIp).thenComparing(Logstash::getPort))), ArrayList::new)).size()){
            throw new Exception("There is duplicate logstashes config please check! ");
        }
        return this.logstashProperties.getLogstashes().stream().map(logstash -> {
            return new MetricCollectorRegistry();
        }).collect(Collectors.toList());

    }

}
