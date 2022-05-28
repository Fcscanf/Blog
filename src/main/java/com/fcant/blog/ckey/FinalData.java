package com.fcant.blog.ckey;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* loaded from: classes2.dex */
public class FinalData {
    public static final String AES = "1";
    public static final String APP_VERSION = "VERSION";
    public static final String BASE_IMG_URL = "http://106.14.31.41:8080/";
    public static final String BASE_IMG_URL_FLAG = "BASE_IMG_URL_FLAG";
    public static final String DB_UPDATE_END = "DB_UPDATE_END";
    public static final String FINGER = "FINGER";
    public static final String GOOGLE_AUTH = "3";
    public static final String GUESTURE = "GUESTURE";
    public static final String GUESTURE_CONTENT = "GUESTURE_CONTENT";
    public static final String IMG_URL = "auth/upload/images/propertyImg/";
    public static final String JSHS = "JSHS";
    public static final String LANGUAGE_CHOICE = "language_choice";
    public static final int LANGUAGE_ENGLISH = 2;
    public static final String LANGUAGE_ID = "id";
    public static final int MAIN_GUIDE = 1;
    public static final String MIX_PASSWORD = "hongsinfo.com";
    public static final String ON = "on";
    public static final String PASSPORTID = "S_USER_PASSPORTID";
    public static final String PASSWORD = "T#s)STq~whp]b52G";
    public static final String SCAN_LOGIN = "scanLogin";
    public static final String SCAN_TOKEN = "scantoken";
    public static final String SERVER_ADDRESS = "SERVER_ADDRESS";
    public static final String SERVER_TIME = "SERVER_TIME";
    public static final int SIMPLIFIED_CHINESE = 1;
    public static final String STR_IV = "Yw*M3^6JpV%0U@qk";
    public static final String TOKEN = "S_USER_TOKEN";
    public static final String TOKEN_LIST = "TOKEN_LIST";
    public static final int TRADITIONAL_CHINESE = 3;
    public static final String USER_INFO = "data";
    public static final String USER_NAME = "userName";
    public static final String VERSIONCODE = "versionCode";
    private static FinalData instance;

    private FinalData() {
    }

    public static FinalData getInstance() {
        if (instance == null) {
            synchronized (FinalData.class) {
                instance = new FinalData();
            }
        }
        return instance;
    }


}
