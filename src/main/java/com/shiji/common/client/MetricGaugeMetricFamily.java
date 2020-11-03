package com.shiji.common.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MetricGaugeMetricFamily extends MetricCollector.MetricFamilySamples {

  private final List<String> labelNames;

  public MetricGaugeMetricFamily(String name, String help, double value) {
    super(name, MetricCollector.Type.GAUGE, help, new ArrayList<Sample>());
    labelNames = Collections.emptyList();
    samples.add(
        new Sample(
          name,
          labelNames,
          Collections.<String>emptyList(),
          value));
  }

  public MetricGaugeMetricFamily(String name, String help, List<String> labelNames) {
    super(name, MetricCollector.Type.GAUGE, help, new ArrayList<Sample>());
    this.labelNames = labelNames;
  }

  public MetricGaugeMetricFamily addMetric(List<String> labelValues, double value) {
    if (labelValues.size() != labelNames.size()) {
      throw new IllegalArgumentException("Incorrect number of labels.");
    }
    samples.add(new Sample(name, labelNames, labelValues, value));
    return this;
  }
}
