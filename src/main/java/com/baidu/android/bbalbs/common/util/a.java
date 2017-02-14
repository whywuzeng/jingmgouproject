package com.baidu.android.bbalbs.common.util;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.android.bbalbs.common.a.b;
import com.baidu.android.bbalbs.common.a.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public final class a
{
  public static String a(Context paramContext)
  {
    a(paramContext, "android.permission.WRITE_SETTINGS");
    a(paramContext, "android.permission.READ_PHONE_STATE");
    a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE");
    Object localObject1 = a.a(paramContext);
    String str2 = ((a)localObject1).a;
    if (((a)localObject1).b);
    String str3;
    for (int i = 0; ; i = 1)
    {
      str3 = c(paramContext);
      if (i == 0)
        break;
      return c.a(("com.baidu" + str3).getBytes(), true);
    }
    Object localObject3 = null;
    Object localObject2 = Settings.System.getString(paramContext.getContentResolver(), "com.baidu.deviceid");
    localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject2 = c.a(("com.baidu" + str2 + str3).getBytes(), true);
      String str1 = Settings.System.getString(paramContext.getContentResolver(), (String)localObject2);
      localObject1 = str1;
      localObject3 = localObject2;
      if (!TextUtils.isEmpty(str1))
      {
        Settings.System.putString(paramContext.getContentResolver(), "com.baidu.deviceid", str1);
        a(str2, str1);
        localObject3 = localObject2;
        localObject1 = str1;
      }
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject1 = a(str2);
      localObject2 = localObject1;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        Settings.System.putString(paramContext.getContentResolver(), localObject3, (String)localObject1);
        Settings.System.putString(paramContext.getContentResolver(), "com.baidu.deviceid", (String)localObject1);
        localObject2 = localObject1;
      }
    }
    localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject1 = UUID.randomUUID().toString();
      localObject1 = c.a((str2 + str3 + (String)localObject1).getBytes(), true);
      Settings.System.putString(paramContext.getContentResolver(), localObject3, (String)localObject1);
      Settings.System.putString(paramContext.getContentResolver(), "com.baidu.deviceid", (String)localObject1);
      a(str2, (String)localObject1);
    }
    return localObject1;
  }

  private static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return "";
      Object localObject = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
      try
      {
        localObject = new BufferedReader(new FileReader((File)localObject));
        StringBuilder localStringBuilder = new StringBuilder();
        while (true)
        {
          String str = ((BufferedReader)localObject).readLine();
          if (str == null)
          {
            ((BufferedReader)localObject).close();
            localObject = new String(com.baidu.android.bbalbs.common.a.a.b("30212102dicudiab", "30212102dicudiab", b.a(localStringBuilder.toString().getBytes()))).split("=");
            if ((localObject == null) || (localObject.length != 2) || (!paramString.equals(localObject[0])))
              break;
            return localObject[1];
          }
          localStringBuilder.append(str);
          localStringBuilder.append("\r\n");
        }
      }
      catch (FileNotFoundException paramString)
      {
        return "";
      }
      catch (Exception paramString)
      {
        return "";
      }
      catch (IOException paramString)
      {
      }
    }
    return "";
  }

  private static void a(Context paramContext, String paramString)
  {
    if (paramContext.checkCallingOrSelfPermission(paramString) == 0);
    for (int i = 1; i == 0; i = 0)
      throw new SecurityException("Permission Denial: requires permission " + paramString);
  }

  private static void a(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("=");
    localStringBuilder.append(paramString2);
    paramString1 = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
    try
    {
      new File(paramString1.getParent()).mkdirs();
      paramString1 = new FileWriter(paramString1, false);
      paramString1.write(b.a(com.baidu.android.bbalbs.common.a.a.a("30212102dicudiab", "30212102dicudiab", localStringBuilder.toString().getBytes()), "utf-8"));
      paramString1.flush();
      paramString1.close();
      return;
    }
    catch (IOException paramString1)
    {
    }
    catch (Exception paramString1)
    {
    }
  }

  public static String b(Context paramContext)
  {
    return a.a(paramContext).a;
  }

  public static String c(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    paramContext = str;
    if (TextUtils.isEmpty(str))
      paramContext = "";
    return paramContext;
  }

  static final class a
  {
    public final String a;
    public final boolean b;

    private a(String paramString, boolean paramBoolean)
    {
      this.a = paramString;
      this.b = paramBoolean;
    }

    static a a(Context paramContext)
    {
      int i = 0;
      Object localObject1 = "";
      try
      {
        String str2 = Settings.System.getString(paramContext.getContentResolver(), "bd_setting_i");
        String str1 = str2;
        localObject1 = str2;
        if (TextUtils.isEmpty(str2))
        {
          localObject1 = str2;
          str1 = a(paramContext, "");
        }
        localObject1 = str1;
        Settings.System.putString(paramContext.getContentResolver(), "bd_setting_i", str1);
        if (i != 0)
        {
          bool = false;
          return new a(str1, bool);
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          boolean bool;
          Log.e("DeviceId", "Settings.System.getString or putString failed", localException);
          int j = 1;
          Object localObject2 = localObject1;
          i = j;
          if (TextUtils.isEmpty((CharSequence)localObject1))
          {
            localObject2 = a(paramContext, "");
            i = j;
            continue;
            bool = true;
          }
        }
      }
    }

    private static String a(Context paramContext, String paramString)
    {
      String str = null;
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        paramContext = str;
        if (localTelephonyManager != null)
          paramContext = localTelephonyManager.getDeviceId();
        str = a(paramContext);
        paramContext = str;
        if (TextUtils.isEmpty(str))
          paramContext = paramString;
        return paramContext;
      }
      catch (Exception paramContext)
      {
        while (true)
        {
          Log.e("DeviceId", "Read IMEI failed", paramContext);
          paramContext = str;
        }
      }
    }

    private static String a(String paramString)
    {
      String str = paramString;
      if (paramString != null)
      {
        str = paramString;
        if (paramString.contains(":"))
          str = "";
      }
      return str;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.android.bbalbs.common.util.a
 * JD-Core Version:    0.6.2
 */