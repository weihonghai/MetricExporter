package com.shiji.exporter.logstash.config;

import com.shiji.exporter.logstash.pushgateway.MetricPushGateway;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PushGateWayConfig {

    @Value("${soc.push-gate-way.ip}")
    private String logstashIp;
    @Value("${soc.push-gate-way.port}")
    private String logstashPort;

    @Bean(name = "pushGatewayLogstash")
    public MetricPushGateway SocPushGateway(){
        return new MetricPushGateway(logstashIp + ":" + logstashPort);
    }
    @Bean(name = "pushGatewayExport")
    public PushGateway pushGateway(){
        return new PushGateway(logstashIp + ":" + logstashPort);
    }

}
