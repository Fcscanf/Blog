package com.fcant.blog.netty.server;

import com.fcant.blog.netty.server.handler.MessageServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
public class NettyServer {

    private Integer port;

    public NettyServer(Integer port) {
        this.port = port;
    }

    public boolean run() {
        boolean result = false;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024*1024)); //这行配置比较重要;
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new MessageServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(port).sync();
            if (f.isSuccess()) {
                result = true;
                log.info("Server Started on {}...", port);
            }
            f.channel().closeFuture().addListener(new ChannelFutureListener()
            {
                @Override public void operationComplete(ChannelFuture future) throws Exception
                {       //通过回调只关闭自己监听的channel
                    future.channel().close();
                }
            });
            // 等待服务端监听端口关闭
            //f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("NettyServer run error: {}", e.getMessage());
        //}finally {
            // 这里一定要注释掉，因为上面没有阻塞了，不注释的话，这里会直接关闭的
            //workerGroup.shutdownGracefully();
            //bossGroup.shutdownGracefully();
        }
        return result;
    }
}
