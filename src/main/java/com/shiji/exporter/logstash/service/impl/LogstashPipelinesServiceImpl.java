package com.shiji.exporter.logstash.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shiji.exporter.logstash.logstash.node.stats.LogstashNodeStats;
import com.shiji.exporter.logstash.logstash.node.stats.pipelines.*;
import com.shiji.exporter.logstash.logstash.target.LogstashPrometheusTarget;
import com.shiji.exporter.logstash.metrics.LogstashPipelinesMetrics;
import com.shiji.exporter.logstash.service.LogstashPipelinesService;
import com.shiji.exporter.logstash.util.LogstashMetricsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Slf4j
@Service
public class LogstashPipelinesServiceImpl implements LogstashPipelinesService {
    @Override
    public void pipelinesParse(LogstashNodeStats stats, LogstashPrometheusTarget target, LogstashPipelinesMetrics pipelinesMetrics) {
        if (null != stats) {
            String pipelinesStr = stats.getPipelines();
            if (StringUtils.hasText(pipelinesStr)) {
                JSONObject obj = JSONObject.parseObject(pipelinesStr);
                obj.forEach((k, v) -> {
                    Pipelines pipelines = JSONObject.parseObject(v.toString(), Pipelines.class);
                    if (null != pipelines) {
                        Events events = pipelines.getEvents();
                        if (null != events){
                            this.parseEvents(events,target,k,pipelinesMetrics);
                        }
                        Plugins plugins = pipelines.getPlugins();
                        if (null != plugins){
                            this.parseInputOutputPlugin(plugins.getInputs(),plugins.getOutputs(),target,k,pipelinesMetrics);
                        }
                        Queue queue = pipelines.getQueue();
                        if (null != queue){
                           this.parseQueue(queue,target,k,pipelinesMetrics);
                        }
                    }
                });
            }

        }
    }

    private void parseQueue(Queue queue,LogstashPrometheusTarget target,String pipeline,LogstashPipelinesMetrics pipelinesMetrics){
        if (null != queue){
            Long queueCount = queue.getEvents_count();
            Long maxSize = queue.getMax_queue_size_in_bytes();
            Long queueSize = queue.getQueue_size_in_bytes();
            String queueType = queue.getType();
            if (null != queueCount){
                LogstashMetricsUtils.setMetricGaugePipelinesQueueMetrics(pipelinesMetrics.getPipelinesQueueEventsCount(),target,pipeline,queueType,queueCount.doubleValue());
            }
            if (null != maxSize){
                LogstashMetricsUtils.setMetricGaugePipelinesQueueMetrics(pipelinesMetrics.getPipelinesQueueMaxQueueSizeInBytes(),target,pipeline,queueType,maxSize.doubleValue());
            }
            if (null != queueSize){
                LogstashMetricsUtils.setMetricGaugePipelinesQueueMetrics(pipelinesMetrics.getPipelinesQueueSizeInBytes(),target,pipeline,queueType,queueSize.doubleValue());
            }
        }

    }

    private void parseEvents(Events events, LogstashPrometheusTarget target, String pipeline,LogstashPipelinesMetrics pipelinesMetrics) {
        if (null != events) {
            Long duration = events.getDuration_in_millis();
            if (null != duration){
                LogstashMetricsUtils.setMetricGaugePipelinesEventsMetrics(pipelinesMetrics.getPipelinesEventsDurationInMillis(),target,pipeline,duration.doubleValue());
            }
            Long in = events.getIn();
            if (null != in){
                LogstashMetricsUtils.setMetricGaugePipelinesEventsMetrics(pipelinesMetrics.getPipelinesEventsIn(),target,pipeline,in.doubleValue());
            }
            Long filtered = events.getFiltered();
            if (null != filtered){
                LogstashMetricsUtils.setMetricGaugePipelinesEventsMetrics(pipelinesMetrics.getPipelinesEventsFiltered(),target,pipeline,filtered.doubleValue());
            }
            Long out = events.getOut();
            if (null != out){
                LogstashMetricsUtils.setMetricGaugePipelinesEventsMetrics(pipelinesMetrics.getPipelinesEventsOut(),target,pipeline,out.doubleValue());
            }
            Long queueDuration = events.getQueue_push_duration_in_millis();
            if (null != queueDuration){
                LogstashMetricsUtils.setMetricGaugePipelinesEventsMetrics(pipelinesMetrics.getPipelinesEventsQueuePushDurationInMillis(),target,pipeline,queueDuration.doubleValue());
            }
        }

    }

    public void parseInputOutputPlugin(List<Inputs> inputs, List<Outputs> outputs, LogstashPrometheusTarget target, String pipeline, LogstashPipelinesMetrics pipelinesMetrics){
          if (null != inputs && !inputs.isEmpty()){
              for (Inputs input : inputs){
                   this.parseInput(input,target,pipeline,pipelinesMetrics);
              }
          }
        if (null != outputs && !outputs.isEmpty()){
            for (Outputs outPut : outputs){
                this.parseOutPut(outPut,target,pipeline,pipelinesMetrics);
            }
        }
    }

    private void parseInput(Inputs inputs,LogstashPrometheusTarget target, String pipeline,LogstashPipelinesMetrics pipelinesMetrics){
        String id =inputs.getId();
        String name = inputs.getName();
        Long workers = inputs.getWorkers();
        Long queueSize = inputs.getQueue_size();
        InputsEvents inputsEvents = inputs.getEvents();
        Long out = null;
        Long duration = null;
        if (null != inputsEvents){
            out = inputsEvents.getOut();
            duration = inputsEvents.getQueue_push_duration_in_millis();
        }
        if (null != out){
            LogstashMetricsUtils.setMetricGaugePipelinesInputOrOutPutMetrics(pipelinesMetrics.getPipelinesPluginsInputsEventsOut(),target,pipeline,id,name,out.doubleValue());
        }
        if (null != duration){
            LogstashMetricsUtils.setMetricGaugePipelinesInputOrOutPutMetrics(pipelinesMetrics.getPipelinesPluginsInputsQueuePushDurationInMillis(),target,pipeline,id,name,duration.doubleValue());
        }
        if (null != workers){
            LogstashMetricsUtils.setMetricGaugePipelinesInputOrOutPutMetrics(pipelinesMetrics.getPipelinePluginsInputsWorkers(),target,pipeline,id,name,workers.doubleValue());
        }
        if (null != queueSize){
            LogstashMetricsUtils.setMetricGaugePipelinesInputOrOutPutMetrics(pipelinesMetrics.getPipelinePluginsInputsQueueSize(),target,pipeline,id,name,queueSize.doubleValue());
        }
    }

    private void parseOutPut(Outputs outputs,LogstashPrometheusTarget target, String pipeline,LogstashPipelinesMetrics pipelinesMetrics){
        String id =outputs.getId();
        String name = outputs.getName();
        OutputsEvents  outPutsEvents = outputs.getEvents();
        Long out = null;
        Long duration = null;
        Long in = null;
        if (null != outPutsEvents){
            out = outPutsEvents.getOut();
            duration = outPutsEvents.getDuration_in_millis();
            in = outPutsEvents.getIn();
        }
        if (null != out){
            LogstashMetricsUtils.setMetricGaugePipelinesInputOrOutPutMetrics(pipelinesMetrics.getPipelinesPluginsOutPutsEventsOut(),target,pipeline,id,name,out.doubleValue());
        }
        if (null != duration){
            LogstashMetricsUtils.setMetricGaugePipelinesInputOrOutPutMetrics(pipelinesMetrics.getPipelinesPluginsOutPutsEventsDurationInMillis(),target,pipeline,id,name,duration.doubleValue());
        }
        if (null != in){
            LogstashMetricsUtils.setMetricGaugePipelinesInputOrOutPutMetrics(pipelinesMetrics.getPipelinesPluginsOutPutsEventsIn(),target,pipeline,id,name,in.doubleValue());
        }

    }
}
