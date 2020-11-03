package com.shiji.common.client;

import java.util.List;
import java.util.regex.Pattern;

public abstract class MetricCollector {

  public abstract List<MetricFamilySamples> collect();
  public enum Type {
    COUNTER,
    GAUGE,
    SUMMARY,
    HISTOGRAM,
    UNTYPED,
  }

  static public class MetricFamilySamples {
    public final String name;
    public final Type type;
    public final String help;
    public final List<Sample> samples;

    public MetricFamilySamples(String name, Type type, String help, List<Sample> samples) {
      this.name = name;
      this.type = type;
      this.help = help;
      this.samples = samples;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof MetricFamilySamples)) {
        return false;
      }
      MetricFamilySamples other = (MetricFamilySamples) obj;
      
      return other.name.equals(name) && other.type.equals(type)
        && other.help.equals(help) && other.samples.equals(samples) ;
    }

    @Override
    public int hashCode() {
      int hash = 1;
      hash = 37 * hash + name.hashCode();
      hash = 37 * hash + type.hashCode();
      hash = 37 * hash + help.hashCode();
      hash = 37 * hash + samples.hashCode();
      return hash;
    }

    @Override
    public String toString() {
      return "Name: " + name + " Type: " + type + " Help: " + help + 
        " Samples: " + samples;
    }


    public static class Sample {
      public final String name;
      public final List<String> labelNames;
      public final List<String> labelValues;  // Must have same length as labelNames.
      public final double value;
      public final Long timestampMs;  // It's an epoch format with milliseconds value included (this field is subject to change).

      public Sample(String name, List<String> labelNames, List<String> labelValues, double value, Long timestampMs) {
        this.name = name;
        this.labelNames = labelNames;
        this.labelValues = labelValues;
        this.value = value;
        this.timestampMs = timestampMs;
      }

      public Sample(String name, List<String> labelNames, List<String> labelValues, double value) {
    	  this(name, labelNames, labelValues, value, null);
      }

      @Override
      public boolean equals(Object obj) {
        if (!(obj instanceof Sample)) {
          return false;
        }
        Sample other = (Sample) obj;

        return other.name.equals(name) && other.labelNames.equals(labelNames)
          && other.labelValues.equals(labelValues) && other.value == value
          && ((timestampMs == null && other.timestampMs == null) || (other.timestampMs != null) && (other.timestampMs.equals(timestampMs)));
      }

      @Override
      public int hashCode() {
        int hash = 1;
        hash = 37 * hash + name.hashCode();
        hash = 37 * hash + labelNames.hashCode();
        hash = 37 * hash + labelValues.hashCode();
        long d = Double.doubleToLongBits(value);
        hash = 37 * hash + (int)(d ^ (d >>> 32));
        if (timestampMs != null) {
          hash = 37 * hash + timestampMs.hashCode();
        }
        return hash;
      }

      @Override
      public String toString() {
        return "Name: " + name + " LabelNames: " + labelNames + " labelValues: " + labelValues +
          " Value: " + value + " TimestampMs: " + timestampMs;
      }
    }
  }

  public <T extends MetricCollector> T register(MetricCollectorRegistry registry) {
    registry.register(this);
    return (T)this;
  }


  public interface Describable {

    List<MetricFamilySamples> describe();
  }


  /* Various utility functions for implementing Collectors. */


  public static final double NANOSECONDS_PER_SECOND = 1E9;

  public static final double MILLISECONDS_PER_SECOND = 1E3;

  private static final Pattern METRIC_NAME_RE = Pattern.compile("[a-zA-Z_:][a-zA-Z0-9_:]*");
  private static final Pattern METRIC_LABEL_NAME_RE = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
  private static final Pattern RESERVED_METRIC_LABEL_NAME_RE = Pattern.compile("__.*");

  /**
   * Throw an exception if the metric name is invalid.
   */
  protected static void checkMetricName(String name) {
    if (!METRIC_NAME_RE.matcher(name).matches()) {
      throw new IllegalArgumentException("Invalid metric name: " + name);
    }
  }

  private static final Pattern SANITIZE_PREFIX_PATTERN = Pattern.compile("^[^a-zA-Z_:]");
  private static final Pattern SANITIZE_BODY_PATTERN = Pattern.compile("[^a-zA-Z0-9_:]");


  public static String sanitizeMetricName(String metricName) {
    return SANITIZE_BODY_PATTERN.matcher(
            SANITIZE_PREFIX_PATTERN.matcher(metricName).replaceFirst("_")
    ).replaceAll("_");
  }


  protected static void checkMetricLabelName(String name) {
    if (!METRIC_LABEL_NAME_RE.matcher(name).matches()) {
      throw new IllegalArgumentException("Invalid metric label name: " + name);
    }
    if (RESERVED_METRIC_LABEL_NAME_RE.matcher(name).matches()) {
      throw new IllegalArgumentException("Invalid metric label name, reserved for internal use: " + name);
    }
  }


  public static String doubleToGoString(double d) {
    if (d == Double.POSITIVE_INFINITY) {
      return "+Inf";
    } 
    if (d == Double.NEGATIVE_INFINITY) {
      return "-Inf";
    }
    if (Double.isNaN(d)) {
      return "NaN";
    }
    return Double.toString(d);
  }
}
