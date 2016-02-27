package com.wsy.webseed.util;

public class SysConstant {

    public static final String  SESSION_LOGIN_ID_KEY = "loginId";

    public static final String  SESSION_USER_KEY     = "user";

    public static final int     MENU_TYPE_MENU       = 1;

    public static final int     MENU_TYPE_BUTTON     = 2;

    public static final Integer sessionTimeout       = 1800;

    public static final Integer SUCCESS              = 1;

    public static final Integer FAILED               = 0;

    public static final String  LOGSTASH_INDEX_PRE   = "logstash-";           // logstash 绱㈠紩鍓嶇紑

    public static final String  LOGSTASH_TYPE_CMPP   = "ipc-cmpp-pop";        // cmpp 鐨勭被鍨�

    public static final String  FIRSTINDEX           = "\"hits\":{\"total\":";

    public static final String  LASTINDEX            = ",\"max_score\"";

    public static final String  ERRORSTATUS          = "exception";

}
