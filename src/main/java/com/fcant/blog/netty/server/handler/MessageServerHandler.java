package com.fcant.blog.netty.server.handler;

import com.fcant.blog.utils.AESUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * MessageHandler
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 20:14 2021/10/21/0021
 */
@Slf4j
public class MessageServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer();
        ClassPathResource classPathResource = new ClassPathResource("UserMapper.xml");
        InputStream inputStream = classPathResource.getInputStream();
        String content = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        int modLen = content.getBytes(StandardCharsets.UTF_8).length % 128;
        if (modLen > 0) {
            content += StringUtils.rightPad(" ", 128 - modLen);
        }
        String encrypt = AESUtils.encrypt(content.getBytes(StandardCharsets.UTF_8), "yal0YWzybK5boFLy");
        log.info("加密后的内容 :" + encrypt);
        log.info("密文长度 :" + encrypt.length());
        byteBuf.writeBytes(encrypt.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }
}
