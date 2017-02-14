package com.ismartgo.app.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import com.ismartgo.app.grid.utils.ToolNetwork;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class DeviceInfoUtil
{
  private static boolean checkRAMState()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static String getAppVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.ismartgo.apppub", 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  public static LinkedHashMap<String, String> getDeviceInfo(Context paramContext)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put("devType", "Android");
    localLinkedHashMap.put("devModel", Build.MANUFACTURER + " " + Build.MODEL);
    localLinkedHashMap.put("internetType", ToolNetwork.getNetworkInfo(paramContext));
    localLinkedHashMap.put("vs", Build.VERSION.RELEASE);
    localLinkedHashMap.put("devRom", getPhoneRAM(paramContext));
    localLinkedHashMap.put("appVersion", getAppVersion(paramContext));
    paramContext = localLinkedHashMap.keySet().iterator();
    while (true)
    {
      if (!paramContext.hasNext())
        return localLinkedHashMap;
      String str = (String)paramContext.next();
      Log.e("deviceInfo", str + "=" + (String)localLinkedHashMap.get(str));
    }
  }

  public static String getPhoneRAM(Context paramContext)
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    if (checkRAMState())
      try
      {
        paramContext = Formatter.formatFileSize(paramContext, localStatFs.getBlockCount() * localStatFs.getBlockSize()).replace(" ", "");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        return "0GB";
      }
    return "0GB";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.utils.DeviceInfoUtil
 * JD-Core Version:    0.6.2
 */