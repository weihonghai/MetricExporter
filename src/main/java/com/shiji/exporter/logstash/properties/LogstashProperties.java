package com.shiji.exporter.logstash.properties;

import com.shiji.exporter.logstash.constants.LogstashConstants;
import com.shiji.exporter.logstash.dto.Logstash;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
@ConfigurationProperties(prefix = LogstashConstants.LOGSTASH_PROPERTIES_PREFIX)
public class LogstashProperties {

    private List<Logstash> logstashes;
}
