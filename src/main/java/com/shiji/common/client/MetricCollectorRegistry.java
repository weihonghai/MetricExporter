package com.shiji.common.client;

import java.util.*;

/**
 * A registry of Collectors.
 * <p>
 * The majority of users should use the {@link}, rather than instantiating their own.
 * <p>
 * Creating a registry other than the default is primarily useful for unittests, or
 * pushing a subset of metrics to the <a href="https://github.com/prometheus/pushgateway">Pushgateway</a>
 * from batch jobs.
 */
public class MetricCollectorRegistry {
  private final Object namesCollectorsLock = new Object();
  private final Map<MetricCollector, List<String>> collectorsToNames = new HashMap<MetricCollector, List<String>>();
  private final Map<String, MetricCollector> namesToCollectors = new HashMap<String, MetricCollector>();

  private final boolean autoDescribe;

  public MetricCollectorRegistry() {
    this(false);
  }

  public MetricCollectorRegistry(boolean autoDescribe) {
    this.autoDescribe = autoDescribe;
  }


  public void register(MetricCollector m) {
    List<String> names = collectorNames(m);
    synchronized (namesCollectorsLock) {
      for (String name : names) {
        if (namesToCollectors.containsKey(name)) {
          throw new IllegalArgumentException("Collector already registered that provides name: " + name);
        }
      }
      for (String name : names) {
        namesToCollectors.put(name, m);
      }
      collectorsToNames.put(m, names);
    }
  }


  public void unregister(MetricCollector m) {
    synchronized (namesCollectorsLock) {
      List<String> names = collectorsToNames.remove(m);
      for (String name : names) {
        namesToCollectors.remove(name);
      }
    }
  }


  public void clear() {
    synchronized (namesCollectorsLock) {
      collectorsToNames.clear();
      namesToCollectors.clear();
    }
  }

  /**
   * A snapshot of the current collectors.
   */
  private Set<MetricCollector> collectors() {
    synchronized (namesCollectorsLock) {
      return new HashSet<MetricCollector>(collectorsToNames.keySet());
    }
  }

  private List<String> collectorNames(MetricCollector m) {
    List<MetricCollector.MetricFamilySamples> mfs;
    if (m instanceof MetricCollector.Describable) {
      mfs = ((MetricCollector.Describable) m).describe();
    } else if (autoDescribe) {
      mfs = m.collect();
    } else {
      mfs = Collections.emptyList();
    }

    List<String> names = new ArrayList<String>();
    for (MetricCollector.MetricFamilySamples family : mfs) {
      switch (family.type) {
        case SUMMARY:
          names.add(family.name + "_count");
          names.add(family.name + "_sum");
          names.add(family.name);
          break;
        case HISTOGRAM:
          names.add(family.name + "_count");
          names.add(family.name + "_sum");
          names.add(family.name + "_bucket");
          names.add(family.name);
          break;
        default:
          names.add(family.name);
      }
    }
    return names;
  }

  /**
   * Enumeration of metrics of all registered collectors.
   */
  public Enumeration<MetricCollector.MetricFamilySamples> metricFamilySamples() {
    return new MetricFamilySamplesEnumeration();
  }


  public Enumeration<MetricCollector.MetricFamilySamples> filteredMetricFamilySamples(Set<String> includedNames) {
    return new MetricFamilySamplesEnumeration(includedNames);
  }

  class MetricFamilySamplesEnumeration implements Enumeration<MetricCollector.MetricFamilySamples> {

    private final Iterator<MetricCollector> collectorIter;
    private Iterator<MetricCollector.MetricFamilySamples> metricFamilySamples;
    private MetricCollector.MetricFamilySamples next;
    private Set<String> includedNames;

    MetricFamilySamplesEnumeration(Set<String> includedNames) {
      this.includedNames = includedNames;
      collectorIter = includedCollectorIterator(includedNames);
      findNextElement();
    }

    private Iterator<MetricCollector> includedCollectorIterator(Set<String> includedNames) {
      if (includedNames.isEmpty()) {
        return collectors().iterator();
      } else {
        HashSet<MetricCollector> collectors = new HashSet<MetricCollector>();
        synchronized (namesCollectorsLock) {
          for (Map.Entry<String, MetricCollector> entry : namesToCollectors.entrySet()) {
            if (includedNames.contains(entry.getKey())) {
              collectors.add(entry.getValue());
            }
          }
        }

        return collectors.iterator();
      }
    }

    MetricFamilySamplesEnumeration() {
      this(Collections.<String>emptySet());
    }

    private void findNextElement() {
      next = null;

      while (metricFamilySamples != null && metricFamilySamples.hasNext()) {
        next = filter(metricFamilySamples.next());
        if (next != null) {
          return;
        }
      }

      if (next == null) {
        while (collectorIter.hasNext()) {
          metricFamilySamples = collectorIter.next().collect().iterator();
          while (metricFamilySamples.hasNext()) {
            next = filter(metricFamilySamples.next());
            if (next != null) {
              return;
            }
          }
        }
      }
    }

    private MetricCollector.MetricFamilySamples filter(MetricCollector.MetricFamilySamples next) {
      if (includedNames.isEmpty()) {
        return next;
      } else {
        Iterator<MetricCollector.MetricFamilySamples.Sample> it = next.samples.iterator();
        while (it.hasNext()) {
            if (!includedNames.contains(it.next().name)) {
                it.remove();
            }
        }
        if (next.samples.size() == 0) {
          return null;
        }
        return next;
      }
    }

    public MetricCollector.MetricFamilySamples nextElement() {
      MetricCollector.MetricFamilySamples current = next;
      if (current == null) {
        throw new NoSuchElementException();
      }
      findNextElement();
      return current;
    }

    public boolean hasMoreElements() {
      return next != null;
    }
  }

  /**
   * Returns the given value, or null if it doesn't exist.
   * <p>
   * This is inefficient, and intended only for use in unittests.
   */
  public Double getSampleValue(String name) {
    return getSampleValue(name, new String[]{}, new String[]{});
  }

  /**
   * Returns the given value, or null if it doesn't exist.
   * <p>
   * This is inefficient, and intended only for use in unittests.
   */
  public Double getSampleValue(String name, String[] labelNames, String[] labelValues) {
    for (MetricCollector.MetricFamilySamples metricFamilySamples : Collections.list(metricFamilySamples())) {
      for (MetricCollector.MetricFamilySamples.Sample sample : metricFamilySamples.samples) {
        if (sample.name.equals(name)
                && Arrays.equals(sample.labelNames.toArray(), labelNames)
                && Arrays.equals(sample.labelValues.toArray(), labelValues)) {
          return sample.value;
        }
      }
    }
    return null;
  }

}
