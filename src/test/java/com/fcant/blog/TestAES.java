package com.fcant.blog;

import com.fcant.blog.bean.User;
import com.fcant.blog.ckey.AESUtils;
import com.fcant.blog.ckey.CompuToken;
import com.fcant.blog.cyzk.AESUtil;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public void ckeyTest() throws Exception {
        String lockdespselect = "FmUSj333j8bD50MMbsOOElTTi8Wqp57kMj4i/BKSzAGeWW87fq4sHaP9d5akwnbF";
        String decryptingCode = AESUtils.decrypt("T#s)STq~whp]b52G", "Yw*M3^6JpV%0U@qk", lockdespselect);
        byte[] arrayOfByte = hex2Bytes(decryptingCode);
        CompuToken compuToken = new CompuToken();
        Date date = new Date();
        boolean bool1 = false;
        String result = compuToken.TokenComput("", arrayOfByte, date, bool1);
        System.out.println(result);
    }

    public static byte[] hex2Bytes(String paramString) {
        byte[] arrayOfByte = new byte[paramString.length() / 2];
        char[] arrayOfChar = paramString.toCharArray();
        byte b1 = 0;
        for (byte b2 = 0; b1 < arrayOfChar.length; b2++) {
            arrayOfByte[b2] = (byte) Integer.parseInt(new String(arrayOfChar, b1, 2), 16);
            b1 += 2;
        }
        return arrayOfByte;
    }

    @Test
    public void cyzkApiKeyTest() throws Exception {
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
    public void generatorDateTest() {
        LocalDate startDate = LocalDate.parse("2021-06-15", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse("2021-08-16", DateTimeFormatter.ISO_LOCAL_DATE);
        Period period = Period.between(startDate, endDate);
        int months = period.getYears() * 12 + period.getMonths();
        for (int i = 0; i <= months; i++) {
            if (i == 0) {
                System.out.println("Start:"+ startDate);
                System.out.println("End:"+ startDate.with(TemporalAdjusters.lastDayOfMonth()));
            }else if (i == months) {
                System.out.println("Start:"+ startDate.plusMonths(i).with(TemporalAdjusters.firstDayOfMonth()));
                System.out.println("End:"+ endDate);
            }else {
                System.out.println("Start:"+ startDate.plusMonths(i).with(TemporalAdjusters.firstDayOfMonth()));
                System.out.println("End:"+ startDate.plusMonths(i).with(TemporalAdjusters.lastDayOfMonth()));
            }
        }
    }

    @Test
    public void readFileTest() throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get("D:\\LinkSpace\\Document\\WorkSpace\\Code\\IDEA\\Blog\\src\\main\\resources\\content.txt"));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        List<String[]> myDataList = new ArrayList<>();
        while ((line=in2.readLine())!=null){
            /* logger.info(line);*/
            String[] details = line.split("\\|@\\|");
            myDataList.add(details);
            System.out.println(line);
        }
        System.out.println(myDataList);
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

