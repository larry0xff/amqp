package com.larry.amqp.producer;

import com.larry.amqp.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 谢仲东
 * @date 2019/9/1 17:39
 * @description
 */
@Component
@Slf4j
public class MessageProducer implements RabbitTemplate.ConfirmCallback{

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTE_KEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_A, RabbitMqConfig.ROUTE_KEY_A, content, correlationId);
    }
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        log.info(" 回调id:" + correlationData.getId());
        if (b) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:" + s);
        }
    }
}
