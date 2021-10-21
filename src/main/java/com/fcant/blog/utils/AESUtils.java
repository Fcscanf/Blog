package com.fcant.blog.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AESUtils
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 13:31 2021/10/21/0021
 */
@Slf4j
public class AESUtils {

    private static final String ivParameter = "ABCHINA..ANIHCBA";


    /**
     * 加密
     *
     * @param buff 待加密内容
     * @param password 加密密钥
     * @return String 加密结果
     * @author Fcant 下午 13:39 2021/10/21/0021
     */
    public static String encrypt(byte[] buff, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] passwordBytes = password.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(passwordBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
//        使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] result = cipher.doFinal(buff);
        return parseByte2HexStr(result);
    }

    /**
     * 解密
     *
     * @param buff 待解密内容
     * @param password 解密密钥
     * @return String 解密结果
     * @author Fcant 下午 13:40 2021/10/21/0021
     */
    public static String decrypt(byte[] buff, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] passwordBytes = password.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(passwordBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
//        使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] bytes = cipher.doFinal(buff);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 将二进制转16进制
     *
     * @param buff 二进制内容
     * @return String
     * @author Fcant 下午 13:47 2021/10/21/0021
     */
    public static String parseByte2HexStr(byte[] buff) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte b : buff) {
            String hexString = Integer.toHexString(b & 0xFF);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    /**
     * 将16进制转为二进制
     *
     * @param hexStr 16进制字符串
     * @return byte[] 结果
     * @author Fcant 下午 13:54 2021/10/21/0021
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
