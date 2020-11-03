package com.shiji.common.client;

import io.prometheus.client.Collector;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public abstract class MetricSimpleCollector<Child> extends MetricCollector {
  protected final String fullname;
  protected final String help;
  protected final List<String> labelNames;

  protected final ConcurrentMap<List<String>, Child> children = new ConcurrentHashMap<List<String>, Child>();
  protected Child noLabelsChild;

  /**
   * Return the Child with the given labels, creating it if needed.
   * <p>
   * Must be passed the same number of labels are were passed to {@link #labelNames}.
   */
  public Child labels(String... labelValues) {
    if (labelValues.length != labelNames.size()) {
      throw new IllegalArgumentException("Incorrect number of labels.");
    }
    for (String label: labelValues) {
      if (label == null) {
        throw new IllegalArgumentException("Label cannot be null.");
      }
    }
    List<String> key = Arrays.asList(labelValues);
    Child c = children.get(key);
    if (c != null) {
      return c;
    }
    Child c2 = newChild();
    Child tmp = children.putIfAbsent(key, c2);
    return tmp == null ? c2 : tmp;
  }

  /**
   * Remove the Child with the given labels.
   * <p>
   * Any references to the Child are invalidated.
   */
  public void remove(String... labelValues) {
    children.remove(Arrays.asList(labelValues));
    initializeNoLabelsChild();
  }
  
  /**
   * Remove all children.
   * <p>
   * Any references to any children are invalidated.
   */
  public void clear() {
    children.clear();
    initializeNoLabelsChild();
  }
  
  /**
   * Initialize the child with no labels.
   */
  protected void initializeNoLabelsChild() {
    // Initialize metric if it has no labels.
    if (labelNames.size() == 0) {
      noLabelsChild = labels();
    }
  }

  /**
   * Replace the Child with the given labels.
   * <p>
   * This is intended for advanced uses, in particular proxying metrics
   * from another monitoring system. This allows for callbacks for returning
   * values for {@link Counter} and {@link Gauge} without having to implement
   * a full {@link Collector}.
   * <p>
   * An example with {@link Gauge}:
   * <pre>
   * {@code
   *   Gauge.build().name("current_time").help("Current unixtime.").create()
   *       .setChild(new Gauge.Child() {
   *         public double get() {
   *           return System.currentTimeMillis() / MILLISECONDS_PER_SECOND;
   *         }
   *       }).register();
   * }
   * </pre>
   * <p>
   * Any references any previous Child with these labelValues are invalidated. 
   * A metric should be either all callbacks, or none.
   */
  public <T extends MetricCollector> T setChild(Child child, String... labelValues) {
    if (labelValues.length != labelNames.size()) {
      throw new IllegalArgumentException("Incorrect number of labels.");
    }
    children.put(Arrays.asList(labelValues), child);
    return (T)this;
  }

  /**
   * Return a new child, workaround for Java generics limitations.
   */
  protected abstract Child newChild();

  protected List<MetricFamilySamples> familySamplesList(Type type, List<MetricFamilySamples.Sample> samples) {
    MetricFamilySamples mfs = new MetricFamilySamples(fullname, type, help, samples);
    List<MetricFamilySamples> mfsList = new ArrayList<MetricFamilySamples>(1);
    mfsList.add(mfs);
    return mfsList;
  }

  protected MetricSimpleCollector(Builder b) {
    if (b.name.isEmpty()) throw new IllegalStateException("Name hasn't been set.");
    String name = b.name;
    if (!b.subsystem.isEmpty()) {
      name = b.subsystem + '_' + name;
    }
    if (!b.namespace.isEmpty()) {
      name = b.namespace + '_' + name;
    }
    fullname = name;
    checkMetricName(fullname);
    if (b.help != null && b.help.isEmpty()) throw new IllegalStateException("Help hasn't been set.");
    help = b.help;
    labelNames = Arrays.asList(b.labelNames);

    for (String n: labelNames) {
      checkMetricLabelName(n);
    }

    if (!b.dontInitializeNoLabelsChild) {
      initializeNoLabelsChild();
    }
  }

  /**
   * Builders let you configure and then create collectors.
   */
  public abstract static class Builder<B extends Builder<B, C>, C extends MetricSimpleCollector> {
    String namespace = "";
    String subsystem = "";
    String name = "";
    String fullname = "";
    String help = "";
    String[] labelNames = new String[]{};
    // Some metrics require additional setup before the initialization can be done.
    boolean dontInitializeNoLabelsChild;

    /**
     * Set the name of the metric. Required.
     */
    public B name(String name) {
      this.name = name;
      return (B)this;
    }
    /**
     * Set the subsystem of the metric. Optional.
     */
    public B subsystem(String subsystem) {
      this.subsystem = subsystem;
      return (B)this;
    }
    /**
     * Set the namespace of the metric. Optional.
     */
    public B namespace(String namespace) {
      this.namespace = namespace;
      return (B)this;
    }
    /**
     * Set the help string of the metric. Required.
     */
    public B help(String help) {
      this.help = help;
      return (B)this;
    }
    /**
     * Set the labelNames of the metric. Optional, defaults to no labels.
     */
    public B labelNames(String... labelNames) {
      this.labelNames = labelNames;
      return (B)this;
    }

    /**
     * Return the constructed collector.
     * <p>
     * Abstract due to generics limitations.
     */
    public abstract C create();


    /**
     * Create and register the Collector with the given registry.
     */
    public C register(MetricCollectorRegistry registry) {
      C sc = create();
      registry.register(sc);
      return sc;
    }
  }
}
