package com.baidu.location.f.a;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.location.f.b.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.UUID;

public final class b
{
  private static final boolean a = false;
  private static final String jdField_do = "DeviceId";
  private static final String jdField_for = "30212102dicudiab";
  private static final String jdField_if = "baidu/.cuid";
  private static final String jdField_int = "com.baidu.deviceid";

  public static String a(Context paramContext)
  {
    a(paramContext, "android.permission.WRITE_SETTINGS");
    a(paramContext, "android.permission.READ_PHONE_STATE");
    a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE");
    Object localObject1 = a.a(paramContext);
    String str2 = ((a)localObject1).a;
    int i;
    String str3;
    if (!((a)localObject1).b)
    {
      i = 1;
      str3 = jdField_if(paramContext);
      if (i == 0)
        break label83;
      localObject1 = c.a(("com.baidu" + str3).getBytes(), true);
    }
    label83: Object localObject3;
    Object localObject2;
    do
    {
      return localObject1;
      i = 0;
      break;
      localObject3 = null;
      localObject2 = Settings.System.getString(paramContext.getContentResolver(), "com.baidu.deviceid");
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject2 = c.a(("com.baidu" + str2 + str3).getBytes(), true);
        String str1 = Settings.System.getString(paramContext.getContentResolver(), (String)localObject2);
        localObject3 = localObject2;
        localObject1 = str1;
        if (!TextUtils.isEmpty(str1))
        {
          Settings.System.putString(paramContext.getContentResolver(), "com.baidu.deviceid", str1);
          a(str2, str1);
          localObject1 = str1;
          localObject3 = localObject2;
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
    }
    while (!TextUtils.isEmpty((CharSequence)localObject2));
    localObject1 = UUID.randomUUID().toString();
    localObject1 = c.a((str2 + str3 + (String)localObject1).getBytes(), true);
    Settings.System.putString(paramContext.getContentResolver(), localObject3, (String)localObject1);
    Settings.System.putString(paramContext.getContentResolver(), "com.baidu.deviceid", (String)localObject1);
    a(str2, (String)localObject1);
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
        localObject = new FileReader((File)localObject);
        BufferedReader localBufferedReader = new BufferedReader((Reader)localObject);
        StringBuilder localStringBuilder = new StringBuilder();
        while (true)
        {
          String str = localBufferedReader.readLine();
          if (str == null)
            break;
          localStringBuilder.append(str);
          localStringBuilder.append("\r\n");
        }
        localBufferedReader.close();
        ((FileReader)localObject).close();
        localObject = new String(a.a("30212102dicudiab", "30212102dicudiab", com.baidu.location.f.b.b.a(localStringBuilder.toString().getBytes()))).split("=");
        if ((localObject != null) && (localObject.length == 2) && (paramString.equals(localObject[0])))
        {
          paramString = localObject[1];
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        return "";
      }
      catch (IOException paramString)
      {
        return "";
      }
      catch (FileNotFoundException paramString)
      {
      }
    }
    return "";
  }

  private static void a(Context paramContext, String paramString)
  {
    if (paramContext.checkCallingOrSelfPermission(paramString) == 0);
    for (int i = 1; ; i = 0)
    {
      if (i == 0)
        try
        {
          throw new SecurityException("Permission Denial: requires permission " + paramString);
        }
        catch (Exception paramContext)
        {
        }
      return;
    }
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
      paramString1.write(com.baidu.location.f.b.b.a(a.jdField_if("30212102dicudiab", "30212102dicudiab", localStringBuilder.toString().getBytes()), "utf-8"));
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

  public static String jdField_do(Context paramContext)
  {
    return a.a(paramContext).a;
  }

  public static String jdField_if(Context paramContext)
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
      boolean bool = true;
      Object localObject = "";
      try
      {
        String str2 = Settings.System.getString(paramContext.getContentResolver(), "bd_setting_i");
        String str1 = str2;
        localObject = str2;
        if (TextUtils.isEmpty(str2))
        {
          localObject = str2;
          str1 = a(paramContext, "");
        }
        localObject = str1;
        Settings.System.putString(paramContext.getContentResolver(), "bd_setting_i", str1);
        i = 0;
        localObject = str1;
        if (i == 0)
          return new a((String)localObject, bool);
      }
      catch (Exception localException)
      {
        while (true)
        {
          int i;
          Log.d("DeviceId", "Settings.System.getString or putString failed");
          if (TextUtils.isEmpty((CharSequence)localObject))
          {
            localObject = a(paramContext, "");
            i = 1;
            continue;
            bool = false;
          }
          else
          {
            i = 1;
          }
        }
      }
    }

    private static String a(Context paramContext, String paramString)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getDeviceId();
          if (!TextUtils.isEmpty(paramContext))
            break label43;
          return paramString;
        }
      }
      catch (Exception paramContext)
      {
        while (true)
        {
          Log.e("DeviceId", "Read IMEI failed", paramContext);
          paramContext = null;
        }
      }
      label43: return paramContext;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.f.a.b
 * JD-Core Version:    0.6.2
 */