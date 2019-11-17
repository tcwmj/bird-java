package com.bird.eventbus.kafka.sample;

import com.bird.eventbus.EventBus;
import com.bird.eventbus.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxx
 * @date 2019/8/28
 */
@RestController
@RequestMapping("/another")
@Slf4j
public class AnotherController {

    @Autowired
    private EventBus eventBus;

    @GetMapping("/send")
    public void sendEvent(String name) {
        AnotherEvent event = new AnotherEvent(name);
        eventBus.push(event);
    }


    /**
     * 事件监听
     * <p>
     * 经过消息队列发送的事件，可在各个微服务之间进行传递
     *
     * @param event
     */
    @EventHandler
    public void handleEvent(AnotherEvent event) {
        log.info("接收到{} {}", event.getClass(), event.getDescription());
    }
}
