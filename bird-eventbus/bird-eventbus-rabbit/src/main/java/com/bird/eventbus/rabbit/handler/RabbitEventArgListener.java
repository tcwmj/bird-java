package com.bird.eventbus.rabbit.handler;

import com.alibaba.fastjson.JSON;
import com.bird.eventbus.arg.IEventArg;
import com.bird.eventbus.handler.EventDispatcher;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.nio.charset.Charset;

/**
 * @author liuxx
 * @date 2019/1/18
 */
@Slf4j
public class RabbitEventArgListener implements ChannelAwareMessageListener {

    private EventDispatcher dispatcher;

    public RabbitEventArgListener(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        Class<?> clazz;
        String className = message.getMessageProperties().getReceivedExchange();
        try {
            clazz = Class.forName(className);
            if (!IEventArg.class.isAssignableFrom(clazz)) {
                log.error("事件处理失败：" + className + "不是IEventArg的子类");
            }
        } catch (ClassNotFoundException ex) {
            log.error("事件处理失败：", ex);
            return;
        }

        String body = new String(message.getBody(), Charset.forName("utf8"));
        IEventArg eventArg = (IEventArg) JSON.parseObject(body,clazz);

        dispatcher.enqueue(eventArg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
    }
}
