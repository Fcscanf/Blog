package com.fcant.blog.socket;

import com.fcant.blog.utils.AESUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * SocketClient
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 17:06 2021/10/20/0020
 */
@Slf4j
public class SocketClient {
    @SneakyThrows
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer();
        try (
                 Socket socket = new Socket("localhost", 9000);
                 OutputStream outputStream = socket.getOutputStream();
                 InputStream inputStream = socket.getInputStream();
             )
        {
            outputStream.write("Hello".getBytes());
            outputStream.flush();
            socket.shutdownOutput();
            int read = -1;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                buffer.writeBytes(bytes, 0, read);
            }
            String receiveContent = buffer.toString(StandardCharsets.UTF_8);
            log.info("客户端接收到的编码内容：" + receiveContent);
            log.info("客户端接收到的密文长度：" + receiveContent.length());
            String decrypt = AESUtils.decrypt(AESUtils.parseHexStr2Byte(receiveContent), "yal0YWzybK5boFLy");
            log.info("客户端解码后的内容：" + decrypt);
            log.info("客户端解码后内容长度：" + decrypt.length());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
