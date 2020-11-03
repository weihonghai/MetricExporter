package com.shiji.exporter.logstash.pushgateway;


import com.google.common.collect.Lists;
import com.shiji.common.client.MetricCollector;
import com.shiji.common.client.MetricCollectorRegistry;
import com.shiji.common.client.MetricTextFormat;
import io.prometheus.client.exporter.DefaultHttpConnectionFactory;
import io.prometheus.client.exporter.HttpConnectionFactory;
import io.prometheus.client.exporter.common.TextFormat;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MetricPushGateway {

    private static final int MILLISECONDS_PER_SECOND = 1000;

    // Visible for testing.
    protected final String gatewayBaseURL;

    private HttpConnectionFactory connectionFactory = new DefaultHttpConnectionFactory();

    /**
     * Construct a Pushgateway, with the given address.
     * <p>
     * @param address  host:port or ip:port of the Pushgateway.
     */
    public MetricPushGateway(String address) {
        this(createURLSneakily("http://" + address));
    }

    /**
     * Construct a Pushgateway, with the given URL.
     * <p>
     * @param serverBaseURL the base URL and optional context path of the Pushgateway server.
     */
    public MetricPushGateway(URL serverBaseURL) {
        this.gatewayBaseURL = URI.create(serverBaseURL.toString() + "/metrics/")
                .normalize()
                .toString();
    }

    public void setConnectionFactory(HttpConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Creates a URL instance from a String representation of a URL without throwing a checked exception.
     * Required because you can't wrap a call to another constructor in a try statement.
     *
     * TODO: Remove this along with other deprecated methods before version 1.0 is released.
     *
     * @param urlString the String representation of the URL.
     * @return The URL instance.
     */
    private static URL createURLSneakily(final String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pushes all metrics in a registry, replacing all those with the same job and no grouping key.
     * <p>
     * This uses the PUT HTTP method.
     */
    public void push(MetricCollectorRegistry registry, String job) throws IOException {
        doRequest(registry, job, null, "PUT");
    }

    /**
     * Pushes all metrics in a Collector, replacing all those with the same job and no grouping key.
     * <p>
     * This is useful for pushing a single Gauge.
     * <p>
     * This uses the PUT HTTP method.
     */
    public void push(MetricCollector collector, String job) throws IOException {
        MetricCollectorRegistry registry = new MetricCollectorRegistry();
        collector.register(registry);
        push(registry, job);
    }

    /**
     * Pushes all metrics in a registry, replacing all those with the same job and grouping key.
     * <p>
     * This uses the PUT HTTP method.
     */
    public void push(MetricCollectorRegistry registry, String job, Map<String, String> groupingKey) throws IOException {
        doRequest(registry, job, groupingKey, "PUT");
    }

    /**
     * Pushes all metrics in a Collector, replacing all those with the same job and grouping key.
     * <p>
     * This is useful for pushing a single Gauge.
     * <p>
     * This uses the PUT HTTP method.
     */
    public void push(MetricCollector collector, String job, Map<String, String> groupingKey) throws IOException {
        MetricCollectorRegistry registry = new MetricCollectorRegistry();
        collector.register(registry);
        push(registry, job, groupingKey);
    }

    /**
     * Pushes all metrics in a registry, replacing only previously pushed metrics of the same name and job and no grouping key.
     * <p>
     * This uses the POST HTTP method.
     */
    public void pushAdd(MetricCollectorRegistry registry, String job) throws IOException {
        doRequest(registry, job, null, "POST");
    }

    /**
     * Pushes all metrics in a Collector, replacing only previously pushed metrics of the same name and job and no grouping key.
     * <p>
     * This is useful for pushing a single Gauge.
     * <p>
     * This uses the POST HTTP method.
     */
    public void pushAdd(MetricCollector collector, String job) throws IOException {
        MetricCollectorRegistry registry = new MetricCollectorRegistry();
        collector.register(registry);
        pushAdd(registry, job);
    }

    /**
     * Pushes all metrics in a registry, replacing only previously pushed metrics of the same name, job and grouping key.
     * <p>
     * This uses the POST HTTP method.
     */
    public void pushAdd(MetricCollectorRegistry registry, String job, Map<String, String> groupingKey) throws IOException {
        doRequest(registry, job, groupingKey, "POST");
    }

    /**
     * Pushes all metrics in a Collector, replacing only previously pushed metrics of the same name, job and grouping key.
     * <p>
     * This is useful for pushing a single Gauge.
     * <p>
     * This uses the POST HTTP method.
     */
    public void pushAdd(MetricCollector collector, String job, Map<String, String> groupingKey) throws IOException {
        MetricCollectorRegistry registry = new MetricCollectorRegistry();
        collector.register(registry);
        pushAdd(registry, job, groupingKey);
    }


    /**
     * Deletes metrics from the Pushgateway.
     * <p>
     * Deletes metrics with no grouping key and the provided job.
     * This uses the DELETE HTTP method.
     */
    public void delete(String job) throws IOException {
        doRequest(null, job, null, "DELETE");
    }

    /**
     * Deletes metrics from the Pushgateway.
     * <p>
     * Deletes metrics with the provided job and grouping key.
     * This uses the DELETE HTTP method.
     */
    public void delete(String job, Map<String, String> groupingKey) throws IOException {
        doRequest(null, job, groupingKey, "DELETE");
    }


    /**
     * Pushes all metrics in a registry, replacing all those with the same job and instance.
     * <p>
     * This uses the PUT HTTP method.
     */
    @Deprecated
    public void push(MetricCollectorRegistry registry, String job, String instance) throws IOException {
        push(registry, job, Collections.singletonMap("instance", instance));
    }

    /**
     * Pushes all metrics in a Collector, replacing all those with the same job and instance.
     * <p>
     * This is useful for pushing a single Gauge.
     * <p>
     * This uses the PUT HTTP method.
     */
    @Deprecated
    public void push(MetricCollector collector, String job, String instance) throws IOException {
        push(collector, job, Collections.singletonMap("instance", instance));
    }

    /**
     * Pushes all metrics in a registry, replacing only previously pushed metrics of the same name.
     * <p>
     * This uses the POST HTTP method.
     */
    @Deprecated
    public void pushAdd(MetricCollectorRegistry registry, String job, String instance) throws IOException {
        pushAdd(registry, job, Collections.singletonMap("instance", instance));
    }

    /**
     * Pushes all metrics in a Collector, replacing only previously pushed metrics of the same name.
     * <p>
     * <p>
     * This uses the POST HTTP method.
     */
    @Deprecated
    public void pushAdd(MetricCollector collector, String job, String instance) throws IOException {
        pushAdd(collector, job, Collections.singletonMap("instance", instance));
    }

    /**
     * Deletes metrics from the Pushgateway.
     * <p>
     * This uses the DELETE HTTP method.
     * @deprecated use {@link #delete(String, Map)}
     */
    @Deprecated
    public void delete(String job, String instance) throws IOException {
        delete(job, Collections.singletonMap("instance", instance));
    }

    void doRequest(MetricCollectorRegistry registry, String job, Map<String, String> groupingKey, String method) throws IOException {
        String url = gatewayBaseURL;
        if (job.contains("/")) {
            url += "job@base64/" + base64url(job);
        } else {
            url += "job/" + URLEncoder.encode(job, "UTF-8");
        }

        if (groupingKey != null) {
            for (Map.Entry<String, String> entry: groupingKey.entrySet()) {
                if (entry.getValue().contains("/")) {
                    url += "/" + entry.getKey() + "@base64/" + base64url(entry.getValue());
                } else {
                    url += "/" + entry.getKey() + "/" + URLEncoder.encode(entry.getValue(), "UTF-8");
                }
            }
        }
        HttpURLConnection connection = connectionFactory.create(url);
        connection.setRequestProperty("Content-Type", TextFormat.CONTENT_TYPE_004);
        if (!method.equals("DELETE")) {
            connection.setDoOutput(true);
        }
        connection.setRequestMethod(method);

        connection.setConnectTimeout(10 * MILLISECONDS_PER_SECOND);
        connection.setReadTimeout(10 * MILLISECONDS_PER_SECOND);
        connection.connect();

        try {
            if (!method.equals("DELETE")) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                MetricTextFormat.write004(writer, Lists.newArrayList(registry.metricFamilySamples()));
                writer.flush();
                writer.close();
            }

            int response = connection.getResponseCode();
            if (response/100 != 2) {
                String errorMessage;
                InputStream errorStream = connection.getErrorStream();
                if(errorStream != null) {
                    String errBody = readFromStream(errorStream);
                    errorMessage = "Response code from " + url + " was " + response + ", response body: " + errBody;
                } else {
                    errorMessage = "Response code from " + url + " was " + response;
                }
                throw new IOException(errorMessage);
            }
        } finally {
            connection.disconnect();
        }
    }

    private static String base64url(String v) {
        // Per RFC4648 table 2. We support Java 6, and java.util.Base64 was only added in Java 8,
        try {
            return DatatypeConverter.printBase64Binary(v.getBytes("UTF-8")).replace("+", "-").replace("/", "_");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);  // Unreachable.
        }
    }

    /**
     * Returns a grouping key with the instance label set to the machine's IP address.
     * <p>
     * This is a convenience function, and should only be used where you want to
     * push per-instance metrics rather than cluster/job level metrics.
     */
    public static Map<String, String> instanceIPGroupingKey() throws UnknownHostException {
        Map<String, String> groupingKey = new HashMap<String, String>();
        groupingKey.put("instance", InetAddress.getLocalHost().getHostAddress());
        return groupingKey;
    }

    private static String readFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}

