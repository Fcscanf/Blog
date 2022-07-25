package com.fcant.blog.amqp.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * BusinessConsumers
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 14:23 2022/7/25/0025
 */
@Service
public class BusinessConsumers {

    @RabbitListener(queues = "myqueue")
    public void listenOnw(String in) {
        System.out.println("one" + in);
    }

    @RabbitListener(queues = "myqueue")
    public void listenTwo(String in) {
        System.out.println("two" + in);
    }
}
