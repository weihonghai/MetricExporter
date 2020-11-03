package com.shiji.exporter.logstash.util;

import java.text.DecimalFormat;

public class DoubleUtils {

    public static final DecimalFormat df = new DecimalFormat("#.00");

    public static Double getDoubleFormat(Double result){
        return Double.valueOf(df.format(result));
    }

    public static Double parseMillisecondToSeconds(Double millisecond){
        Double seconds = getDoubleFormat(millisecond/1000.0d);
        return seconds;

    }
}
