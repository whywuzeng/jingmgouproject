package com.umeng.update.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.io.File;
import u.upd.n;

public class DeltaUpdate
{
  private static boolean a = false;
  private static final String b = "bspatch";

  static
  {
    try
    {
      System.loadLibrary("bspatch");
      a = true;
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      a = false;
    }
  }

  public static int a(String paramString1, String paramString2, String paramString3)
  {
    return bspatch(paramString1, paramString2, paramString3);
  }

  public static String a(Context paramContext)
  {
    return paramContext.getApplicationInfo().sourceDir;
  }

  public static boolean a()
  {
    return a;
  }

  public static String b(Context paramContext)
  {
    paramContext = a(paramContext);
    if (!new File(paramContext).exists())
      return "";
    return n.a(new File(paramContext));
  }

  public static native int bspatch(String paramString1, String paramString2, String paramString3);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.util.DeltaUpdate
 * JD-Core Version:    0.6.2
 */