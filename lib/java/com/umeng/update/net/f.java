package com.umeng.update.net;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class f
{
  public static final String a = "pause";
  public static final String b = "continue";
  public static final String c = "cancel";
  public static final String d = "com.umeng.intent.DOWNLOAD";
  public static final String e = "com.umeng.broadcast.download.msg";

  public static int a(a.a parama)
  {
    int j = (int)System.currentTimeMillis();
    int i = j;
    if (j < 0)
      i = -j;
    return i;
  }

  public static PendingIntent a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("com.umeng.intent.DOWNLOAD");
    localIntent.addFlags(1073741824);
    localIntent.putExtra("com.umeng.broadcast.download.msg", paramString);
    return PendingIntent.getBroadcast(paramContext, paramString.hashCode(), localIntent, 134217728);
  }

  public static String a(int paramInt, String paramString)
  {
    if (paramInt == 0)
      return null;
    StringBuilder localStringBuilder = new StringBuilder(paramInt + "");
    localStringBuilder.append(":");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static PendingIntent b(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, DownloadingService.class);
    localIntent.putExtra("com.umeng.broadcast.download.msg", paramString);
    return PendingIntent.getService(paramContext, paramString.hashCode(), localIntent, 134217728);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.f
 * JD-Core Version:    0.6.2
 */