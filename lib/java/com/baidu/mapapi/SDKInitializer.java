package com.baidu.mapapi;

import android.content.Context;
import com.baidu.platform.comapi.c;

public class SDKInitializer
{
  public static final String SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR = "network error";
  public static final String SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR = "permission check error";
  public static final String SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK = "permission check ok";
  public static final String SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE = "error_code";

  public static void initialize(Context paramContext)
  {
    initialize(null, paramContext);
  }

  public static void initialize(String paramString, Context paramContext)
  {
    c.a(paramString, paramContext);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.SDKInitializer
 * JD-Core Version:    0.6.2
 */