package com.bird.eventbus.kafka.sample;

import com.bird.eventbus.arg.EventArg;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuxx
 * @date 2019/8/28
 */
@Getter
@Setter
public class AnotherEvent extends EventArg {
    private String description;

    public AnotherEvent() {
    }

    public AnotherEvent(String description) {
        this.description = description;
    }
}
