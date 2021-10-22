package com.fcant.blog.netty.client;

import com.fcant.blog.netty.client.handler.MessageClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
    public void run() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
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
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new MessageClientHandler());
                }
            });
            // 开启客户端.
            int port = 9000;
            ChannelFuture f = b.connect(host, port).sync();
            // 等到连接关闭.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
