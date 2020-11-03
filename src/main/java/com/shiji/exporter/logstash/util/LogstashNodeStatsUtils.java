package com.shiji.exporter.logstash.util;

import com.alibaba.fastjson.JSONObject;
import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.node.stats.pipelines.Pipelines;
import org.springframework.util.StringUtils;


public class LogstashNodeStatsUtils {

    public static LogstashNodeStats convertNodeStats(String str){
        LogstashNodeStats stats = null;
        if(StringUtils.hasText(str)){
            stats = JSONObject.parseObject(str,LogstashNodeStats.class);
            if(null != stats && StringUtils.hasText(stats.getPipelines())){
                JSONObject obj = JSONObject.parseObject(stats.getPipelines());
                obj.forEach((k,v)->{
                    System.out.println(JSONObject.parseObject(v.toString(), Pipelines.class));
                });
            }
        }
        return null;
    }
}
