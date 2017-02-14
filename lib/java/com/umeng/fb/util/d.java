package com.umeng.fb.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.fb.model.Store;
import com.umeng.fb.res.g;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

public class d
{
  public static final String a = System.getProperty("line.separator");
  private static final String b = d.class.getName();
  private static String c = null;
  private static String d = null;
  private static SimpleDateFormat e = null;
  private static SimpleDateFormat f = null;

  public static String a(Context paramContext, long paramLong)
  {
    Date localDate1 = new Date();
    Date localDate2 = new Date(paramLong);
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTime(localDate1);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTime(localDate2);
    if (localCalendar1.get(1) == localCalendar2.get(1));
    for (int i = 1; ; i = 0)
    {
      paramLong = (localDate1.getTime() - paramLong) / 60000L;
      if (paramLong >= 1L)
        break;
      if (c == null)
        c = paramContext.getResources().getString(g.g(paramContext));
      return c;
    }
    if (paramLong < 30L)
    {
      if (d == null)
        d = paramContext.getResources().getString(g.h(paramContext));
      return String.format(d, new Object[] { Long.valueOf(paramLong) });
    }
    if (i != 0)
    {
      if (e == null)
        e = new SimpleDateFormat(paramContext.getResources().getString(g.i(paramContext)), Locale.CHINA);
      return e.format(localDate2);
    }
    if (f == null)
      f = new SimpleDateFormat(paramContext.getResources().getString(g.j(paramContext)), Locale.CHINA);
    return f.format(localDate2);
  }

  public static String a(String paramString)
  {
    int i = 0;
    if (paramString == null)
      return null;
    try
    {
      Object localObject1 = paramString.getBytes();
      Object localObject2 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject2).reset();
      ((MessageDigest)localObject2).update((byte[])localObject1);
      localObject1 = ((MessageDigest)localObject2).digest();
      localObject2 = new StringBuffer();
      while (i < localObject1.length)
      {
        ((StringBuffer)localObject2).append(String.format("%02X", new Object[] { Byte.valueOf(localObject1[i]) }));
        i += 1;
      }
      localObject1 = ((StringBuffer)localObject2).toString();
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return paramString.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
  }

  public static JSONObject a(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("device_id", b.f(paramContext));
      localJSONObject.put("idmd5", b.g(paramContext));
      localJSONObject.put("device_model", Build.MODEL);
      localJSONObject.put("appkey", b.p(paramContext));
      localJSONObject.put("channel", b.t(paramContext));
      localJSONObject.put("app_version", b.d(paramContext));
      localJSONObject.put("version_code", b.c(paramContext));
      localJSONObject.put("sdk_type", "Android");
      localJSONObject.put("sdk_version", "5.4.0.20150727");
      localJSONObject.put("os", "Android");
      localJSONObject.put("os_version", Build.VERSION.RELEASE);
      localJSONObject.put("country", b.o(paramContext)[0]);
      localJSONObject.put("language", b.o(paramContext)[1]);
      localJSONObject.put("timezone", b.n(paramContext));
      localJSONObject.put("resolution", b.r(paramContext));
      localJSONObject.put("access", b.j(paramContext)[0]);
      localJSONObject.put("access_subtype", b.j(paramContext)[1]);
      localJSONObject.put("carrier", b.h(paramContext));
      localJSONObject.put("cpu", b.a());
      localJSONObject.put("package", b.u(paramContext));
      localJSONObject.put("uid", Store.getInstance(paramContext).getUid());
      localJSONObject.put("mac", b.q(paramContext));
      localJSONObject.put("protocol_version", "2.0");
      return localJSONObject;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localJSONObject;
  }

  public static void a(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }

  @SuppressLint({"NewApi"})
  public static void a(SharedPreferences.Editor paramEditor)
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      paramEditor.apply();
      return;
    }
    paramEditor.commit();
  }

  public static String b(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i = 0;
      while (i < paramString.length)
      {
        ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      Log.a(b, "getMD5 error", paramString);
    }
    return "";
  }

  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }

  public static boolean c(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.util.d
 * JD-Core Version:    0.6.2
 */