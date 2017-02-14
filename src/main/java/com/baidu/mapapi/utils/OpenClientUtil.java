package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

public class OpenClientUtil
{
  public static int getBaiduMapVersion(Context paramContext)
  {
    if (paramContext == null);
    while (true)
    {
      return 0;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 0).versionName;
        if ((paramContext != null) && (paramContext.length() > 0))
        {
          int i = Integer.valueOf(paramContext.trim().replace(".", "").trim()).intValue();
          return i;
        }
      }
      catch (Exception paramContext)
      {
      }
    }
    return 0;
  }

  public static void getLatestBaiduMapApp(Context paramContext)
  {
    if (paramContext == null)
      return;
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://mo.baidu.com/map/"));
    paramContext.startActivity(localIntent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.OpenClientUtil
 * JD-Core Version:    0.6.2
 */