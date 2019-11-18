package com.bird.eventbus.kafka.sample.model;

import com.bird.eventbus.arg.EventArg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuxx
 * @date 2019/8/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnotherEvent extends EventArg {

    private String description;

}
