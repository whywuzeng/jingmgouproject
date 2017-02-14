package cn.smssdk.framework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.Method;

public class a
{
  private static a b;
  private Context a;

  private a(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
  }

  public static a a(Context paramContext)
  {
    if (b == null)
      b = new a(paramContext);
    return b;
  }

  private Object a(String paramString)
  {
    try
    {
      paramString = this.a.getSystemService(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      d.c(paramString);
    }
    return null;
  }

  private boolean p()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)a("phone");
    if (localTelephonyManager == null)
      return false;
    if (localTelephonyManager.getNetworkType() == 13);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean q()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)a("phone");
    if (localTelephonyManager == null)
      return false;
    switch (localTelephonyManager.getNetworkType())
    {
    default:
      return false;
    case 7:
      return false;
    case 4:
      return false;
    case 2:
      return false;
    case 5:
      return true;
    case 6:
      return true;
    case 1:
      return false;
    case 8:
      return true;
    case 10:
      return true;
    case 9:
      return true;
    case 3:
      return true;
    case 14:
      return true;
    case 12:
      return true;
    case 15:
      return true;
    case 11:
      return false;
    case 13:
      return true;
    case 0:
    }
    return false;
  }

  public String a()
  {
    Object localObject = (WifiManager)a("wifi");
    if (localObject == null);
    do
    {
      return null;
      localObject = ((WifiManager)localObject).getConnectionInfo();
    }
    while (localObject == null);
    String str = ((WifiInfo)localObject).getMacAddress();
    localObject = str;
    if (str == null)
      localObject = null;
    return localObject;
  }

  public String b()
  {
    return Build.MODEL;
  }

  public String c()
  {
    return Build.MANUFACTURER;
  }

  public String d()
  {
    Object localObject = (TelephonyManager)a("phone");
    String str;
    if (localObject == null)
      str = null;
    do
    {
      return str;
      str = ((TelephonyManager)localObject).getDeviceId();
      localObject = "";
      if (str != null)
        localObject = new String(str).replace("0", "");
    }
    while ((str != null) && (((String)localObject).length() > 0));
    return null;
  }

  public String e()
  {
    if (Build.VERSION.SDK_INT >= 9)
      try
      {
        Object localObject = Class.forName("android.os.SystemProperties");
        localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        d.a(localThrowable);
        return null;
      }
    return null;
  }

  public String f()
  {
    return Build.VERSION.RELEASE;
  }

  public String g()
  {
    int[] arrayOfInt = R.getScreenSize(this.a);
    if (this.a.getResources().getConfiguration().orientation == 1)
      return arrayOfInt[0] + "x" + arrayOfInt[1];
    return arrayOfInt[1] + "x" + arrayOfInt[0];
  }

  public String h()
  {
    Object localObject = (TelephonyManager)a("phone");
    if (localObject == null)
      localObject = "-1";
    String str;
    do
    {
      return localObject;
      str = ((TelephonyManager)localObject).getSimOperator();
      localObject = str;
    }
    while (!TextUtils.isEmpty(str));
    return "-1";
  }

  public String i()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)a("phone");
    if (localTelephonyManager == null)
      return "-1";
    return localTelephonyManager.getSimSerialNumber();
  }

  public String j()
  {
    Object localObject1 = (ConnectivityManager)a("connectivity");
    if (localObject1 == null)
      return "none";
    localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
    if ((localObject1 == null) || (!((NetworkInfo)localObject1).isAvailable()))
      return "none";
    switch (((NetworkInfo)localObject1).getType())
    {
    case 2:
    case 3:
    case 4:
    case 5:
    default:
      return "none";
    case 1:
      return "wifi";
    case 0:
      Object localObject2 = Proxy.getDefaultHost();
      String str = "";
      localObject1 = str;
      if (localObject2 != null)
      {
        localObject1 = str;
        if (((String)localObject2).length() > 0)
          localObject1 = " wap";
      }
      if (p())
        return "4G" + (String)localObject1;
      localObject2 = new StringBuilder();
      if (q());
      for (str = "3G"; ; str = "2G")
        return str + (String)localObject1;
    case 7:
      return "bluetooth";
    case 8:
      return "dummy";
    case 9:
      return "ethernet";
    case 6:
    }
    return "wimax";
  }

  public String k()
  {
    String str = j();
    if ((TextUtils.isEmpty(str)) || ("none".equals(str)))
      return "none";
    if ((str.startsWith("4G")) || (str.startsWith("3G")) || (str.startsWith("2G")))
      return "cell";
    if (str.startsWith("wifi"))
      return "wifi";
    return "other";
  }

  public String l()
  {
    return this.a.getPackageName();
  }

  public String m()
  {
    try
    {
      String str = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
      return str;
    }
    catch (Throwable localThrowable)
    {
      d.a(localThrowable);
    }
    return "1.0";
  }

  public boolean n()
  {
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      return bool;
    }
    catch (Throwable localThrowable)
    {
      d.c(localThrowable);
    }
    return false;
  }

  public String o()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.utils.a
 * JD-Core Version:    0.6.2
 */