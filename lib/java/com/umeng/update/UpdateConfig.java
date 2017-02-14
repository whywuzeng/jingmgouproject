package com.umeng.update;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import u.upd.a;

public class UpdateConfig
{
  public static final String a = "update";
  public static final String b = "2.6.0.1.20150312";
  public static final String c = "1.4";
  public static final String d = "com.umeng.update.net.DownloadingService";
  public static final String e = "com.umeng.update.UpdateDialogActivity";
  public static final String f = "android.permission.WRITE_EXTERNAL_STORAGE";
  public static final String g = "android.permission.ACCESS_NETWORK_STATE";
  public static final String h = "android.permission.INTERNET";
  public static final String i = "UMUpdateCheck";
  private static final String j = "umeng_update";
  private static final String k = "ignore";
  private static String l;
  private static String m;
  private static String n;
  private static boolean o = true;
  private static boolean p = true;
  private static boolean q = false;
  private static boolean r = true;
  private static boolean s = false;
  private static boolean t = true;
  private static boolean u = true;
  private static int v = 0;

  public static String getAppkey(Context paramContext)
  {
    if (l == null)
      l = a.o(paramContext);
    return l;
  }

  public static String getChannel(Context paramContext)
  {
    if (m == null)
      m = a.t(paramContext);
    return m;
  }

  public static String getIgnoreMd5(Context paramContext)
  {
    String str = paramContext.getApplicationContext().getSharedPreferences("umeng_update", 0).getString("ignore", "");
    paramContext = str;
    if ("".equals(str))
      paramContext = null;
    return paramContext;
  }

  public static String getSlotId()
  {
    return n;
  }

  public static int getStyle()
  {
    return v;
  }

  public static boolean isDeltaUpdate()
  {
    return r;
  }

  public static boolean isRichNotification()
  {
    return u;
  }

  public static boolean isSilentDownload()
  {
    return s;
  }

  public static boolean isUpdateAutoPopup()
  {
    return p;
  }

  public static boolean isUpdateCheck()
  {
    return t;
  }

  public static boolean isUpdateForce()
  {
    return q;
  }

  public static boolean isUpdateOnlyWifi()
  {
    return o;
  }

  public static void saveIgnoreMd5(Context paramContext, String paramString)
  {
    paramContext.getApplicationContext().getSharedPreferences("umeng_update", 0).edit().putString("ignore", paramString).commit();
  }

  public static void setAppkey(String paramString)
  {
    l = paramString;
  }

  public static void setChannel(String paramString)
  {
    m = paramString;
  }

  public static void setDebug(boolean paramBoolean)
  {
    u.upd.b.a = paramBoolean;
  }

  public static void setDeltaUpdate(boolean paramBoolean)
  {
    r = paramBoolean;
  }

  public static void setRichNotification(boolean paramBoolean)
  {
    u = paramBoolean;
  }

  public static void setSilentDownload(boolean paramBoolean)
  {
    s = paramBoolean;
  }

  public static void setSlotId(String paramString)
  {
    n = paramString;
  }

  public static void setStyle(int paramInt)
  {
    v = paramInt;
  }

  public static void setUpdateAutoPopup(boolean paramBoolean)
  {
    p = paramBoolean;
  }

  public static void setUpdateCheck(boolean paramBoolean)
  {
    t = paramBoolean;
  }

  public static void setUpdateForce(boolean paramBoolean)
  {
    q = paramBoolean;
  }

  public static void setUpdateOnlyWifi(boolean paramBoolean)
  {
    o = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.UpdateConfig
 * JD-Core Version:    0.6.2
 */