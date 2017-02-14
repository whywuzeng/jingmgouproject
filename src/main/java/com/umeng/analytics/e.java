package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;

public class e
{
  private static String[] a = new String[2];

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    a[0] = paramString1;
    a[1] = paramString2;
    if (paramContext != null)
      h.a(paramContext).a(paramString1, paramString2);
  }

  public static String[] a(Context paramContext)
  {
    if ((!TextUtils.isEmpty(a[0])) && (!TextUtils.isEmpty(a[1])))
      return a;
    if (paramContext != null)
    {
      paramContext = h.a(paramContext).a();
      if (paramContext != null)
      {
        a[0] = paramContext[0];
        a[1] = paramContext[1];
        return a;
      }
    }
    return null;
  }

  public static void b(Context paramContext)
  {
    a[0] = null;
    a[1] = null;
    if (paramContext != null)
      h.a(paramContext).b();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.e
 * JD-Core Version:    0.6.2
 */