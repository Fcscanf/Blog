package com.fcant.blog;

import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.Month;

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
}
