package com.baidu.mapapi.common;

import com.baidu.platform.comapi.util.f;

public class SysOSUtil
{
  public static int GetScreenSizeX()
  {
    return f.f();
  }

  public static int GetScreenSizeY()
  {
    return f.h();
  }

  public static float getDensity()
  {
    return f.C;
  }

  public static int getDensityDpi()
  {
    return f.j();
  }

  public static String getDeviceID()
  {
    return f.l();
  }

  public static String getModuleFileName()
  {
    return f.k();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.common.SysOSUtil
 * JD-Core Version:    0.6.2
 */