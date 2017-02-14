package com.ismartgo.app.jpush;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JPushUtil
{
  public static final String KEY_APP_KEY = "JPUSH_APPKEY";
  public static final String PREFS_DAYS = "JPUSH_EXAMPLE_DAYS";
  public static final String PREFS_END_TIME = "PREFS_END_TIME";
  public static final String PREFS_NAME = "JPUSH_EXAMPLE";
  public static final String PREFS_START_TIME = "PREFS_START_TIME";

  public static String GetVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return "Unknown";
  }

  public static String getAppKey(Context paramContext)
  {
    Bundle localBundle = null;
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject1 = localObject2;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        localObject1 = localObject2;
        localBundle = paramContext.metaData;
      }
      paramContext = localObject3;
      if (localBundle != null)
      {
        localObject1 = localObject2;
        paramContext = localBundle.getString("JPUSH_APPKEY");
        if (paramContext != null)
        {
          localObject1 = paramContext;
          int i = paramContext.length();
          if (i == 24);
        }
        else
        {
          paramContext = null;
        }
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return localObject1;
  }

  public static String getImei(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.e(JPushUtil.class.getSimpleName(), paramContext.getMessage());
    }
    return paramString;
  }

  public static boolean isConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }

  public static boolean isEmpty(String paramString)
  {
    if (paramString == null);
    while ((paramString.length() == 0) || (paramString.trim().length() == 0))
      return true;
    return false;
  }

  public static boolean isValidTagAndAlias(String paramString)
  {
    return Pattern.compile("^[一-龥0-9a-zA-Z_-]{0,}$").matcher(paramString).matches();
  }

  public static void showToast(final String paramString, Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Looper.prepare();
        Toast.makeText(JPushUtil.this, paramString, 0).show();
        Looper.loop();
      }
    }).start();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.jpush.JPushUtil
 * JD-Core Version:    0.6.2
 */