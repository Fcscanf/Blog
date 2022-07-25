package com.fcant.blog.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * CommandByteBufDecoder
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 12:01 2022/1/26/0026
 */
@Slf4j
public class CommandHandler extends ChannelDuplexHandler {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            String s = ((ByteBuf) msg).toString(StandardCharsets.UTF_8);
            if (StringUtils.isNotBlank(s) && s.startsWith("GET")) {
                log.info("收到HTTP命令请求：{}", s);
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
                    ctx.channel().parent().close();
                }
            }
        }
        ctx.fireChannelRead(msg);
    }
}
