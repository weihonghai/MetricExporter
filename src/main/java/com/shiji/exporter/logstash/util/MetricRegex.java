package com.shiji.exporter.logstash.util;

public class MetricRegex {

    public static boolean isIp(String ip) {
        boolean result = ip.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        return result;
    }

}
