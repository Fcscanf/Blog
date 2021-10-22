package com.fcant.blog.utils;

import com.fcant.blog.netty.client.NettyClient;

/**
 * Run
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 上午 11:44 2021/10/20/0020
 */
public class Run {
    public static void main(String[] args) throws Exception {
//        Process exec = Runtime.getRuntime().exec("javac --help");
//        Cipher.getInstance("");
        NettyClient nettyClient = new NettyClient();
        nettyClient.run();
    }
}
