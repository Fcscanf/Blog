package com.fcant.blog.ckey;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

/* loaded from: classes2.dex */
public class CompuToken {
    static Mac sha1_HMAC;
    private Date Basedate;
    private String token = "";
    private String time = "2007/01/01 00:00:00";
    private Date Nowdate = new Date();

    public CompuToken() {
        this.Nowdate.getTime();
        if (sha1_HMAC == null) {
            try {
                sha1_HMAC = Mac.getInstance("HmacSHA1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public String TokenComput(String serial, byte[] seed, Date Nowdate, boolean second30) {
        try {
            this.token = GetPasswd2(serial, seed, Nowdate, second30);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.token;
    }

    public String TokenComput(String serial, byte[] seed, Date Nowdate) {
        try {
            this.token = GetPasswd(serial, seed, Nowdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.token;
    }

    public static byte[] Comput0ath(byte[] privatekey, byte[] msg) {
        try {
            if (sha1_HMAC == null) {
                try {
                    sha1_HMAC = Mac.getInstance("HmacSHA1");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            SecretKeySpec secret_key = new SecretKeySpec(privatekey, "HmacSHA1");
            sha1_HMAC.init(secret_key);
            byte[] tmp = sha1_HMAC.doFinal(msg);
            return tmp;
        } catch (InvalidKeyException e2) {
            throw new UnsupportedOperationException(e2);
        }
    }

    public String GetPasswd2(String productserial, byte[] seed, Date token, boolean second30) throws ParseException {
        long Movefactor;
        long Movefactor2 = token.getTime();
        if (second30) {
            Movefactor = (Movefactor2 / 1000) / 30;
        } else {
            Movefactor = (Movefactor2 / 1000) / 60;
        }
        byte[] text = new byte[8];
        for (int i = text.length - 1; i >= 0; i--) {
            text[i] = (byte) (255 & Movefactor);
            Movefactor >>= 8;
        }
        byte[] hashkey = Comput0ath(seed, text);
        int offset = hashkey[hashkey.length - 1] & 15;
        if (31 >= 0 && 31 < hashkey.length - 4) {
            offset = 31;
        }
        int binary = ((hashkey[offset + 2] & 255) << 8) | ((hashkey[offset] & Byte.MAX_VALUE) << 24) | ((hashkey[offset + 1] & 255) << 16) | (hashkey[offset + 3] & 255);
        int otp = binary % 1000000;
        String result = String.valueOf(otp);
        while (result.length() < 6) {
            result = "0" + result;
        }
        return result;
    }

    public String GetPasswd(String productserial, byte[] seed, Date token) throws ParseException {
        long Movefactor = token.getTime();
        long Movefactor2 = (Movefactor / 1000) / 60;
        byte[] text = new byte[8];
        for (int i = text.length - 1; i >= 0; i--) {
            text[i] = (byte) (255 & Movefactor2);
            Movefactor2 >>= 8;
        }
        byte[] hashkey = Comput0ath(seed, text);
        int offset = hashkey[hashkey.length - 1] & 15;
        if (31 >= 0 && 31 < hashkey.length - 4) {
            offset = 31;
        }
        int binary = ((hashkey[offset + 2] & 255) << 8) | ((hashkey[offset] & Byte.MAX_VALUE) << 24) | ((hashkey[offset + 1] & 255) << 16) | (hashkey[offset + 3] & 255);
        int otp = binary % 1000000;
        String result = String.valueOf(otp);
        while (result.length() < 6) {
            result = "0" + result;
        }
        return result;
    }
}
