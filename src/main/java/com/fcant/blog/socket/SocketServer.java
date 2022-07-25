package com.fcant.blog.socket;

import com.fcant.blog.utils.AESUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

/**
 * SocketServer
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 17:05 2021/10/20/0020
 */
@Slf4j
public class SocketServer {
    public static void main() throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println("--服务器端已开启--");

        //创建ServerSocket对象，这里的端口号必须与客户端的端口号相同
        ServerSocket server = new ServerSocket(9000);

        //调用方法accept()，用来监听客户端发来的请求
        Socket socket = server.accept();

        //获取输入流对象
        InputStream is = socket.getInputStream();

        //读取输入流中的数据
        int b = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((b =is.read()) != -1){
            stringBuilder.append((char) b);

        }
        log.info("Receive Content:" + stringBuilder.toString());
        OutputStream outputStream = socket.getOutputStream();
//        FileInputStream fileInputStream = new FileInputStream("D:\\LinkSpace\\Document\\WorkSpace\\Code\\IDEA\\Blog\\src\\main\\java\\com\\fcant\\blog\\socket\\UserMapper.xml");
        ClassPathResource classPathResource = new ClassPathResource("UserMapper.xml");
        InputStream inputStream = classPathResource.getInputStream();
//        byte[] bytes = new byte[1024];
//        int readLength = -1;
//        while ((readLength = fileInputStream.read(bytes)) != -1) {
//            outputStream.write(bytes, 0, readLength);
//        }

        String content = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        log.info("Send Content：" + content);
        int modLen = content.getBytes(StandardCharsets.UTF_8).length % 128;
        if (modLen > 0) {
            content += StringUtils.rightPad(" ", 128 - modLen);
        }
        String encrypt = AESUtils.encrypt(content.getBytes(StandardCharsets.UTF_8), "yal0YWzybK5boFLy");
        log.info("加密后的内容 :" + encrypt);
        log.info("密文长度 :" + encrypt.length());
        outputStream.write(encrypt.getBytes(StandardCharsets.UTF_8));
        //关闭流
        inputStream.close();
        outputStream.close();
        is.close();
        socket.close();
        server.close();
    }
}
