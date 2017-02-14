package com.ismartgo.beacon.http;

public class HttpConstants
{
  public static final String BEACON_INFO = SERVER_URL + "/app/getAppSdkBeaconActivity.do";
  public static final int EVENT_BASE = 256;
  public static final int EVENT_GET_DATA_EEEOR = 259;
  public static final int EVENT_GET_DATA_SUCCESS = 260;
  public static final int EVENT_NETWORD_EEEOR = 258;
  public static final int HTTP_CONNECTION_TIME_OUT = 8000;
  public static final int HTTP_READ_TIME_OUT = 8000;
  public static final String INIT_APP;
  public static final String SAVE_STATISTICS_INFO = SERVER_URL + "/app/saveSdkBeaconActivityByUser.do";
  public static String SERVER_URL = "http://sv.ismartgo.cn:29090/appsv2";
  public static final int STATUS = 10001;

  static
  {
    INIT_APP = SERVER_URL + "/app/initAppSdkService.do";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.http.HttpConstants
 * JD-Core Version:    0.6.2
 */