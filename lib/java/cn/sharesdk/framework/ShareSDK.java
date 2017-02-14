package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Ln;
import java.util.HashMap;

public class ShareSDK
{
  private static k a;
  private static boolean b = true;

  static String a(int paramInt, String paramString)
  {
    c();
    return a.a(paramInt, paramString);
  }

  static String a(Bitmap paramBitmap)
  {
    c();
    return a.a(paramBitmap);
  }

  static String a(String paramString)
  {
    c();
    return a.d(paramString);
  }

  static String a(String paramString1, boolean paramBoolean, int paramInt, String paramString2)
  {
    c();
    return a.a(paramString1, paramBoolean, paramInt, paramString2);
  }

  static void a(int paramInt1, int paramInt2)
  {
    c();
    a.a(paramInt1, paramInt2);
  }

  static void a(String paramString1, String paramString2)
  {
    c();
    a.a(paramString1, paramString2);
  }

  static boolean a()
  {
    c();
    return a.e();
  }

  static boolean a(HashMap<String, Object> paramHashMap)
  {
    c();
    return a.a(paramHashMap);
  }

  static String b(String paramString1, String paramString2)
  {
    c();
    return a.b(paramString1, paramString2);
  }

  static boolean b()
  {
    c();
    return a.f();
  }

  static boolean b(HashMap<String, Object> paramHashMap)
  {
    c();
    return a.b(paramHashMap);
  }

  private static void c()
  {
    if (a == null)
      throw new NullPointerException("Please call ShareSDK.initSDK(Context) before any action.");
  }

  public static void closeDebug()
  {
    b = false;
  }

  public static void deleteCache()
  {
    c();
    a.g();
  }

  @Deprecated
  public static Platform getPlatform(Context paramContext, String paramString)
  {
    c();
    return a.b(paramString);
  }

  public static Platform getPlatform(String paramString)
  {
    c();
    return a.b(paramString);
  }

  public static Platform[] getPlatformList()
  {
    try
    {
      c();
      Platform[] arrayOfPlatform = a.a();
      return arrayOfPlatform;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Deprecated
  public static Platform[] getPlatformList(Context paramContext)
  {
    try
    {
      paramContext = getPlatformList();
      return paramContext;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  public static int getSDKVersionCode()
  {
    c();
    return a.c();
  }

  public static String getSDKVersionName()
  {
    c();
    return a.b();
  }

  public static <T extends Service> T getService(Class<T> paramClass)
  {
    c();
    return a.c(paramClass);
  }

  public static void initSDK(Context paramContext)
  {
    initSDK(paramContext, null, true);
  }

  public static void initSDK(Context paramContext, String paramString)
  {
    initSDK(paramContext, paramString, true);
  }

  public static void initSDK(Context paramContext, String paramString, boolean paramBoolean)
  {
    Ln.close();
    DeviceHelper localDeviceHelper = DeviceHelper.getInstance(paramContext);
    if (localDeviceHelper == null)
      throw new NullPointerException("Please call ShareSDK.initSDK(Context) in the main process!");
    if ((localDeviceHelper.isMainProcess(paramContext)) && (a == null))
    {
      paramContext = new k(paramContext);
      paramContext.a(paramString);
      paramContext.a(paramBoolean);
      paramContext.startThread();
      a = paramContext;
    }
  }

  public static void initSDK(Context paramContext, boolean paramBoolean)
  {
    initSDK(paramContext, null, paramBoolean);
  }

  public static boolean isDebug()
  {
    return b;
  }

  public static boolean isRemoveCookieOnAuthorize()
  {
    c();
    return a.d();
  }

  public static void logApiEvent(String paramString, int paramInt)
  {
    c();
    a.a(paramString, paramInt);
  }

  public static void logDemoEvent(int paramInt, Platform paramPlatform)
  {
    c();
    a.a(paramInt, paramPlatform);
  }

  public static String platformIdToName(int paramInt)
  {
    c();
    return a.c(paramInt);
  }

  public static int platformNameToId(String paramString)
  {
    c();
    return a.c(paramString);
  }

  public static void registerPlatform(Class<? extends CustomPlatform> paramClass)
  {
    c();
    a.d(paramClass);
  }

  public static void registerService(Class<? extends Service> paramClass)
  {
    c();
    a.a(paramClass);
  }

  public static void removeCookieOnAuthorize(boolean paramBoolean)
  {
    c();
    a.b(paramBoolean);
  }

  public static void setConnTimeout(int paramInt)
  {
    c();
    a.a(paramInt);
  }

  public static void setPlatformDevInfo(String paramString, HashMap<String, Object> paramHashMap)
  {
    c();
    a.a(paramString, paramHashMap);
  }

  public static void setReadTimeout(int paramInt)
  {
    c();
    a.b(paramInt);
  }

  public static void stopSDK()
  {
  }

  public static void stopSDK(Context paramContext)
  {
    stopSDK();
  }

  public static void unregisterPlatform(Class<? extends CustomPlatform> paramClass)
  {
    c();
    a.e(paramClass);
  }

  public static void unregisterService(Class<? extends Service> paramClass)
  {
    c();
    a.b(paramClass);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.ShareSDK
 * JD-Core Version:    0.6.2
 */