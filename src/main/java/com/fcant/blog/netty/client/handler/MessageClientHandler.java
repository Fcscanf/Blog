package com.fcant.blog.netty.client.handler;

import com.fcant.blog.utils.AESUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * MessageHandler
 * <p>
* encoding:UTF-8
 *
 * @author Fcant 下午 20:14 2021/10/21/0021
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class MessageClientHandler extends ChannelInboundHandlerAdapter {

    private String message;
    private String result;

    public MessageClientHandler(String message) {
        this.message = message;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端接收到的编码内容：" + msg.toString());
        log.info("客户端接收到的密文长度：" + msg.toString().length());
        result = AESUtils.decrypt(AESUtils.parseHexStr2Byte(msg.toString()), "yal0YWzybK5boFLy");
        ctx.close();
//        log.info("客户端解码后的内容：" + decrypt);
//        log.info("客户端解码后内容长度：" + decrypt.length());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(message.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }
}
