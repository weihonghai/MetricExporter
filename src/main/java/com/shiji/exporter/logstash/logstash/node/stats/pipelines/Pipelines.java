package com.shiji.exporter.logstash.logstash.node.stats.pipelines;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pipelines {

    private Events events;
    private Plugins plugins;
    private Reloads reloads;
    private Queue queue;
    private DeadLetterQueue dead_letter_queue;
    private String hash;
    private String ephemeral_id;
}
