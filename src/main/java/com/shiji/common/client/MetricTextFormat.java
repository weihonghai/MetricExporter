package com.shiji.common.client;


import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.List;

public class MetricTextFormat {
  /**
   * Content-type for text version 0.0.4.
   */
  public final static String CONTENT_TYPE_004 = "text/plain; version=0.0.4; charset=utf-8";

  /**
   * Write out the text version 0.0.4 of the given MetricFamilySamples.
   */
  public static void write004(Writer writer, List<Enumeration<MetricCollector.MetricFamilySamples>> list) throws IOException {
    /* See http://prometheus.io/docs/instrumenting/exposition_formats/
     * for the output format specification. */
    if (null != list && !list.isEmpty()){
      for(Enumeration<MetricCollector.MetricFamilySamples> mfs : list){
        while(mfs.hasMoreElements()) {
          MetricCollector.MetricFamilySamples metricFamilySamples = mfs.nextElement();
          writer.write("# HELP ");
          writer.write(metricFamilySamples.name);
          writer.write(' ');
          writeEscapedHelp(writer, metricFamilySamples.help);
          writer.write('\n');

          writer.write("# TYPE ");
          writer.write(metricFamilySamples.name);
          writer.write(' ');
          writer.write(typeString(metricFamilySamples.type));
          writer.write('\n');

          for (MetricCollector.MetricFamilySamples.Sample sample: metricFamilySamples.samples) {
            writer.write(sample.name);
            if (sample.labelNames.size() > 0) {
              writer.write('{');
              for (int i = 0; i < sample.labelNames.size(); ++i) {
                writer.write(sample.labelNames.get(i));
                writer.write("=\"");
                writeEscapedLabelValue(writer, sample.labelValues.get(i));
                writer.write("\",");
              }
              writer.write('}');
            }
            writer.write(' ');
            writer.write(MetricCollector.doubleToGoString(sample.value));
            if (sample.timestampMs != null){
              writer.write(' ');
              writer.write(sample.timestampMs.toString());
            }
            writer.write('\n');
          }
        }
      }

    }

  }

  private static void writeEscapedHelp(Writer writer, String s) throws IOException {
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case '\\':
          writer.append("\\\\");
          break;
        case '\n':
          writer.append("\\n");
          break;
        default:
          writer.append(c);
      }
    }
  }

  private static void writeEscapedLabelValue(Writer writer, String s) throws IOException {
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case '\\':
          writer.append("\\\\");
          break;
        case '\"':
          writer.append("\\\"");
          break;
        case '\n':
          writer.append("\\n");
          break;
        default:
          writer.append(c);
      }
    }
  }

  private static String typeString(MetricCollector.Type t) {
    switch (t) {
      case GAUGE:
        return "gauge";
      case COUNTER:
        return "counter";
      case SUMMARY:
        return "summary";
      case HISTOGRAM:
        return "histogram";
      default:
        return "untyped";
    }
  }
}
