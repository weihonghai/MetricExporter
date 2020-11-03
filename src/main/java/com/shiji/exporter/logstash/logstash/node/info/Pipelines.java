package com.shiji.exporter.logstash.logstash.node.info;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pipelines {
    /**
     * main : {"ephemeral_id":"22287079-1348-44e5-afd9-443e94c4f75e","hash":"59ffb75c09974843fc373bdde79334737ca4ad23b0a05270934a04341ded7e67","workers":4,"batch_size":125,"batch_delay":50,"config_reload_automatic":false,"config_reload_interval":3000000000,"dead_letter_queue_enabled":false}
     */

    private Main main;
}
