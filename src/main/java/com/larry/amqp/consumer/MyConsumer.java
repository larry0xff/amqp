package com.larry.amqp.consumer;

import com.larry.amqp.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 谢仲东
 * @date 2019/9/1 18:07
 * @description
 */
@Component
@RabbitListener(queues = RabbitMqConfig.QUEUE_A)
@Slf4j
public class MyConsumer {

    @RabbitHandler
    public void consume(String content) {

        log.info("消费 -> {}", content);
    }
}
