package com.fcant.blog.service.impl;

import com.fcant.blog.ckey.AESUtils;
import com.fcant.blog.ckey.CompuToken;
import com.fcant.blog.ckey.FinalData;
import com.fcant.blog.service.CkeyService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * CkeyServiceImpl
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 22:51 2022/5/30/0030
 */
@Service
public class CkeyServiceImpl implements CkeyService {

    private static final HashMap<String, String> USER_TOKEN = new HashMap<String, String>(){{
        put("fcant", "FmUSj333j8bD50MMbsOOElTTi8Wqp57kMj4i/BKSzAGeWW87fq4sHaP9d5akwnbF");
    }};
    @Override
    public String getCkeyByUser(String user) {
        String decryptingCode = AESUtils.decrypt(FinalData.PASSWORD, FinalData.STR_IV, USER_TOKEN.getOrDefault(user, "fcant"));
        byte[] arrayOfByte = hex2Bytes(decryptingCode);
        CompuToken compuToken = new CompuToken();
        Date date = new Date();
        boolean bool1 = false;
        return compuToken.TokenComput("", arrayOfByte, date, bool1);
    }

    public static byte[] hex2Bytes(String paramString) {
        byte[] arrayOfByte = new byte[paramString.length() / 2];
        char[] arrayOfChar = paramString.toCharArray();
        byte b1 = 0;
        for (byte b2 = 0; b1 < arrayOfChar.length; b2++) {
            arrayOfByte[b2] = (byte)Integer.parseInt(new String(arrayOfChar, b1, 2), 16);
            b1 += 2;
        }
        return arrayOfByte;
    }
}
