package com.fcant.blog.netty.client;

import com.fcant.blog.netty.client.handler.MessageClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * server
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 20:12 2021/10/21/0021
 */
@Slf4j
public class NettyClient {
    String host = "localhost";
    public String run(String msg) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        MessageClientHandler messageClientHandler = new MessageClientHandler(msg);
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.option(ChannelOption.TCP_NODELAY, false);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024*1024)); //这行配置比较重要;
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new FixedLengthFrameDecoder(96512));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(messageClientHandler);
                }
            });
            // 开启客户端.
            int port = 9011;
            ChannelFuture f = b.connect(host, port).sync();
            // 等到连接关闭.
            f.channel().closeFuture().sync();
            return messageClientHandler.getResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
        return "";
    }
}
