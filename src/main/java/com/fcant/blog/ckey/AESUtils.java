package com.fcant.blog.ckey;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/* loaded from: classes2.dex */
public class AESUtils {
    protected static Cipher decryptCipher;

    public static String encrypt(String seed, String iv, String clearText) {
        byte[] result = null;
        try {
            byte[] rawkey = seed.getBytes(StandardCharsets.US_ASCII);
            byte[] rawiv = iv.getBytes();
            result = encrypt(rawkey, rawiv, clearText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content = Base64.getEncoder().encodeToString(result);
        return content;
    }

    public static String decrypt(String seed, String iv, String encrypted) {
        try {
            byte[] rawKey = seed.getBytes(StandardCharsets.US_ASCII);
            byte[] rawiv = iv.getBytes();
            byte[] enc = Base64.getDecoder().decode(encrypted);
            byte[] result = decrypt(rawKey, rawiv, enc);
            String coentn = new String(result);
            return coentn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] encrypt(byte[] raw, byte[] strIV, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, 0, raw.length, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, skeySpec, new IvParameterSpec(strIV));
        byte[] encrypted = cipher.doFinal(clear, 0, clear.length);
        return encrypted;
    }

    protected static Cipher decryptCipher(byte[] raw, byte[] strIV) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, skeySpec, new IvParameterSpec(strIV));
        return cipher;
    }

    private static byte[] decrypt(byte[] raw, byte[] strIV, byte[] encrypted) throws Exception {
        if (decryptCipher == null) {
            decryptCipher = decryptCipher(raw, strIV);
        }
        byte[] decrypted = decryptCipher.doFinal(encrypted);
        return decrypted;
    }
}
