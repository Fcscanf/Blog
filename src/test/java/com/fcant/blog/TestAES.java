package com.fcant.blog;

import com.fcant.blog.bean.User;
import com.fcant.blog.cyzk.AESUtil;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;

/**
 * TestAES
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 上午 10:04 2021/10/25/0025
 */
public class TestAES {
    @Test
    public void aesTest() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.DECEMBER, 1, 14, 46, 56);
        System.out.println(localDateTime.minusDays(1));
    }

    @Test
    public void urlTest() throws Exception {
        String aesEncrypt = AESUtil.aesEncrypt("OSWMQVjbZrE6KTr6aU2tDewKANIkKsZLQ3ZAAM1LJUE25HxHRHviwPRNKwuyF+xJ", "g/t7lH9bzQpbXNkz4HM3wvri/XJi1W5DI");
        String url = URLEncoder.encode(aesEncrypt, "utf-8");
        System.out.println(url);
    }

//    v8cDoERdrQR14nbLus%!BV3j4ez0Mk3IzFiQN%!FUzHajpOrS6kQORD9GRwW%!F%!BPUq5z7luZcEZ2VVFX4G0%!BwR%!FYl0FQIplSzIHTaKaU23SbaEws%!D
//    v8cDoERdrQR14nbLus%2BV3j4ez0Mk3IzFiQN%2FUzHajpOrS6kQORD9GRwW%2F%2BPUq5z7luZcEZ2VVFX4G0%2BwR%2FYl0FQIplSzIHTaKaU23SbaEws%3D

    @Test
    public void timeTest() {
        Date date = new Date();
        date.setTime(1647878400000L);
        System.out.println(date);
    }

    @Test
    public void splitTest() {
        String[] split = "123,4567,89,,".split(",", 5);
        Arrays.stream(split).map(s -> "prefix" + s).forEach(System.out::println);
        System.out.println(split.length);
    }

    @Test
    public void Test() {
        User user = new User("1", "2");
        System.out.println("before:" + user);
        changeUser(user);
        System.out.println("after:" + user);

    }

    void changeUser(User user) {
        user.setGender("4");
    }
}
