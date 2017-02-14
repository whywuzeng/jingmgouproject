package com.umeng.message.proguard;

public class bG
{
  private static final String a = "ThreadUtil";
  private static int b;

  private static final int a()
  {
    try
    {
      int i = b;
      b = i + 1;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static final void a(Runnable paramRunnable)
  {
    if (paramRunnable != null);
    try
    {
      new Thread(paramRunnable, "agoo-thread-" + a()).start();
      return;
    }
    catch (Throwable paramRunnable)
    {
      bd.e("ThreadUtil", "startTread", paramRunnable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bG
 * JD-Core Version:    0.6.2
 */