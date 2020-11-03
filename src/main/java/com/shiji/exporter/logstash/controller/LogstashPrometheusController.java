package com.shiji.exporter.logstash.controller;

import com.shiji.common.client.MetricCollector;
import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.common.client.MetricTextFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/monitor")
@Api(value = "Metric Logstash Metrics Monitor Endpoint For Pull Mode")

public class LogstashPrometheusController {

    @Autowired
    private List<MetricCollectorRegistry> allLogstashRegistry;

    @ApiOperation(value = "prometheus", nickname = "")
    @GetMapping(value = "/prometheus/logstash", produces = {"application/json;charset=UTF-8"})
    public void logstashExporter(HttpServletResponse response) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            List<Enumeration<MetricCollector.MetricFamilySamples>> list = new ArrayList<>();
            allLogstashRegistry.forEach((logstash)->{
                list.add(logstash.metricFamilySamples());
            });
            MetricTextFormat.write004(writer, list);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
        String response2 = writer.toString();
        try (OutputStream os = response.getOutputStream()) {
            os.write(response2.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
