package com.fcant.blog.controller;

import com.fcant.blog.netty.client.NettyClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * NettyRemoteController
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 23:55 2021/10/27/0027
 */
@RestController
@RequestMapping("/netty")
public class NettyRemoteController {

    @GetMapping
    public ResponseEntity<String> hello(@RequestParam String msg) throws IOException {
        NettyClient nettyClient = new NettyClient();
        ClassPathResource classPathResource = new ClassPathResource("content.txt");
        InputStream inputStream = classPathResource.getInputStream();
        String content = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String result = nettyClient.run(content);
        return ResponseEntity.ok(result);
    }
}
