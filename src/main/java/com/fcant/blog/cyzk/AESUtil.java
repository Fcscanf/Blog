package com.fcant.blog.cyzk;

import android.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;

/**
 * AESUtil
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 23:16 2022/5/25/0025
 */
public class AESUtil {
    public static String aesDecrypt(String paramString1, String paramString2) throws Exception {
        if (StringUtils.isEmpty(paramString1)) {
            paramString1 = null;
        } else {
            paramString1 = aesDecryptByBytes(base64Decode(paramString1), paramString2);
        }
        return paramString1;
    }

    public static String aesDecryptByBytes(byte[] paramArrayOfbyte, String paramString) throws Exception {
        KeyGenerator.getInstance("AES").init(128, new SecureRandom(paramString.getBytes()));
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(paramString.getBytes("UTF-8"), "AES"));
        return new String(cipher.doFinal(paramArrayOfbyte));
    }

    public static String aesEncrypt(String paramString1, String paramString2) throws Exception {
        return (paramString2.length() > 32) ?
                base64Encode(aesEncryptToBytes(paramString1, paramString2.substring(0, 32))) :
                base64Encode(aesEncryptToBytes(paramString1, String.format("%1$-32s", new Object[] { paramString2 })));
    }

    public static byte[] aesEncryptToBytes(String paramString1, String paramString2) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator.getInstance("AES").init(128, new SecureRandom(paramString2.getBytes()));
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(paramString2.getBytes("UTF-8"), "AES"));
        return cipher.doFinal(paramString1.getBytes("utf-8"));
    }

    public static byte[] base64Decode(String paramString) throws Exception {
        byte[] arrayOfByte = new byte[0];
        if (StringUtils.isEmpty(paramString)) {
            paramString = null;
        } else {
            arrayOfByte = Base64.decode(paramString, 0);
        }
        return arrayOfByte;
    }

    public static String base64Encode(byte[] paramArrayOfbyte) {
        return (new String(Base64.encode(paramArrayOfbyte, 0))).replaceAll("\r|\n", "");
    }

    public static String binary(byte[] paramArrayOfbyte, int paramInt) {
        return (new BigInteger(1, paramArrayOfbyte)).toString(paramInt);
    }

    public static void main(String[] paramArrayOfString) throws Exception {
        String str1 = aesEncrypt("beginTime=-1&endTime=1462032000&partner_id=10&status=1,2,3,4,5,50", "d5e774de46f53ffeae8623ac47ca3334").replaceAll("\\s|\\r|\\n|\\t", "");
        PrintStream printStream2 = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(str1);
        printStream2.println(stringBuilder.toString());
        String str2 = aesDecrypt(str1, "d5e774de46f53ffeae8623ac47ca3334");
        PrintStream printStream1 = System.out;
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
                stringBuilder.append(str2);
        printStream1.println(stringBuilder.toString());
        System.out.println(System.currentTimeMillis() / 1000L);
    }

    public static byte[] md5(String paramString) throws Exception {
        byte[] arrayOfByte = new byte[0];
        if (StringUtils.isEmpty(paramString)) {
            paramString = null;
        } else {
            arrayOfByte = md5(paramString.getBytes());
        }
        return arrayOfByte;
    }

    public static byte[] md5(byte[] paramArrayOfbyte) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(paramArrayOfbyte);
        return messageDigest.digest();
    }

    public static String md5Encrypt(String paramString) throws Exception {
        if (StringUtils.isEmpty(paramString)) {
            paramString = null;
        } else {
            paramString = base64Encode(md5(paramString));
        }
        return paramString;
    }
}
