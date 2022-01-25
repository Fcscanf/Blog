package com.fcant.blog.utils;

import com.fcant.blog.netty.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * NettyServerUtils
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 22:54 2021/11/20/0020
 */
@Component
@Slf4j
public class NettyServerUtils {

    @Value("${spring.profiles.active}")
    private String env;

    public void ServerRun() throws Exception {
        List<Integer> ports = new ArrayList<>();
        if (env.equals("dev")) {
            ports.add(9001);
            ports.add(9002);
            ports.add(9003);
            ports.add(9004);
        } else {
            ports.add(9011);
            ports.add(9012);
            ports.add(9013);
            ports.add(9014);
        }
        ports.forEach(port -> {
            NettyServer nettyServer = new NettyServer(port);
            try {
                nettyServer.run();
            } catch (Exception e) {
                log.error("NettyServerUtils.ServerRun() error:{}", e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
