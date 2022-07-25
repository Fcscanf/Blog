package com.fcant.blog;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BlogApplication.class, args);
    }

    //@Bean
    //public ApplicationRunner runner(AmqpTemplate template) {
    //    return args -> template.convertAndSend("myqueue", "foo");
    //}

    @Bean
    public Queue myQueue() {
        return new Queue("myqueue");
    }

}
