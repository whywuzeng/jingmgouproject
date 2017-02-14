package com.baidu.mapapi;

import android.content.Context;
import com.baidu.platform.comapi.a;

public class BMapManager
{
  public static void destroy()
  {
    a.a().d();
  }

  public static Context getContext()
  {
    return a.a().e();
  }

  public static void init()
  {
    a.a().b();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.BMapManager
 * JD-Core Version:    0.6.2
 */