package com.fcant.blog;

import com.fcant.blog.netty.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BlogApplication.class, args);
        NettyServer nettyServer = new NettyServer();
        nettyServer.run();
    }

}
