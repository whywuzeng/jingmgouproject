package com.baidu.location.e;

import android.os.HandlerThread;

public class n
{
  private static HandlerThread a = null;

  public static HandlerThread a()
  {
    if (a == null)
    {
      a = new HandlerThread("ServiceStartArguments", 10);
      a.start();
    }
    return a;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.n
 * JD-Core Version:    0.6.2
 */