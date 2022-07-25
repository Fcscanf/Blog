package com.fcant.blog.amqp.producers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BusinessProducers
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 14:26 2022/7/25/0025
 */
@Service
public class BusinessProducers {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsg(String msg) {
        amqpTemplate.convertAndSend("myqueue", msg);
    }
}
