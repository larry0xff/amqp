package com.larry.amqp;

import com.larry.amqp.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmqpApplication.class, args);
    }

    @Autowired
    private MessageProducer producer;

    @GetMapping("/send")
    public void sendMsg(@RequestParam("msg") String msg) {
        producer.sendMsg(msg);

    }
}
