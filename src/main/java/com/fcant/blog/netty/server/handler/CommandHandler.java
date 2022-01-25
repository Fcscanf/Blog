package com.fcant.blog.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * MessageHandler
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 20:14 2021/10/21/0021
 */
@Slf4j
public class CommandHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = msg.toString();
        if (StringUtils.isNotBlank(s) && s.startsWith("GET")) {
            log.info("HTTP msg:{}", s);
            if (s.contains("CLOSE-EOF")) {
                String success = "HTTP/1.1 200 \n" +
                        "Content-Type: text/plain;charset=UTF-8\n" +
                        "Content-Length: 7\n" +
                        "Date: Tue, 25 Jan 2022 09:20:13 GMT\n" +
                        "Keep-Alive: timeout=60\n" +
                        "Connection: keep-alive\n" +
                        "\n" +
                        "success";
                ctx.writeAndFlush(success.getBytes(StandardCharsets.UTF_8));
                // 关闭对该端口的监听
                ctx.channel().parent().close();
            } else {
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
